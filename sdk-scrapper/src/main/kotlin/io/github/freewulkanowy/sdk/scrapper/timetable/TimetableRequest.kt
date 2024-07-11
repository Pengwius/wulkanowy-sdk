package io.github.freewulkanowy.sdk.scrapper.timetable

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TimetableRequest(

    @SerialName("data")
    val date: String,
)
