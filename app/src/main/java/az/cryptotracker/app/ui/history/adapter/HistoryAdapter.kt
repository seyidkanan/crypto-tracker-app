package az.cryptotracker.app.ui.history.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import az.cryptotracker.app.R
import az.cryptotracker.app.data.db.model.CoinDbModel
import az.cryptotracker.app.ui.home.adapter.CoinModel

class HistoryAdapter : RecyclerView.Adapter<HistoryViewHolder>() {

    private val coinList: ArrayList<CoinDbModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_coin_history_item_layout, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    private fun getItem(pos: Int) = coinList.get(pos)

    public fun setCoinList(coinList: List<CoinDbModel>) {
        this.coinList.clear()
        this.coinList.addAll(coinList)
        notifyDataSetChanged()
    }

}