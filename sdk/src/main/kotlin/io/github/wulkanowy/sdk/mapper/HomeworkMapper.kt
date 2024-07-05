package io.github.wulkanowy.sdk.mapper

import io.github.wulkanowy.sdk.pojo.Homework
import io.github.wulkanowy.sdk.pojo.HomeworkAttachment
import io.github.wulkanowy.sdk.scrapper.homework.Homework as ScrapperHomework
import io.github.wulkanowy.sdk.hebe.models.Homework as HebeHomework

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

@JvmName("heveScrapperHomework")
internal fun List<HebeHomework>.mapHomework() = map {
    Homework(
        date = it.dateCreated.date,
        teacher = it.creator.displayName,
        teacherSymbol = "DUPA", //it.teacherSymbol, TODO
        content = it.content,
        subject = it.subject.name,
        entryDate = it.dateCreated.date,
        attachments = it.attachments.map { (url, name) ->
            HomeworkAttachment(url, name)
        },
    )
}
