package com.example.countryregiontest.data

import com.google.gson.annotations.SerializedName

data class Country (
    @SerializedName("name")
    val name: String,
    @SerializedName("capital")
    val capital: String,
    @SerializedName("region")
    val region: String,
    var isRegion: Boolean = false
)
