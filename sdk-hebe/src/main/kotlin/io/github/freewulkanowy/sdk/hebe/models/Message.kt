package io.github.freewulkanowy.sdk.hebe.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Message(
    @SerialName("Id")
    val id: String,
    @SerialName("GlobalKey")
    val globalKey: String?,
    @SerialName("ThreadKey")
    val threadKey: String?,
    @SerialName("Subject")
    val subject: String?,
    @SerialName("Content")
    val content: String?,
    @SerialName("DateSent")
    val sentDate: Date?,
    @SerialName("Status")
    val status: Int?,
    @SerialName("Sender")
    val sender: Address?,
    @SerialName("Receiver")
    val receivers: List<Address>?,
    @SerialName("Attachments")
    val attachments: List<Attachment>?,
    @SerialName("DateRead")
    val readDate: Date?,
)
