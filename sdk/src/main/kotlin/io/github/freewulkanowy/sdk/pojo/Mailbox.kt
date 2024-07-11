package io.github.freewulkanowy.sdk.pojo

data class Mailbox(
    val globalKey: String,
    val fullName: String,
    val userName: String,
    val studentName: String,
    val schoolNameShort: String,
    val type: MailboxType,
)
