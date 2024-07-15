package io.github.freewulkanowy.sdk.hebe.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Exam(
    @SerialName("Content")
    val content: String?,
    @SerialName("Creator")
    val creator: Teacher?,
    @SerialName("DateCreated")
    val dateCreated: Date?,
    @SerialName("DateModify")
    val dateModify: Date?,
    @SerialName("Deadline")
    val deadline: Date,
    @SerialName("Id")
    val id: Int?,
    @SerialName("Key")
    val key: String?,
    @SerialName("PupilId")
    val pupilId: Int?,
    @SerialName("Subject")
    val subject: Subject?,
    @SerialName("Type")
    val type: String?,
)
