package az.cryptotracker.app.ui.home.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import az.cryptotracker.app.R
import az.cryptotracker.app.ui.RvItemClickListener

class CoinViewHolder(
    private val view: View,
    private val rvItemClickListener: RvItemClickListener<CoinModel>
) : RecyclerView.ViewHolder(view) {

    private var textViewCoinName: TextView = view.findViewById(R.id.textViewCoinName)
    private var textViewCoinPrice: TextView = view.findViewById(R.id.textViewCoinPrice)

    fun bind(coinModel: CoinModel) {
        textViewCoinName.text = coinModel.coinName
        textViewCoinPrice.text = coinModel.coinPrice

        view.setOnClickListener {
            rvItemClickListener.onItemClick(coinModel)
        }
    }

}