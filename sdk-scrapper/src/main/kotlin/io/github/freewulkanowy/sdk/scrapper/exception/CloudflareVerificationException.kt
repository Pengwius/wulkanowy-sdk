package io.github.freewulkanowy.sdk.scrapper.exception

import java.io.IOException

class CloudflareVerificationException(val originalUrl: String?) : IOException()
