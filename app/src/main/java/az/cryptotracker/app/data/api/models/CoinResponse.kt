package az.cryptotracker.app.data.api.models

import com.google.gson.annotations.SerializedName

data class CoinResponse(
    @SerializedName("bitcoin")
    val bitcoin: BitcoinModel?,
    @SerializedName("ethereum")
    val ethereum: EthereumModel?,
    @SerializedName("ripple")
    val ripple: RippleModel?
)

open class UsdCurrencyModel {
    @SerializedName("usd")
    var usd: String? = null
}

class BitcoinModel : UsdCurrencyModel() {
    override fun toString(): String {
        return "usd = $usd"
    }
}

class EthereumModel : UsdCurrencyModel() {
    override fun toString(): String {
        return "usd = $usd"
    }
}

class RippleModel : UsdCurrencyModel() {
    override fun toString(): String {
        return "usd = $usd"
    }
}