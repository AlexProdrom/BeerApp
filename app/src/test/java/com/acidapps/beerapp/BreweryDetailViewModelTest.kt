package com.acidapps.beerapp

import com.acidapps.beerapp.data.BreweryRepository
import com.acidapps.beerapp.ui.brewerydetail.BreweryDetailViewModel
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(JUnit4::class)
class BreweryDetailViewModelTest {
    private val breweryRepositoryMock = mock(BreweryRepository::class.java)
    private val breweryId = 2
    private val breweryDetailViewModel = BreweryDetailViewModel(breweryRepositoryMock, breweryId)

    @Test
    fun getBrewery_valid() {
        // Execute
        breweryDetailViewModel.getBrewery()

        // Verify
        verify(breweryRepositoryMock).retrieveBrewery(breweryId)
    }
}