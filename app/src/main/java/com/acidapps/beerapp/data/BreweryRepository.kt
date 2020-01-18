package com.acidapps.beerapp.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BreweryRepository(private val breweryService: BreweryService) {
    val tag: String = BreweryRepository::class.java.name

    fun retrieveBreweries(): MutableLiveData<List<Brewery>> {
        val breweries = MutableLiveData<List<Brewery>>()

        breweryService.getBreweries().enqueue(object : Callback<List<Brewery>> {
            override fun onResponse(call: Call<List<Brewery>>, response: Response<List<Brewery>>) {
                breweries.value = response.body()
            }

            override fun onFailure(call: Call<List<Brewery>>, t: Throwable) {
                Log.e(tag, t.message)
            }
        })

        return breweries
    }

    fun retrieveBrewery(breweryId: Int): MutableLiveData<Brewery?> {
        val brewery = MutableLiveData<Brewery?>()

        breweryService.getBrewery(breweryId.toString()).enqueue(object : Callback<Brewery> {
            override fun onResponse(call: Call<Brewery>, response: Response<Brewery>) {
                brewery.value = response.body()
            }

            override fun onFailure(call: Call<Brewery>, t: Throwable) {
                Log.e(tag, t.message)
            }
        })

        return brewery
    }
}