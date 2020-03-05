package com.rahul.gamechangeassignment.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rahul.gamechangeassignment.R
import com.rahul.gamechangeassignment.data.models.Restaurants
import java.util.ArrayList


class SearchListAdapter(issues: List<Restaurants>) : RecyclerView.Adapter<SearchListAdapter.SearchVieHolder>() {

  private var searchList = ArrayList<Restaurants>()
  var onItemClick: ((Restaurants) -> Unit)? = null
  init {
    this.searchList = issues as ArrayList<Restaurants>
  }
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchVieHolder {
    val itemView = LayoutInflater.from(parent?.context).inflate(
      R.layout.search_list_item,
      parent, false)
    return SearchVieHolder(itemView)  }


  override fun onBindViewHolder(holder: SearchVieHolder, position: Int) {
    val searchItem = searchList[position]

    holder?.searchListItem(searchItem)
  }

  override fun getItemCount(): Int {
    return searchList.size
  }



  fun addResults(result: List<Restaurants>){
    val initPosition = searchList.size
    searchList.addAll(result)
    notifyItemRangeInserted(initPosition, searchList.size)
  }

  inner class SearchVieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    init {
      itemView.setOnClickListener {
        onItemClick?.invoke(searchList[adapterPosition])
      }
    }

    var restaurantName = itemView.findViewById<TextView>(R.id.restaurantName)
    var restaurantRating = itemView.findViewById<TextView>(R.id.restaurantRating)
    var restaurantCuisine = itemView.findViewById<TextView>(R.id.restaurantCuisine)
    var restaurantCost = itemView.findViewById<TextView>(R.id.restaurantcost)

    fun searchListItem(issuesItem: Restaurants) {
      restaurantName.text = "Restaurant Name: " +issuesItem.restaurant!!.name
      restaurantRating.text = "Restaurant Rating: "+ issuesItem.restaurant.rating.rating
      restaurantCuisine.text = "Cuisine Type: "+ issuesItem.restaurant.cuisines
      restaurantCost.text = "Average Cost for Two: "+ issuesItem.restaurant.averageCost

    }
  }


}
