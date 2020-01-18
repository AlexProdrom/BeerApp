package com.acidapps.beerapp.ui.base

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.acidapps.beerapp.BreweryActivity

open class BaseFragment : Fragment() {
    protected fun updateActionBarTitle(title: String) {
        (requireActivity() as BreweryActivity).supportActionBar?.title = title

    }

    protected fun updateActionBarTitle(@StringRes title: Int) {
        (requireActivity() as BreweryActivity).supportActionBar?.title = getString(title)
    }
}