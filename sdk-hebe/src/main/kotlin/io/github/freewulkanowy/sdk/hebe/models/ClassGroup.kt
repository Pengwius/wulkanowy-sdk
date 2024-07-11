package io.github.freewulkanowy.sdk.hebe.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClassGroup(
    @SerialName("Id")
    val id: Int,
    @SerialName("Key")
    val key: String,
    @SerialName("Shortcut")
    val shortcut: String,
    @SerialName("Name")
    val name: String,
    @SerialName("PartType")
    val partType: String,
)
