package az.cryptotracker.app.data.api.models

sealed class ApiResult {
    class Success<out T>(val result: T) : ApiResult()
    class Fail(errorModel: ErrorModel) : ApiResult()
}

data class ErrorModel(
    val code: Int? = null,
    val t: Throwable? = null
)
