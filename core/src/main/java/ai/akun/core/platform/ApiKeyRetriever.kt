package ai.akun.core.platform

object ApiKeyRetriever {

    init {
        System.loadLibrary("api-keys")
    }

    external fun getMovieApiKey(): String
}