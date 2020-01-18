package com.acidapps.beerapp

import com.acidapps.beerapp.data.Brewery
import com.acidapps.beerapp.data.BreweryRepository
import com.acidapps.beerapp.data.BreweryService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import retrofit2.Call

@RunWith(JUnit4::class)
class BreweryRepositoryTest {
    private lateinit var breweryRepository: BreweryRepository
    private val breweryService = mock(BreweryService::class.java)

    @Before
    fun before() {
        breweryRepository = BreweryRepository(breweryService)
    }

    @Test
    fun retrieveBreweries_valid() {
        // Prepare
        val call: Call<List<Brewery>> = mock(Call::class.java) as Call<List<Brewery>>
        `when`(breweryService.getBreweries()).thenReturn(call)

        // Execute
        breweryRepository.retrieveBreweries()

        // Verify
        verify(breweryService).getBreweries()
    }

    @Test
    fun retrieveBrewery_valid() {
        // Prepare
        val breweryId = 2
        val call: Call<Brewery> = mock(Call::class.java) as Call<Brewery>
        `when`(breweryService.getBrewery(breweryId.toString())).thenReturn(call)

        // Execute
        breweryRepository.retrieveBrewery(breweryId)

        // Verify
        verify(breweryService).getBrewery(breweryId.toString())
    }
}