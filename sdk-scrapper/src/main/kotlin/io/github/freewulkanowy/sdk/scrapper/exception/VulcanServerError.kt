package io.github.freewulkanowy.sdk.scrapper.exception

class VulcanServerError internal constructor(
    message: String,
    val httpCode: Int,
) : VulcanException(message)
