package com.acidapps.beerapp.ui.brewerylist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.acidapps.beerapp.BeerApp
import com.acidapps.beerapp.R
import com.acidapps.beerapp.databinding.FragmentBreweryListBinding
import com.acidapps.beerapp.ui.base.BaseFragment
import com.acidapps.beerapp.utils.InjectorUtils

class BreweryListFragment : BaseFragment() {
    private lateinit var binding: FragmentBreweryListBinding
    private val viewModel by viewModels<BreweryListViewModel> {
        InjectorUtils.provideBreweryListViewModelFactory((requireActivity().application as BeerApp).breweryRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBreweryListBinding.inflate(inflater, container, false)
        updateActionBarTitle(R.string.brewery_list_title)
        setupBreweryList()

        return binding.root
    }

    private fun setupBreweryList() {
        val adapter = BreweryAdapter()
        binding.breweryList.adapter = adapter
        viewModel.getBreweries().observe(this, Observer { adapter.submitList(it) })
    }
}