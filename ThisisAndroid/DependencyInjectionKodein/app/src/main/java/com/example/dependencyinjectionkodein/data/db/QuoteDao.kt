package com.example.dependencyinjectionkodein.data.db

import androidx.lifecycle.LiveData
import com.example.dependencyinjectionkodein.data.model.Quote

interface QuoteDao {
    fun addQuote(quote: Quote)
    fun getQuotes() : LiveData<List<Quote>>

}