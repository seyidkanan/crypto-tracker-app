package az.cryptotracker.app.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.cryptotracker.app.data.api.models.ApiResult
import az.cryptotracker.app.data.api.models.CoinResponse
import az.cryptotracker.app.data.api.repository.CoinGeckoRepository
import az.cryptotracker.app.util.mapper.DataClassMapper
import az.cryptotracker.app.ui.home.adapter.CoinModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CoinGeckoRepository
) : ViewModel() {

    private val _coinModelList = MutableLiveData<ArrayList<CoinModel>>()
    val coinModelList: LiveData<ArrayList<CoinModel>>
        get() = _coinModelList

    fun fetchData() {
        viewModelScope.launch(Dispatchers.Main) {
            repository.fetchCoinPrice(
                ids = "bitcoin,ethereum,ripple",
                currencies = "usd"
            ) { apiResult ->
                when (apiResult) {
                    is ApiResult.Success<*> -> {
                        val coinList: ArrayList<CoinModel> =
                            DataClassMapper.coinResponseModelToCoinModel(apiResult.result as CoinResponse?)
                        _coinModelList.value = coinList
                    }
                    is ApiResult.Fail -> {
                        //show error
                    }
                }
            }
        }
    }

}