package com.rahul.gamechangeassignment.ui.search

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahul.gamechangeassignment.R
import com.rahul.gamechangeassignment.data.SearchResponse
import com.rahul.gamechangeassignment.utils.Constants
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_list_search.*
import java.util.*
import javax.inject.Inject


class SearchActivity: DaggerAppCompatActivity() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var searchViewModel: SearchViewModel
    var searchListAdapter = SearchListAdapter(ArrayList())
    private val TAG: String = SearchActivity::class.java.getName()
    lateinit var mActivity: Activity
    lateinit var serchtext: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_search)

        serchtext = findViewById(R.id.searchtext)
        mActivity = this
        initializeRecycler()
        searchViewModel = ViewModelProviders.of(this, viewModelFactory)[SearchViewModel::class.java]
        searchViewModel.getObservableIsLoading()
            .observe(this, object : Observer<Int> {
                val dialog: ProgressDialog = ProgressDialog(mActivity)
                override fun onChanged(@Nullable response: Int) {
                    if (response != null) {
                        if (response == 1) {
                            dialog.setMessage("Loading, please wait....")
                            dialog.show()
                        } else if (response == 2) {
                            if (dialog.isShowing()) {
                                dialog.dismiss()
                            }
                        } else if (response == 3) {
                            if (dialog.isShowing()) {
                                dialog.dismiss()
                            }
                            alertDialogShow(
                                mActivity,
                                getString(R.string.check_internet_connection)
                            )
                        }
                    }
                }
            })

        searchViewModel.getObserverableSearch(Constants.lattitude,Constants.longitude,serchtext.text.toString()).observe(this,
            Observer<SearchResponse> {
                if (it != null) {
                    it.restaurants?.let { it1 -> searchListAdapter.addResults(it1) }
                    recycler.adapter = searchListAdapter
                    searchListAdapter.notifyDataSetChanged()

                }
            })
    }



    private fun initializeRecycler() {
        val gridLayoutManager = GridLayoutManager(this, 1)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager

        }
    }

    fun alertDialogShow(mActivity: Activity, message: String?) {
        try {
            if (!mActivity.isFinishing) {
                val alertDialog: AlertDialog = AlertDialog.Builder(mActivity).create()
                alertDialog.setMessage(message)
                alertDialog.setButton(
                    "OK",
                    DialogInterface.OnClickListener { dialog, which -> alertDialog.dismiss() })
                alertDialog.show()
            }
        } catch (e: Exception) {
            Log.d(TAG, e.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun SearchClicked(view: View) {
        searchViewModel.getObserverableSearch(Constants.lattitude,Constants.longitude,serchtext.text.toString()).observe(this,
            Observer<SearchResponse> {
                if (it != null) {
                    searchtext.text = null
                    val view = this.currentFocus
                    if (view != null) {
                        val imm: InputMethodManager =
                            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(view.windowToken, 0)
                    }
                    recycler.removeAllViews()
                    searchListAdapter = SearchListAdapter(ArrayList())
                    it.restaurants?.let { it1 -> searchListAdapter.addResults(it1) }
                    recycler.adapter = searchListAdapter
                    searchListAdapter.notifyDataSetChanged()

                }
            })

    }
}
