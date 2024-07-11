package io.github.freewulkanowy.sdk.hebe.models

import io.github.freewulkanowy.sdk.hebe.CustomDateAdapter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class Date(
    @SerialName("Date")
    @Serializable(with = CustomDateAdapter::class)
    val date: LocalDate,
    @SerialName("DateDisplay")
    val dateDisplay: String,
    @SerialName("Time")
    val time: String,
    @SerialName("Timestamp")
    val timestamp: Long,
)
