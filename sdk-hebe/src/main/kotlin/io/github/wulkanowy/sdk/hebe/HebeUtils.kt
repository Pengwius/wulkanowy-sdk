package io.github.wulkanowy.sdk.hebe

import io.github.wulkanowy.sdk.hebe.models.ApiResponse

internal fun <T> ApiResponse<T>.getEnvelopeOrThrowError(): T? {
    if (envelope == null) {
        when (status.code) {
            // 100 -> "Użytkownik nie jest uprawniony do przeglądania żądanych danych"
            // todo: add more codes
            else -> error("Unknown error: ${status.message}")
        }
    }
    return envelope
}
