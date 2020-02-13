package com.example.countryregiontest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.countryregiontest.data.Country
import androidx.recyclerview.widget.RecyclerView
import com.example.countryregiontest.R
import kotlinx.android.synthetic.main.country_item_view.view.*
import kotlinx.android.synthetic.main.region_item_view.view.*

class CountryListAdapter(private val regionsAndCountries: List<Country>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return if (regionsAndCountries[position].isRegion) {
            0
        } else {
            1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return if (viewType == 0) {
            view = LayoutInflater.from(parent.context).inflate(R.layout.region_item_view, parent, false)
            RegionViewHolder(view)
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.country_item_view, parent, false)
            CountryViewHolder(view)
        }
    }

    override fun getItemCount() = regionsAndCountries.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RegionViewHolder) {
            holder.region.text = regionsAndCountries[position].region
        } else if (holder is CountryViewHolder) {
            holder.country.text = regionsAndCountries[position].name
            holder.capital.text = regionsAndCountries[position].capital
        }
    }

    class RegionViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val region: TextView = view.region
    }

    class CountryViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val country: TextView = view.country
        val capital: TextView = view.capital
    }

}