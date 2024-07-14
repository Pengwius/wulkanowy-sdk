package io.github.freewulkanowy.sdk.hebe.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Subject(
    @SerialName("Id")
    val id: Int? = null,
    @SerialName("Key")
    val key: String? = null,
    @SerialName("Kod")
    val kod: String? = null,
    @SerialName("Name")
    val name: String? = null,
    @SerialName("Position")
    val position: Int? = null,
)
