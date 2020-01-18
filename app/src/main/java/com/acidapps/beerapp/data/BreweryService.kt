package com.acidapps.beerapp.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BreweryService {
    @GET("/breweries")
    fun getBreweries(): Call<List<Brewery>>

    @GET("/breweries/{id}")
    fun getBrewery(@Path("id") id: String): Call<Brewery>
}