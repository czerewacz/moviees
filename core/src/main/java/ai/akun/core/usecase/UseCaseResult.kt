package ai.akun.core.usecase

sealed class UseCaseResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : UseCaseResult<T>()
    data class Error(val exception: Throwable) : UseCaseResult<Nothing>()
}