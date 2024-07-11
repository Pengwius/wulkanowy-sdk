package io.github.freewulkanowy.sdk.scrapper.grades

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GradeRequest(

    @SerialName("okres")
    val semesterId: Int?,
)
