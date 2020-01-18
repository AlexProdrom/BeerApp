package com.acidapps.beerapp.ui.brewerydetail

import androidx.lifecycle.ViewModel
import com.acidapps.beerapp.data.BreweryRepository

class BreweryDetailViewModel(
    private val breweryRepository: BreweryRepository,
    private val breweryId : Int) : ViewModel() {

    fun getBrewery() = breweryRepository.retrieveBrewery(breweryId)
}