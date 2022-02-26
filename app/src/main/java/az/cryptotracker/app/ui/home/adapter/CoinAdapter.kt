package az.cryptotracker.app.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import az.cryptotracker.app.R
import az.cryptotracker.app.ui.RvItemClickListener

class CoinAdapter(private val rvItemClickListener: RvItemClickListener<CoinModel>) :
    RecyclerView.Adapter<CoinViewHolder>() {

    private val coinList: ArrayList<CoinModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_coin_item_layout, parent, false)
        return CoinViewHolder(view, rvItemClickListener)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    private fun getItem(pos: Int) = coinList.get(pos)

    public fun setCoinList(coinList: ArrayList<CoinModel>) {
        this.coinList.clear()
        this.coinList.addAll(coinList)
        notifyDataSetChanged()
    }

}