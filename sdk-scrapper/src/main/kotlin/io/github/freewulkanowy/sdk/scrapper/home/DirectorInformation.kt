package io.github.freewulkanowy.sdk.scrapper.home

import java.time.LocalDate

data class DirectorInformation(
    val date: LocalDate,
    val subject: String,
    val content: String,
)
