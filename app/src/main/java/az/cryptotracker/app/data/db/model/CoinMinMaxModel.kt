package az.cryptotracker.app.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin_min_max_table")
data class CoinMinMaxModel(
    @PrimaryKey
    @ColumnInfo(name = "coin_id")
    val coinId: String,
    @ColumnInfo(name = "coin_min_value")
    val min: Double,
    @ColumnInfo(name = "coin_max_value")
    val max: Double
)
