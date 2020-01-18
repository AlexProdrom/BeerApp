package com.acidapps.beerapp.ui.brewerylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.acidapps.beerapp.data.Brewery
import com.acidapps.beerapp.databinding.ListItemBreweryBinding

class BreweryAdapter : ListAdapter<Brewery, BreweryAdapter.ViewHolder>(BreweryDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val brewery = getItem(position)
        holder.apply {
            bind(createOnClickListener(brewery.id), brewery)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ListItemBreweryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    class ViewHolder(private val binding: ListItemBreweryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, item: Brewery) {
            binding.apply {
                clickListener = listener
                brewery = item
                executePendingBindings()
            }
        }
    }

    private fun createOnClickListener(breweryId: Int): View.OnClickListener {
        return View.OnClickListener {
            val direction =
                BreweryListFragmentDirections.actionBreweryListFragmentToBreweryDetailFragment(
                    breweryId.toLong()
                )
            it.findNavController().navigate(direction)
        }
    }
}

private class BreweryDiffCallback : DiffUtil.ItemCallback<Brewery>() {

    override fun areItemsTheSame(oldItem: Brewery, newItem: Brewery): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Brewery, newItem: Brewery): Boolean {
        return oldItem == newItem
    }
}