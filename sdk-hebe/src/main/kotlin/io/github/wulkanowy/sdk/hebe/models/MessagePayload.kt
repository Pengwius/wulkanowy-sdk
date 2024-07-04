package io.github.wulkanowy.sdk.hebe.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessagePayload(
    @SerialName("Id")
    val id: String?,
    @SerialName("GlobalKey")
    val globalKey: String?,
    @SerialName("ThreadKey")
    val threadKey: String?,
    @SerialName("Subject")
    val subject: String?,
    @SerialName("Content")
    val content: String?,
    @SerialName("DateSent")
    val dateSent: Date?,
    @SerialName("DateRead")
    val dateRead: Date?,
    @SerialName("Status")
    val status: Int?,
    @SerialName("Sender")
    val sender: Correspondent?,
    @SerialName("Receiver")
    val receiver: List<Correspondent>?,
    @SerialName("Attachments")
    val attachments: List<Attachment>?,
) {
    @Serializable
    data class Correspondent(
        @SerialName("GlobalKey")
        val globalKey: String?,
        @SerialName("Owner")
        val owner: String?,
        @SerialName("HasRead")
        val hasRead: Int?,
    )
}
