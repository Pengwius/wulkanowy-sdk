package io.github.freewulkanowy.sdk.scrapper.login

import io.github.freewulkanowy.sdk.scrapper.exception.ScrapperException

class AccountPermissionException internal constructor(message: String) : ScrapperException(message)
