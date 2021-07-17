package com.example.navernewsapp.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.navernewsapp.data.model.Items
import com.example.navernewsapp.databinding.RecyclerItemBinding
import com.example.navernewsapp.databinding.RecyclerLoadingBinding
import java.text.SimpleDateFormat

class ItemAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_POST = 0 //게시글
        private const val TYPE_LOADING = 1 //NULL
    }

    var items = mutableListOf<Items>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
         return when(viewType) {
            TYPE_POST -> {
                val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemHolder(binding)
            }
            else -> {
                val binding = RecyclerLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                LoadingHolder(binding)

            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ItemHolder) {
            holder.setItem(items.get(position))
        }
        else{

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int { //뷰의 타입 결정
        return when(items[position].title){
            " "-> TYPE_LOADING
            else -> TYPE_POST
        }
    }

    fun addItem(items: List<Items>) {
        this.items.addAll(items)
        this.items.add(Items(" ", " ", " ", " ", " "))
    }
    fun deleteLoading() {
        items.removeAt(items.lastIndex)

    }


    inner class ItemHolder(val binding: RecyclerItemBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val intent = Intent(it.context, NewsActivity::class.java)
                intent.putExtra("description", binding.totalContent.text)
                intent.putExtra("title", binding.title.text)
                intent.putExtra("link", binding.title.text)
                intent.putExtra("date", binding.date.text)
                it.context.startActivity(intent)
            }
        }
        fun setItem(item: Items) {

            binding.description.text = item.description
            binding.date.text = item.pubDate
            binding.title.text = item.title
            binding.link.text = item.link
            binding.totalContent.text = item.description
        }

    }

    inner class LoadingHolder(val binding: RecyclerLoadingBinding) : RecyclerView.ViewHolder(binding.root) {
    }

}

