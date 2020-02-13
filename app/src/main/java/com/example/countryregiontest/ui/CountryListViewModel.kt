package com.example.countryregiontest.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countryregiontest.data.Country
import com.example.countryregiontest.network.CountryService
import com.example.countryregiontest.network.RetrofitClientInstance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class CountryListViewModel : ViewModel() {
    private val TAG = CountryListViewModel::class.java.simpleName

    private var countryService: CountryService? = null
    lateinit var regionCountryList: List<Country>
    var regionCountrySuccess = MutableLiveData<Boolean>()

    init {
        regionCountrySuccess.postValue(false)
    }

    fun getCountries() {
        val retrofit = RetrofitClientInstance.getRetrofitInstance()

        retrofit?.let { retrofitInstance ->
            countryService = retrofitInstance.create(CountryService::class.java)
            countryService!!.getCountries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ countryList ->
                    if (countryList != null) {
                        val regionList = countryList.sortedBy { it.region }.distinctBy { it.region }

                        val regionsAndCountries = mutableListOf<Country>()
                        regionList.forEach { regionItem ->
                            regionsAndCountries.add(regionItem.copy(isRegion = true))
                            regionsAndCountries.addAll(countryList.filter { it.region == regionItem.region })
                        }

                        regionCountryList = regionsAndCountries
                        regionCountrySuccess.postValue(true)

                    } else {
                        Log.e(TAG, "No Results")
                    }
                }, { Log.e(TAG, "Error") })
        }
    }
}