package ai.akun.core.network.clients

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Serializable for handling error responses
 */
@Serializable
class ErrorResponse(
    @SerialName ("status_code") val code: String?= "400",
    @SerialName ("status_message") val message: String = ""
)