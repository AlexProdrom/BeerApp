package com.acidapps.beerapp.data

class BreweryRepository(private val breweryService: BreweryService) : IBreweryRepository {
    override suspend fun retrieveBreweries(): List<Brewery> = breweryService.getBreweries()

    override suspend fun retrieveBrewery(breweryId: Int): Brewery = breweryService.getBrewery(breweryId.toString())
}