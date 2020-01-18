package com.acidapps.beerapp.ui.brewerydetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acidapps.beerapp.data.BreweryRepository

class BreweryDetailViewModelFactory(private val breweryRepository : BreweryRepository,
                                    private val breweryId : Int)
    : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BreweryDetailViewModel(breweryRepository, breweryId) as T
    }
}