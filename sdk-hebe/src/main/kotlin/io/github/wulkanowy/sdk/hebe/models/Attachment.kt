package io.github.wulkanowy.sdk.hebe.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attachment(
    @SerialName("Name")
    val name: String,
    @SerialName("Link")
    val link: String,
)

