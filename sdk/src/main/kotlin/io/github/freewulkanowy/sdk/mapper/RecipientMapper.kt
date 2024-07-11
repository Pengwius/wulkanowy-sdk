package io.github.freewulkanowy.sdk.mapper

import io.github.freewulkanowy.sdk.pojo.MailboxType
import io.github.freewulkanowy.sdk.pojo.Recipient
import io.github.freewulkanowy.sdk.scrapper.messages.Recipient as ScrapperRecipient

internal fun List<ScrapperRecipient>.mapRecipients() = map {
    it.mapToRecipient()
}

internal fun ScrapperRecipient.mapToRecipient() = Recipient(
    mailboxGlobalKey = mailboxGlobalKey,
    fullName = fullName,
    userName = userName,
    studentName = studentName,
    schoolNameShort = schoolNameShort,
    type = MailboxType.fromLetter(type.letter),
)
