package com.acidapps.beerapp.ui.brewerydetail

import androidx.lifecycle.ViewModel
import com.acidapps.beerapp.data.IBreweryRepository

class BreweryDetailViewModel(
    private val breweryRepository: IBreweryRepository,
    private val breweryId : Int) : ViewModel() {

    fun getBrewery() = breweryRepository.retrieveBrewery(breweryId)
}