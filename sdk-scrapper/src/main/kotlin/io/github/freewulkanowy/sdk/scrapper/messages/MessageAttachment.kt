package io.github.freewulkanowy.sdk.scrapper.messages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageAttachment(

    @SerialName("url")
    val url: String,

    @SerialName("nazwaPliku")
    val filename: String,
)
