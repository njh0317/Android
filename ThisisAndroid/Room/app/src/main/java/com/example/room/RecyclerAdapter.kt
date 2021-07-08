package com.example.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat


class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.Holder>() {
    var memoList = mutableListOf<RoomMemo>()
    var helper: RoomHelper? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setRoomMemo(memoList.get(position))
    }

    override fun getItemCount(): Int {
        return memoList.size
    }


    inner class Holder(val binding: ItemRecyclerBinding):RecyclerView.ViewHolder(binding.root){

        var mRoomMemo: RoomMemo? = null

        init {
            binding.btnDelete.setOnClickListener {
                helper?.roomMemoDao()?.delete(mRoomMemo!!)
                memoList.remove(mRoomMemo)
                notifyDataSetChanged()
            }
        }
        fun setRoomMemo(memo: RoomMemo){
            this.mRoomMemo = memo
            binding.textNo.text = "${memo.no}"
            binding.textContent.text = memo.content
            val sdf = SimpleDateFormat("yyyy/mm/dd hh:mm")
            binding.textDatetime.text = "${sdf.format(memo.datetime)}"
        }
    }
}
