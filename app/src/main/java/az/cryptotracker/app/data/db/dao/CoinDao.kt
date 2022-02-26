package az.cryptotracker.app.data.db.dao

import androidx.room.*
import az.cryptotracker.app.data.db.model.CoinDbModel
import az.cryptotracker.app.data.db.model.CoinMinMaxModel

@Dao
interface CoinDao {

    @Query("SELECT * FROM coin_table WHERE coin_id==:coinId")
    fun loadAllCoinDataById(coinId: String): List<CoinDbModel>

    @Insert
    fun insertAllCoinData(vararg coinModel: CoinDbModel)

    @Query("SELECT * FROM coin_table")
    fun getAllCoinData(): List<CoinDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoinMinMaxValue(coinMinMaxModel: CoinMinMaxModel)

    @Query("SELECT * FROM coin_min_max_table WHERE coin_id==:coinId")
    fun loadCoinMinMaxValueById(coinId: String): CoinMinMaxModel

    @Query("SELECT * FROM coin_min_max_table")
    fun loadAllCoinMinMaxValue(): List<CoinMinMaxModel>

    @Delete
    fun deleteCoinMinMaxValue(coinMinMaxValue: CoinMinMaxModel)

}