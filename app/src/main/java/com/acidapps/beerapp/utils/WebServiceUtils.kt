package com.acidapps.beerapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.acidapps.beerapp.data.BreweryService
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun getWebService(okHttpClient: OkHttpClient): BreweryService = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()
    .create(BreweryService::class.java)

fun getHttpClient(context: Context): OkHttpClient = OkHttpClient.Builder()
    .cache(Cache(context.cacheDir, (5 * 1024 * 1024).toLong()))
    .addInterceptor { chain ->
        var request = chain.request()
        request = if (hasNetwork(context)!!)
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

fun hasNetwork(context: Context): Boolean? {
    var isConnected: Boolean? = false
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}