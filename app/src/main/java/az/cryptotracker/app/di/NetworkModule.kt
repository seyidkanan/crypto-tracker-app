package az.cryptotracker.app.di

import az.cryptotracker.app.data.api.base.ApiConstants
import az.cryptotracker.app.data.api.base.CoinGeckoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLogging(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(provideLogging())
            .build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(provideConverterFactory())
            .client(okHttpClient)
            .baseUrl(ApiConstants.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideCoinGeckoService(retrofit: Retrofit): CoinGeckoService {
        return retrofit.create(CoinGeckoService::class.java)
    }

}