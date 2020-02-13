package com.example.countryregiontest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countryregiontest.R
import kotlinx.android.synthetic.main.activity_country_list.*


class CountryListActivity : AppCompatActivity() {

    private lateinit var viewModel: CountryListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_list)

        viewModel = ViewModelProvider(this).get(CountryListViewModel::class.java)
        viewModel.regionCountrySuccess.observe(this, regionCountrySuccessObserver)
        viewModel.getCountries()
    }

    private val regionCountrySuccessObserver = Observer<Boolean> { success ->
        if (success) {
            val layoutManager = LinearLayoutManager(this)
            rvCountries.layoutManager = layoutManager
            val dividerItemDecoration = DividerItemDecoration(rvCountries.context, layoutManager.orientation)
            rvCountries.addItemDecoration(dividerItemDecoration)
            rvCountries.adapter = CountryListAdapter(viewModel.regionCountryList)
        }
    }
}
