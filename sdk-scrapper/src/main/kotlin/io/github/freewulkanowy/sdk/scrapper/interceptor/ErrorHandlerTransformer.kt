package io.github.freewulkanowy.sdk.scrapper.interceptor

import io.github.freewulkanowy.sdk.scrapper.ApiResponse
import io.github.freewulkanowy.sdk.scrapper.exception.AuthorizationRequiredException
import io.github.freewulkanowy.sdk.scrapper.exception.FeatureDisabledException
import io.github.freewulkanowy.sdk.scrapper.exception.InvalidPathException
import io.github.freewulkanowy.sdk.scrapper.exception.MissingCsrfException
import io.github.freewulkanowy.sdk.scrapper.exception.ScrapperException
import io.github.freewulkanowy.sdk.scrapper.exception.VulcanException

internal fun <T> ApiResponse<T>.handleErrors(): ApiResponse<T> {
    return when {
        !success && feedback != null -> throw feedback.run {
            when {
                "niespójność danych" in message -> ScrapperException(message)
                "Brak uprawnień" in message -> AuthorizationRequiredException(message)
                "wyłączony" in message -> FeatureDisabledException(message)
                "DB_ERROR" in message -> VulcanException(message)
                "błąd" in message -> VulcanException(message)
                "The controller for path" in message -> InvalidPathException(message)
                "The parameters dictionary contains a null entry for parameter" in message -> InvalidPathException(message)
                "Brak wymaganego ciastka anti-forgery" in message -> MissingCsrfException(message)
                else -> VulcanException(message)
            }
        }

        else -> this
    }
}
