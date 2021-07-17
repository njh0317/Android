package com.example.navernewsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.navernewsapp.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {

    val binding by lazy{ActivityNewsBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setNews()
    }

    fun setNews(){
        val description = intent.getStringExtra("description")
        val title = intent.getStringExtra("title")
        val link = intent.getStringExtra("link")
        val date = intent.getStringExtra("date")

        binding.Title.text = Html.fromHtml(title)
        binding.datetime.text = date
        binding.content.text = Html.fromHtml(description)
    }
}