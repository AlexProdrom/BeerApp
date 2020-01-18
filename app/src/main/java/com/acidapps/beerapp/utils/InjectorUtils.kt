package com.acidapps.beerapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.acidapps.beerapp.data.BreweryRepository
import com.acidapps.beerapp.data.BreweryService
import com.acidapps.beerapp.ui.brewerydetail.BreweryDetailViewModelFactory
import com.acidapps.beerapp.ui.brewerylist.BreweryListViewModelFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InjectorUtils {
    fun provideBreweryListViewModelFactory(breweryRepository: BreweryRepository): BreweryListViewModelFactory =
        BreweryListViewModelFactory(breweryRepository)

    fun provideBreweryDetailViewModelFactory(breweryRepository: BreweryRepository, breweryId: Int): BreweryDetailViewModelFactory =
        BreweryDetailViewModelFactory(breweryRepository, breweryId)

    fun provideBreweryService(okHttpClient: OkHttpClient): BreweryService =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
            .create(BreweryService::class.java)

    fun provideHttpClient(context: Context): OkHttpClient {
        // 5MB
        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(context.cacheDir, cacheSize)

        return OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork(context)!!)
                    //If there is Internet, get the cache that was stored 5 seconds ago.
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
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