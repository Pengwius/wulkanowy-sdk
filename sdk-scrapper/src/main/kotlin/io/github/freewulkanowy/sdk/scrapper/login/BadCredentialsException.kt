package io.github.freewulkanowy.sdk.scrapper.login

import io.github.freewulkanowy.sdk.scrapper.exception.ScrapperException

class BadCredentialsException internal constructor(message: String) : ScrapperException(message)
