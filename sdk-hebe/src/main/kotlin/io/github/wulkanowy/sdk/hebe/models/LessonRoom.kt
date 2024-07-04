package io.github.wulkanowy.sdk.hebe.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LessonRoom(
    @SerialName("Id")
    val id: Int,
    @SerialName("Code")
    val code: String,
)
