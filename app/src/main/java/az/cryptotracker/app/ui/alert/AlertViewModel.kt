package az.cryptotracker.app.ui.alert

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.cryptotracker.app.data.db.dao.CoinDao
import az.cryptotracker.app.data.db.model.CoinMinMaxModel
import az.cryptotracker.app.ui.home.adapter.CoinModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AlertViewModel @Inject constructor(
    private val coinDao: CoinDao
) : ViewModel() {

    private val _currentMinMaxValue = MutableLiveData<CurrentMinMaxValue>()
    val currentMinMaxValue: LiveData<CurrentMinMaxValue>
        get() = _currentMinMaxValue

    fun insertMinMaxValue(min: Double, max: Double, coinId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val coinMinMaxModel = CoinMinMaxModel(
                coinId = coinId,
                min = min,
                max = max
            )
            coinDao.insertCoinMinMaxValue(coinMinMaxModel)
        }
    }

    fun getCurrentMinMaxValueIfHas(coinId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val coinMinMaxModel: CoinMinMaxModel? = coinDao.loadCoinMinMaxValueById(coinId = coinId)
            if (coinMinMaxModel != null && coinMinMaxModel.coinId.isNotEmpty()) {
                launch(Dispatchers.Main) {
                    _currentMinMaxValue.value = CurrentMinMaxValue.MinMaxValue(
                        min = coinMinMaxModel.min,
                        max = coinMinMaxModel.max
                    )
                }
            } else {
                launch(Dispatchers.Main) {
                    _currentMinMaxValue.value = CurrentMinMaxValue.NoValue()
                }
            }
        }
    }

    fun checkInputValidation(minValue: String, maxValue: String): Boolean {
        return try {
            minValue.isNotEmpty() && maxValue.isNotEmpty() &&
                    minValue.toDouble() < maxValue.toDouble()
        } catch (e: Exception) {
            false
        }
    }

}

sealed class CurrentMinMaxValue {
    class MinMaxValue(val min: Double, val max: Double) : CurrentMinMaxValue()
    class NoValue : CurrentMinMaxValue()
}