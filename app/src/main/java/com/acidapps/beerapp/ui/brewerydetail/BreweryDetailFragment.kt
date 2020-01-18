package com.acidapps.beerapp.ui.brewerydetail


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.acidapps.beerapp.BeerApp
import com.acidapps.beerapp.databinding.FragmentBreweryDetailBinding
import com.acidapps.beerapp.ui.base.BaseFragment
import com.acidapps.beerapp.utils.InjectorUtils.provideBreweryDetailViewModelFactory


class BreweryDetailFragment : BaseFragment() {
    private lateinit var binding: FragmentBreweryDetailBinding
    private val viewModel by viewModels<BreweryDetailViewModel> {
        provideBreweryDetailViewModelFactory(
            (requireActivity().application as BeerApp).breweryRepository,
            args.breweryId.toInt()
        )
    }
    private val args: BreweryDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBreweryDetailBinding.inflate(inflater, container, false)

        setBrewery()
        setPhoneClickListener()
        setUriClickListener()

        return binding.root
    }

    private fun setBrewery() {
        viewModel.getBrewery().observe(this, Observer {
            it?.name?.let { name -> updateActionBarTitle(name) }
            binding.brewery = it
        })
    }

    private fun setPhoneClickListener() {
        binding.btnBreweryPhone.setOnClickListener {
            binding.brewery.let {
                val callIntent = Intent(Intent.ACTION_DIAL)
                callIntent.data = Uri.parse("tel: " + it?.phoneNr)
                startActivity(callIntent)
            }
        }
    }

    private fun setUriClickListener() {
        binding.btnBreweryUri.setOnClickListener {
            binding.brewery?.websiteUrl.let {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                startActivity(browserIntent)
            }
        }
    }
}