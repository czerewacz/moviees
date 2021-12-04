package ai.akun.moviees.commons

import ai.akun.core.di.coreModule
import ai.akun.core.network.di.networkModule
import ai.akun.moviees.BuildConfig
import ai.akun.moviees.feature.tvshows.di.tvShowsModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MovieeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)  //Koin crash with Kotlin 1.6 workaround
            androidContext(this@MovieeApp)
            modules(
                listOf(
                    coreModule,
                    networkModule,
                    tvShowsModule
                )
            )
        }
    }
}