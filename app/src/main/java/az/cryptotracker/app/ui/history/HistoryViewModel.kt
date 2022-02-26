package az.cryptotracker.app.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.cryptotracker.app.data.db.dao.CoinDao
import az.cryptotracker.app.data.db.model.CoinDbModel
import az.cryptotracker.app.ui.home.adapter.CoinModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val coinDao: CoinDao
) : ViewModel() {

    private val _coinList = MutableLiveData<CoinListState>()
    val coinList: LiveData<CoinListState>
        get() = _coinList

    fun loadCoinPriceListByCoinId(coinId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val coinList = coinDao.loadAllCoinDataById(coinId)
            launch(Dispatchers.Main) {
                if (!coinList.isNullOrEmpty()) {
                    _coinList.value = CoinListState.CoinListData(coinList)
                } else {
                    _coinList.value = CoinListState.NoData()
                }
            }
        }
    }

}

sealed class CoinListState() {
    class CoinListData(val coinList: List<CoinDbModel>) : CoinListState()
    class NoData : CoinListState()
}