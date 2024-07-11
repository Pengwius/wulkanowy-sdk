package io.github.freewulkanowy.sdk.mapper

import io.github.freewulkanowy.sdk.pojo.Mailbox
import io.github.freewulkanowy.sdk.pojo.MailboxType
import io.github.freewulkanowy.sdk.pojo.Mailbox as SdkMailbox
import io.github.freewulkanowy.sdk.scrapper.messages.Mailbox as ScrapperMailbox

internal fun List<ScrapperMailbox>.mapMailboxes(): List<Mailbox> {
    return map {
        SdkMailbox(
            globalKey = it.globalKey,
            fullName = it.fullName,
            userName = it.userName,
            schoolNameShort = it.schoolNameShort,
            studentName = it.studentName,
            type = MailboxType.fromLetter(it.type.letter),
        )
    }
}
