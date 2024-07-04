package io.github.wulkanowy.sdk.hebe.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TimeSlot(
    @SerialName("Id")
    val id: Int,
    @SerialName("Start")
    val start: String,
    @SerialName("End")
    val end: String,
    @SerialName("Display")
    val display: String,
    @SerialName("Position")
    val position: Int,
)

