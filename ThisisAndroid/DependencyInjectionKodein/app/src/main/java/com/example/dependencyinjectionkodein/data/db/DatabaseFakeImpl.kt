package com.example.dependencyinjectionkodein.data.db

class DatabaseFakeImpl: Database {
    override val quoteDao: QuoteDao
        get() = QuoteDaoImpl()
}