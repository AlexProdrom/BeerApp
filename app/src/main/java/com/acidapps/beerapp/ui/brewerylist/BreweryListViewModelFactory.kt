package com.acidapps.beerapp.ui.brewerylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acidapps.beerapp.data.BreweryRepository

class BreweryListViewModelFactory(private val breweryRepository: BreweryRepository)
    : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = BreweryListViewModel(breweryRepository) as T
}