package com.example.navernewsapp.ui

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navernewsapp.R
import com.example.navernewsapp.data.repository.NewsRequestRepository
import com.example.navernewsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var viewModel : MainViewModel
    lateinit var viewModelFactory: MainViewModelFactory
    lateinit var adapter : ItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViewModel()
        setRecyclerView()
    }
    private fun initViewModel(){
        viewModelFactory = MainViewModelFactory(NewsRequestRepository())
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.data.observe(this) {
            Log.d("Main", "data changed ${it}")
            adapter.items.clear()
            adapter.items.addAll(it)
            adapter.notifyDataSetChanged()
        }

        viewModel.dataLoading.observe(this) {
            Log.d("Main", it.toString())
            if(it){
                binding.progressBar.visibility = VISIBLE
            }
            else{
                binding.progressBar.visibility = GONE
            }
        }

    }
    fun setRecyclerView() {
        adapter = ItemAdapter()
        val decoration = DividerItemDecoration(1f, 1f, Color.LTGRAY)
        binding.recycler.addItemDecoration(decoration)

        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(this)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query!=null) {
                    viewModel.getSearchResult(query)
                    hideKeyboard()

                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
        return true
    }

    private fun hideKeyboard()
    {
        currentFocus?.run{
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(windowToken, 0)
        }
    }




}