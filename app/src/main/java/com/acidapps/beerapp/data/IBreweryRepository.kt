package com.acidapps.beerapp.data

interface IBreweryRepository {
    suspend fun retrieveBreweries(): List<Brewery>

    suspend fun retrieveBrewery(breweryId: Int): Brewery
}