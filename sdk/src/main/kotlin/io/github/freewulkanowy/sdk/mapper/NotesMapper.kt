package io.github.freewulkanowy.sdk.mapper

import io.github.freewulkanowy.sdk.pojo.Note
import io.github.freewulkanowy.sdk.scrapper.notes.NoteCategory
import io.github.freewulkanowy.sdk.scrapper.notes.Note as ScrapperNote

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
