package com.acidapps.beerapp

import com.acidapps.beerapp.data.BreweryRepository
import com.acidapps.beerapp.data.BreweryService
import com.acidapps.beerapp.data.IBreweryRepository
import com.acidapps.beerapp.ui.brewerydetail.BreweryDetailViewModel
import com.acidapps.beerapp.ui.brewerylist.BreweryListViewModel
import com.acidapps.beerapp.utils.getHttpClient
import com.acidapps.beerapp.utils.getWebService
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { BreweryListViewModel(get()) }

    viewModel { BreweryDetailViewModel(get(), getProperty("breweryId")) }

    single<IBreweryRepository> { BreweryRepository(get()) }

    factory<BreweryService> {
        getWebService(get())
    }

    factory {
        getHttpClient(get())
    }
}