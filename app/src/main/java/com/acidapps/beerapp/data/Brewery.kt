package com.acidapps.beerapp.data

import com.google.gson.annotations.SerializedName

data class Brewery(
    val id: Int,
    val name: String,
    @SerializedName("brewery_type")
    val type: String,
    val city: String,
    val state: String,
    val country: String,
    @SerializedName("phone")
    val phoneNr: String,
    @SerializedName("website_url")
    val websiteUrl: String
)