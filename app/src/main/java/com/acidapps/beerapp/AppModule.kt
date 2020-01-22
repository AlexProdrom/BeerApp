package com.acidapps.beerapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.acidapps.beerapp.data.BreweryRepository
import com.acidapps.beerapp.data.BreweryService
import com.acidapps.beerapp.data.IBreweryRepository
import com.acidapps.beerapp.ui.brewerydetail.BreweryDetailViewModel
import com.acidapps.beerapp.ui.brewerylist.BreweryListViewModel
import com.acidapps.beerapp.utils.BASE_URL
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {

    single<IBreweryRepository> { BreweryRepository(get()) }

    factory<BreweryService> {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(get())
            .build()
            .create(BreweryService::class.java)
    }

    factory<OkHttpClient> {
        // 5MB
        OkHttpClient.Builder()
            .cache(Cache(androidContext().cacheDir, (5 * 1024 * 1024).toLong()))
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork(get())!!)
                //If there is Internet, get the cache that was stored 5 seconds ago.
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, max-age=" + 5
                    ).build()
                else
                // If there is no Internet, get the cache that was stored 7 days ago
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                chain.proceed(request)
            }
            .build()
    }

    viewModel { BreweryListViewModel(get()) }

    viewModel { BreweryDetailViewModel(get(), getProperty("breweryId")) }
}

fun hasNetwork(context: Context): Boolean? {
    var isConnected: Boolean? = false
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}
