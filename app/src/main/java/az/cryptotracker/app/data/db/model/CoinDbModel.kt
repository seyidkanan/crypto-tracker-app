package az.cryptotracker.app.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin_table")
data class CoinDbModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null,
    @ColumnInfo(name = "coin_id")
    val coinId: String,
    @ColumnInfo(name = "coin_name")
    val coinName: String,
    @ColumnInfo(name = "coin_price")
    val coinPrice: Double,
    @ColumnInfo(name = "date")
    val date: Long
)
