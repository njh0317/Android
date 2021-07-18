package com.example.navertranslator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.navertranslator.R
import com.example.navertranslator.data.repository.TranslateRequestFactory
import com.example.navertranslator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    lateinit var viewModel : MainViewModel
    lateinit var viewModelFactory: MainViewModelFactory
    lateinit var fromtext: String
    lateinit var totext: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModelFactory = MainViewModelFactory(TranslateRequestFactory())
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.data.observe(this) {
            binding.totext.text = it
        }
        fromtext = binding.from.text.toString()
        totext = binding.to.text.toString()
        setListner()
    }

    private fun setListner() {
        binding.fromtext.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(s.toString()!="") {
                    word[fromtext]?.let { source ->
                        word[totext]?.let { target ->
                            viewModel.translate(
                                s.toString(),
                                source, target
                            )
                        }
                    }
                }
            }

        })

        binding.button.setOnClickListener {
            val (text1, text2) = arrayOf(binding.from.text, binding.to.text)
            binding.from.text = text2
            binding.to.text = text1

            fromtext = binding.from.text.toString()
            totext = binding.to.text.toString()
        }
    }

    companion object {
        val word = mapOf("한국어" to "ko",
        "영어" to "en",
        "일본어" to "ja",
        "중국어 간체" to "zh-CH",
        "중국어 번체" to "zh-TW",
        "베트남어" to "vi",
        "러시아어" to "ru",
        "스페인어" to "es",
        "이탈리아어" to "it",
        "프랑스어" to "fr")
    }
}