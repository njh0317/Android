package com.example.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.room.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    var helper : RoomHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        helper = Room.databaseBuilder(this, RoomHelper::class.java, "room_memo")
                .allowMainThreadQueries()
                .build()
        val adapter = RecyclerAdapter()
        adapter.helper = helper
        adapter.memoList.addAll(helper?.roomMemoDao()?.getAll()?:listOf())

        binding.recyclerMemo.adapter = adapter
        binding.recyclerMemo.layoutManager = LinearLayoutManager(this)

        binding.buttonSave.setOnClickListener {
            if(binding.editMemo.text.toString().isNotEmpty()){
                val memo = RoomMemo(binding.editMemo.text.toString(), System.currentTimeMillis())
                helper?.roomMemoDao()?.insert(memo)
                binding.editMemo.setText("")
                adapter.memoList.clear()
                adapter.memoList.addAll(helper?.roomMemoDao()?.getAll()?:listOf())
                adapter.notifyDataSetChanged()
            }
        }
    }
}