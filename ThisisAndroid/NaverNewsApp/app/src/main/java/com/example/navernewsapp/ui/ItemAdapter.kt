package com.example.navernewsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.navernewsapp.data.model.Items
import com.example.navernewsapp.databinding.RecyclerItemBinding

class ItemAdapter: RecyclerView.Adapter<Holder>() {
    var items = mutableListOf<Items>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setItem(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class Holder(val binding: RecyclerItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun setItem(item: Items) {
        binding.description.text = item.description
        binding.date.text = item.pubDate
        binding.title.text = item.title

    }
}