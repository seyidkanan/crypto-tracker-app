package az.cryptotracker.app.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import az.cryptotracker.app.BuildConfig
import az.cryptotracker.app.data.db.dao.CoinDao
import az.cryptotracker.app.data.db.model.CoinDbModel
import az.cryptotracker.app.data.db.model.CoinMinMaxModel

@Database(
    entities = [CoinDbModel::class, CoinMinMaxModel::class],
    version = BuildConfig.VERSION_CODE
)
abstract class AppDB : RoomDatabase() {

    abstract fun coinDao(): CoinDao

}