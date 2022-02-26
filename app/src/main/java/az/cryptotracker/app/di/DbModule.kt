package az.cryptotracker.app.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import az.cryptotracker.app.data.db.AppDB
import az.cryptotracker.app.data.db.dao.CoinDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDbInstance(@ApplicationContext context: Context): AppDB {
        val dbBuilder = Room.databaseBuilder(
            context,
            AppDB::class.java, "coin-db"
        )
        dbBuilder.setQueryCallback(RoomDatabase.QueryCallback { sqlQuery, bindArgs ->
            println("SQL Query: $sqlQuery SQL Args: $bindArgs")
        }, Executors.newSingleThreadExecutor())
        return dbBuilder.build()
    }

    @Provides
    @Singleton
    fun provideCoinDao(appDB: AppDB): CoinDao {
        return appDB.coinDao()
    }

}