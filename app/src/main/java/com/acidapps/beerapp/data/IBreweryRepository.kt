package com.acidapps.beerapp.data

import androidx.lifecycle.MutableLiveData

interface IBreweryRepository {
    fun retrieveBreweries(): MutableLiveData<List<Brewery>>

    fun retrieveBrewery(breweryId: Int): MutableLiveData<Brewery?>
}