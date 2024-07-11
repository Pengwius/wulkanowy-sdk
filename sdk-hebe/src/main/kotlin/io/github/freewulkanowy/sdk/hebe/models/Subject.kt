package io.github.freewulkanowy.sdk.hebe.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Subject(
    @SerialName("Id")
    val id: Int,
    @SerialName("Key")
    val key: String,
    @SerialName("Kod")
    val kod: String,
    @SerialName("Name")
    val name: String,
    @SerialName("Position")
    val position: Int,
)
