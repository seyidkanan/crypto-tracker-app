package az.cryptotracker.app.di

import az.cryptotracker.app.data.api.repository.CoinGeckoRepository
import az.cryptotracker.app.data.api.repository.CoinGeckoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCoinGeckoRepository(coinGeckoRepositoryImpl: CoinGeckoRepositoryImpl): CoinGeckoRepository

}