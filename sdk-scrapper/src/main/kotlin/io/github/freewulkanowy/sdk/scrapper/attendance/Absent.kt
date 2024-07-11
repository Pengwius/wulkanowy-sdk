package io.github.freewulkanowy.sdk.scrapper.attendance

import java.time.LocalDateTime

data class Absent(
    val date: LocalDateTime,
    val timeId: Int?,
)
