package com.example.countryregiontest.network

import com.example.countryregiontest.data.Country
import retrofit2.http.GET
import io.reactivex.Observable

interface CountryService {
    @GET("countries")
    fun getCountries(): Observable<List<Country>>
}