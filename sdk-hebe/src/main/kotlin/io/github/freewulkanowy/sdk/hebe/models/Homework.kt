package io.github.freewulkanowy.sdk.hebe.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Homework(
    @SerialName("Id")
    val id: Int?,
    @SerialName("Key")
    val key: String?,
    @SerialName("IdHomework")
    val homeworkId: Int?,
    @SerialName("Content")
    val content: String?,
    @SerialName("DateCreated")
    val dateCreated: Date?,
    @SerialName("Creator")
    val creator: Teacher?,
    @SerialName("Subject")
    val subject: Subject?,
    @SerialName("Attachments")
    val attachments: List<Attachment>?,
    @SerialName("IsAnswerRequired")
    val isAnswerRequired: Boolean?,
    @SerialName("Deadline")
    val deadline: Date?,
    @SerialName("AnswerDeadline")
    val answerDeadline: Date?,
    @SerialName("AnswerDate")
    val answerDate: Date?,
)
