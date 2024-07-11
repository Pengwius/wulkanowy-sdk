package io.github.freewulkanowy.sdk.scrapper.mobile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class UnregisterDeviceRequest(

    @SerialName("id")
    val id: Int,
)
