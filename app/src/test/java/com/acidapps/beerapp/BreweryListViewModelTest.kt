package com.acidapps.beerapp


import com.acidapps.beerapp.data.BreweryRepository
import com.acidapps.beerapp.ui.brewerylist.BreweryListViewModel
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class BreweryListViewModelTest {
    private val breweryRepositoryMock = Mockito.mock(BreweryRepository::class.java)
    private val breweryListViewModel = BreweryListViewModel(breweryRepositoryMock)

    @Test
    fun getBrewery_valid() {
        // Execute
        breweryListViewModel.getBreweries()

        // Verify
        Mockito.verify(breweryRepositoryMock).retrieveBreweries()
    }
}