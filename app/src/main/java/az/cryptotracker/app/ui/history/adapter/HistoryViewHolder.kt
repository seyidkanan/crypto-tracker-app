package az.cryptotracker.app.ui.history.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import az.cryptotracker.app.R
import az.cryptotracker.app.data.db.model.CoinDbModel
import az.cryptotracker.app.util.TimeUtil

class HistoryViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private var textViewCoinName: TextView = view.findViewById(R.id.textViewCoinName)
    private var textViewCoinPrice: TextView = view.findViewById(R.id.textViewCoinPrice)
    private var textViewDate: TextView = view.findViewById(R.id.textViewDate)

    fun bind(coinDbModel: CoinDbModel) {
        textViewCoinName.text = coinDbModel.coinName
        textViewCoinPrice.text = "$${coinDbModel.coinPrice}"
        textViewDate.text = TimeUtil.unixTimeToReadableFormat(coinDbModel.date)
    }

}