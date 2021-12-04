package ai.akun.core.network.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopRatedData(
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<Results>,
    @SerialName("total_pages") val total_pages: Int,
    @SerialName("total_results") val total_results: Int
)

@Serializable
data class Results(
    @SerialName("backdrop_path") val backdrop_path: String?,
    @SerialName("first_air_date") val first_air_date: String,
    @SerialName("genre_ids") val genre_ids: List<Int>,
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("origin_country") val origin_country: List<String>,
    @SerialName("original_language") val original_language: String,
    @SerialName("original_name") val original_name: String,
    @SerialName("overview") val overview: String,
    @SerialName("popularity") val popularity: Double,
    @SerialName("poster_path") val poster_path: String?,
    @SerialName("vote_average") val vote_average: Double,
    @SerialName("vote_count") val vote_count: Int
)