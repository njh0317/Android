package com.example.dependencyinjectionkodein

import android.app.Application
import com.example.dependencyinjectionkodein.data.db.Database
import com.example.dependencyinjectionkodein.data.db.DatabaseFakeImpl
import com.example.dependencyinjectionkodein.data.db.QuoteDao
import com.example.dependencyinjectionkodein.data.db.QuoteDaoImpl
import com.example.dependencyinjectionkodein.data.repository.QuoteRepository
import com.example.dependencyinjectionkodein.data.repository.QuoteRepositoryImpl
import com.example.dependencyinjectionkodein.ui.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class QuotesApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        bind<Database>() with singleton { DatabaseFakeImpl() }
        //when request Database instance as an interface -> we are going to create a singleton DatabaseFakeImpl
        // 이후에 다시 요청하면 이미 만들어진 DatabaseFakeImpl 사용

        bind<QuoteDao>() with singleton { instance<Database>().quoteDao }
        bind<QuoteRepository>() with singleton { QuoteRepositoryImpl(instance())}
        bind() from provider { QuotesViewModelFactory(instance()) }
    //init new instance each time when request
    }
}