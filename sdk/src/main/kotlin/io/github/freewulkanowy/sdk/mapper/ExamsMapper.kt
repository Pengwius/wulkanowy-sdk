package io.github.freewulkanowy.sdk.mapper

import io.github.freewulkanowy.sdk.pojo.Exam
import java.time.LocalDate
import io.github.freewulkanowy.sdk.hebe.models.Exam as HebeExam
import io.github.freewulkanowy.sdk.scrapper.exams.Exam as ScrapperExam

@JvmName("mapScrapperExams")
internal fun List<ScrapperExam>.mapExams() = map {
    Exam(
        date = it.date.toLocalDate(),
        entryDate = it.entryDate.toLocalDate(),
        description = it.description,
        teacherSymbol = it.teacherSymbol.orEmpty(),
        teacher = it.teacher,
        subject = it.subject,
        type = it.typeName,
    )
}

@JvmName("mapHebeExams")
internal fun List<HebeExam>.mapExams() = map {
    Exam(
        date = it.deadline.date,
        entryDate = it.dateCreated?.date ?: LocalDate.parse("1970-1-1"),
        description = it.content ?: "Nieznany",
        subject = it.subject?.name ?: "Nieznany",
        teacher = it.creator?.displayName ?: "Nieznany",
        teacherSymbol = it.creator?.displayName?.split(" ")
            ?.mapNotNull { it.firstOrNull()?.uppercase() }
            ?.joinToString("") ?: "",
        type = it.type ?: "Nieznany",
    )
}
