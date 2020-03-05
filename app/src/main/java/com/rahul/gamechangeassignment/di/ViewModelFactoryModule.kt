package com.rahul.gamechangeassignment.di

import androidx.lifecycle.ViewModelProvider
import com.rahul.gamechangeassignment.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}