package io.github.freewulkanowy.sdk.scrapper.exception

import java.io.IOException

open class ScrapperException internal constructor(message: String, val code: Int = -1) : IOException(message)
