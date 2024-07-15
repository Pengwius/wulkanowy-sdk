package io.github.freewulkanowy.sdk.hebe.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageBox(
    @SerialName("Id")
    val id: Int?,
    @SerialName("GlobalKey")
    val globalKey: String?,
    @SerialName("Name")
    val name: String?,
)
