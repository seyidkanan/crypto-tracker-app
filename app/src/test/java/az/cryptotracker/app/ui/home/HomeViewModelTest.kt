package az.cryptotracker.app.ui.home

//import az.cryptotracker.app.data.api.models.*
//import az.cryptotracker.app.data.api.repository.CoinGeckoRepository
//import com.nhaarman.mockito_kotlin.any
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.runBlocking
//import org.junit.Assert.assertEquals
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito
//import org.mockito.junit.MockitoJUnitRunner
//
//@ExperimentalCoroutinesApi
//@RunWith(MockitoJUnitRunner::class)
//class HomeViewModelTest {
//
//    private lateinit var homeViewModel: HomeViewModel
//
//    @Mock
//    private lateinit var coinGeckoRepository: CoinGeckoRepository
//
//    @Before
//    fun before() {
//        homeViewModel = HomeViewModel(coinGeckoRepository)
//    }
//
//    class CallBackApiResult : (ApiResult) -> Unit {
//        override fun invoke(p1: ApiResult) {
//
//        }
//    }
//
//    @Test
//    fun fetchData_test() = runBlocking {
//        val bitcoin = BitcoinModel()
//        bitcoin.usd = "342"
//        val ripple = RippleModel()
//        ripple.usd = "342"
//        val etherium = EthereumModel()
//        etherium.usd = "342"
//        val result = CoinResponse(
//            bitcoin = bitcoin,
//            ripple = ripple,
//            ethereum = etherium
//        )
//        val callback: (ApiResult) -> Unit = CallBackApiResult()
//
//        Mockito.`when`(coinGeckoRepository.fetchCoinPrice(any(), any(), callback))
//            .thenReturn(callback.invoke(ApiResult.Success(result)))
//
//        homeViewModel.fetchData()
//
//        assertEquals(homeViewModel.coinModelList.value?.size, 3)
//    }
//
//}