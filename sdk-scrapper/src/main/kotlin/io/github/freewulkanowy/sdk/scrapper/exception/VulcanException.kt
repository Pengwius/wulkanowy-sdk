package io.github.freewulkanowy.sdk.scrapper.exception

open class VulcanException internal constructor(message: String, httpCode: Int = -1) : ScrapperException(message, httpCode)
