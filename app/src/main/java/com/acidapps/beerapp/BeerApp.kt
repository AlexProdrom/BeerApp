package com.acidapps.beerapp

import android.app.Application
import android.content.Context
import com.acidapps.beerapp.data.BreweryRepository
import com.acidapps.beerapp.utils.InjectorUtils.provideBreweryService
import com.acidapps.beerapp.utils.InjectorUtils.provideHttpClient

class BeerApp : Application() {
    val breweryRepository: BreweryRepository by lazy { provideBreweryRepository(applicationContext) }

    private fun provideBreweryRepository(context: Context): BreweryRepository {
        return BreweryRepository(provideBreweryService(provideHttpClient(context)))
    }
}