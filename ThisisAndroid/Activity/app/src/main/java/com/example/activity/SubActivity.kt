package com.example.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activity.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    val binding by lazy { ActivitySubBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.to1.text = intent.getStringExtra("from1")
        binding.to2.text = intent.getIntExtra("from2", 0).toString()

        binding.btnClose.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("returnValue", binding.editMessage.text.toString())
            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }
}