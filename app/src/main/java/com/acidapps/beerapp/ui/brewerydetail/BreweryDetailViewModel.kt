package com.acidapps.beerapp.ui.brewerydetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.acidapps.beerapp.data.IBreweryRepository
import kotlinx.coroutines.Dispatchers

class BreweryDetailViewModel(
    private val breweryRepository: IBreweryRepository,
    private val breweryId: Int
) : ViewModel() {

    val brewery = liveData(Dispatchers.IO) {
        val brewery = breweryRepository.retrieveBrewery(breweryId)
        emit(brewery)
    }
}