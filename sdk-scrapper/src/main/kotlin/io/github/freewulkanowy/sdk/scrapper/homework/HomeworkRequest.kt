package io.github.freewulkanowy.sdk.scrapper.homework

import io.github.freewulkanowy.sdk.scrapper.adapter.CustomDateAdapter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
internal data class HomeworkRequest(

    @SerialName("date")
    @Serializable(with = CustomDateAdapter::class)
    val date: LocalDateTime,

    @SerialName("schoolYear")
    val schoolYear: Int,

    @SerialName("statusFilter")
    val statusFilter: Int,
)
