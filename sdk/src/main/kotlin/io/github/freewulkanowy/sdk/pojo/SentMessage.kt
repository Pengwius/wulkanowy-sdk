package io.github.freewulkanowy.sdk.pojo

data class SentMessage(
    val recipients: List<Recipient>,
    val subject: String,
    val content: String,
    val sender: Sender,
    val isWelcomeMessage: Boolean,
    val id: Int,
)
