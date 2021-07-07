package com.example.viewpagerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpagerview.databinding.ItemViewpagerBinding

class CustomPagerAdapter: RecyclerView.Adapter<Holder>() {
    var textList = listOf<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val text = textList[position]
        holder.setText(text)
    }

    override fun getItemCount(): Int {
        return textList.size
    }
}

class Holder(val binding: ItemViewpagerBinding):RecyclerView.ViewHolder(binding.root){
    fun setText(text: String) { binding.textView.text = text }
}