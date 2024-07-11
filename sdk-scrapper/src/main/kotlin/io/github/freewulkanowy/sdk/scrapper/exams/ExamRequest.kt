package io.github.freewulkanowy.sdk.scrapper.exams

import io.github.freewulkanowy.sdk.scrapper.adapter.CustomDateAdapter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
internal data class ExamRequest(

    @SerialName("data")
    @Serializable(with = CustomDateAdapter::class)
    val date: LocalDateTime,

    @SerialName("rokSzkolny")
    val schoolYear: Int,
)
