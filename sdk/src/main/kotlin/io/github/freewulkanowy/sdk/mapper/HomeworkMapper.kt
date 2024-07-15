package io.github.freewulkanowy.sdk.mapper

import io.github.freewulkanowy.sdk.pojo.Homework
import io.github.freewulkanowy.sdk.pojo.HomeworkAttachment
import java.time.LocalDate
import io.github.freewulkanowy.sdk.scrapper.homework.Homework as ScrapperHomework
import io.github.freewulkanowy.sdk.hebe.models.Homework as HebeHomework

@JvmName("mapScrapperHomework")
internal fun List<ScrapperHomework>.mapHomework() = map {
    Homework(
        date = it.date.toLocalDate(),
        teacher = it.teacher,
        teacherSymbol = it.teacherSymbol,
        content = it.content,
        subject = it.subject,
        entryDate = it.entryDate.toLocalDate(),
        attachments = it._attachments.map { (url, name) ->
            HomeworkAttachment(url, name)
        },
    )
}

@JvmName("mapHebeHomework")
internal fun List<HebeHomework>.mapHomework() = map {
    Homework(
        date = it.dateCreated?.date ?: LocalDate.parse("1970-1-1"),
        teacher = it.creator?.displayName ?: "Nieznany",
        teacherSymbol = it.creator?.displayName?.split(" ")
            ?.mapNotNull { it.firstOrNull()?.uppercase() }
            ?.joinToString("") ?: "",
        content = it.content ?: "Nieznany",
        subject = it.subject?.name ?: "Nieznany",
        entryDate = it.dateCreated?.date ?: LocalDate.parse("1970-1-1"),
        attachments = it.attachments?.map { (url, name) ->
            HomeworkAttachment(url ?: "", name ?: "Nieznany")
        } ?: emptyList(),
    )
}
