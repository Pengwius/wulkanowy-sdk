package io.github.freewulkanowy.sdk.mapper

import io.github.freewulkanowy.sdk.pojo.Note
import io.github.freewulkanowy.sdk.scrapper.notes.NoteCategory
import java.time.LocalDate
import io.github.freewulkanowy.sdk.scrapper.notes.Note as ScrapperNote
import io.github.freewulkanowy.sdk.hebe.models.Note as HebeNotes

internal fun List<ScrapperNote>.mapNotes() = map {
    Note(
        date = it.date.toLocalDate(),
        teacher = it.teacher,
        teacherSymbol = it.teacherSymbol,
        category = it.category.orEmpty(),
        categoryType = NoteCategory.getByValue(it.categoryType ?: NoteCategory.UNKNOWN.id),
        showPoints = it.showPoints,
        points = it.points.toIntOrNull() ?: 0,
        content = it.content,
    )
}

internal fun List<HebeNotes>.mapHebeNotes() = map {
    Note(
        date = it.dateValid?.date ?: it.dateModify?.date ?: LocalDate.now(),
        teacher = it.creator?.displayName ?: "Nieznany",
        teacherSymbol = it.creator?.displayName?.split(" ")
            ?.mapNotNull { it.firstOrNull()?.uppercase() }
            ?.joinToString("") ?: "",
        category = it.category?.name ?: "Nieznany",
        categoryType = NoteCategory.getByValue(it.category?.id ?: NoteCategory.UNKNOWN.id),
        showPoints = (it.points != null),
        points = it.points ?: 0,
        content = it.content ?: "Nieznany",
    )
}
