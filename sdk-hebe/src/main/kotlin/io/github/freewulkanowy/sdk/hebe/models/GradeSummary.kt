package io.github.freewulkanowy.sdk.hebe.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GradeSummary(
    @SerialName("DateModify")
    val dateModify: Date,
    @SerialName("Entry_1")
    val entry1: String? = null,
    @SerialName("Entry_2")
    val entry2: String? = null,
    // @SerialName("Entry_3")
    // val entry3: Any?,
    @SerialName("Id")
    val id: Int,
    @SerialName("PeriodId")
    val periodId: Int,
    @SerialName("PupilId")
    val pupilId: Int,
    @SerialName("Subject")
    val subject: Subject,
)
