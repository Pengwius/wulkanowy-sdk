package io.github.freewulkanowy.sdk.scrapper.login

import io.github.freewulkanowy.sdk.scrapper.exception.ScrapperException

class PasswordChangeRequiredException internal constructor(message: String, val redirectUrl: String) : ScrapperException(message)
