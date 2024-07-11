package io.github.freewulkanowy.sdk.hebe.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LessonChange(
    @SerialName("Id")
    val id: Int,
    @SerialName("Type")
    val type: Int,
    @SerialName("Separation")
    val separation: Boolean,
)
