package com.acidapps.beerapp.data

import retrofit2.http.GET
import retrofit2.http.Path

interface BreweryService {
    @GET("/breweries")
    suspend fun getBreweries(): List<Brewery>

    @GET("/breweries/{id}")
    suspend fun getBrewery(@Path("id") id: String): Brewery
}