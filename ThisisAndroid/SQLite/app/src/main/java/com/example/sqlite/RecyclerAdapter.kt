package com.example.sqlite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat


class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.Holder>() {
    var memoList = mutableListOf<Memo>()
    var helper: SqliteHelper? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setMemo(memoList.get(position))
    }

    override fun getItemCount(): Int {
        return memoList.size
    }


    inner class Holder(val binding: ItemRecyclerBinding):RecyclerView.ViewHolder(binding.root){

        var mMemo: Memo? = null

        init {
            binding.btnDelete.setOnClickListener {
                helper?.deleteMemo(mMemo!!)
                memoList.remove(mMemo)
                notifyDataSetChanged()
            }
        }
        fun setMemo(memo: Memo){
            this.mMemo = memo
            binding.textNo.text = "${memo.no}"
            binding.textContent.text = memo.content
            val sdf = SimpleDateFormat("yyyy/mm/dd hh:mm")
            binding.textDatetime.text = "${sdf.format(memo.datetime)}"
        }
    }
}
