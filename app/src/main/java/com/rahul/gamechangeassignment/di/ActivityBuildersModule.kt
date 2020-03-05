package com.rahul.gamechangeassignment.di

import com.rahul.gamechangeassignment.ui.search.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
 abstract class ActivityBuildersModule {

    @ContributesAndroidInjector( modules = [SearchViewModelModule::class])
    abstract fun contributeListOfSearchActivity(): SearchActivity

}