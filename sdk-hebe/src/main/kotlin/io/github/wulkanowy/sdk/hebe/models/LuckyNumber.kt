package io.github.wulkanowy.sdk.hebe.models

import io.github.wulkanowy.sdk.hebe.CustomDateAdapter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class LuckyNumber(
    @SerialName("Day")
    val day: String,
    @SerialName("Number")
    val number: String,
)
