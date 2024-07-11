package io.github.freewulkanowy.sdk.scrapper.attendance

import io.github.freewulkanowy.sdk.scrapper.adapter.CustomDateAdapter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
internal data class AttendanceRequest(

    @SerialName("data")
    @Serializable(with = CustomDateAdapter::class)
    val date: LocalDateTime,

    @SerialName("idTypWpisuFrekwencji")
    val typeId: Int = -1,
)

@Serializable
internal data class AttendanceRecordsRequest(

    @SerialName("miesiac")
    val month: Int,
)
