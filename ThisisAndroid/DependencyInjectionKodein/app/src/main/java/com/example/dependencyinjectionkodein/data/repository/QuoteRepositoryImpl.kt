package com.example.dependencyinjectionkodein.data.repository

import androidx.lifecycle.LiveData
import com.example.dependencyinjectionkodein.data.db.QuoteDao
import com.example.dependencyinjectionkodein.data.model.Quote

class QuoteRepositoryImpl(private val quoteDao: QuoteDao)
    :QuoteRepository{
    override fun addQuote(quote: Quote) {
        quoteDao.addQuote(quote)
    }

    override fun getQuotes(): LiveData<List<Quote>> = quoteDao.getQuotes()
}