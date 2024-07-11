package io.github.freewulkanowy.sdk.pojo

import java.time.LocalDate

data class LastAnnouncement(
    val date: LocalDate,
    val subject: String,
    val author: String,
    val content: String,
)
