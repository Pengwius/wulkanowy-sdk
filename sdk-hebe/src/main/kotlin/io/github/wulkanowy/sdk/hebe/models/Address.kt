package io.github.wulkanowy.sdk.hebe.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Address(
    @SerialName("GlobalKey")
    val globalKey: String,
    @SerialName("Name")
    val name: String,
    @SerialName("HasRead")
    val hasRead: String?,
    @SerialName("Group")
    val group: String?,
)
