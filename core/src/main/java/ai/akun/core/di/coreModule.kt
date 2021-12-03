package ai.akun.core.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

val coreModule = module {
    single<CoroutineContext> { Dispatchers.IO }
}