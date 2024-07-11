package io.github.freewulkanowy.sdk.scrapper.home

import java.time.LocalDate

data class LastAnnouncement(
    val date: LocalDate,
    val subject: String,
    val author: String,
    val content: String,
)
