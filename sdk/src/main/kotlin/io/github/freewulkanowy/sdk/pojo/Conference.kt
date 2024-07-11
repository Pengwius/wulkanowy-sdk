package io.github.freewulkanowy.sdk.pojo

import java.time.ZonedDateTime

data class Conference(
    val place: String,
    val topic: String,
    val agenda: String,
    val presentOnConference: String,
    val online: Any?,
    val id: Int,
    val date: ZonedDateTime,
)
