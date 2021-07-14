package com.example.dependencyinjectionkodein.data.repository

import androidx.lifecycle.LiveData
import com.example.dependencyinjectionkodein.data.model.Quote

interface QuoteRepository {
    fun addQuote(quote: Quote)
    fun getQuotes() : LiveData<List<Quote>>
}