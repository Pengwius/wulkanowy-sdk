package io.github.freewulkanowy.sdk.scrapper.exception

class VulcanClientError internal constructor(
    message: String,
    val httpCode: Int,
) : VulcanException(message)
