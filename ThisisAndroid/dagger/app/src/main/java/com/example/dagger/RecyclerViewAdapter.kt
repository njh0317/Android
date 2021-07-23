package com.example.dagger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dagger.model.RecyclerData
import kotlinx.android.synthetic.main.recycler_view_list.view.*

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.Holder>() {
    private var listData: List<RecyclerData>? = null

    fun setUpdatedData(listData: List<RecyclerData>) {
        this.listData = listData
    }
    inner class Holder(view: View): RecyclerView.ViewHolder(view) {
        val imageView = view.imageView
        val textviewName = view.textviewName
        val textViewDescription = view.textviewDescription
        fun bind(data: RecyclerData) {
            textviewName.setText(data.name)
            textViewDescription.setText(data.description)
            Glide.with(imageView)
                .load(data.owner?.avatar_url)
                .apply(RequestOptions.centerCropTransform())
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_list, parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if(listData == null) return 0
        else return listData?.size!!
    }
}