package io.github.freewulkanowy.sdk.hebe.models

import java.time.LocalDate
import java.time.LocalDateTime

data class Timetable(
    val headers: List<TimetableDayHeader>,
    val lessons: List<MergedLesson>,
    val additional: List<LessonAdditional>,
)

data class MergedLesson(
    val id: Int?,
    val timeSlot: TimeSlot?,
    val date: Date?,
    val changeDate: Date?,
    val subject: Subject?,
    val subjectOld: Subject?,
    val group: ClassGroup?,
    val room: LessonRoom?,
    val roomOld: LessonRoom?,
    val teacherPrimary: Teacher?,
    val teacherSecondary: Teacher?,
    val teacherPrimaryOld: Teacher?,
    val teacherSecondaryOld: Teacher?,
    val reason: String?,
    val teacherAbsenceEffectName: String?,
    val changes: Boolean?,
    val canceled: Boolean?,
)

data class TimetableDayHeader(
    val date: LocalDate,
    val content: String,
)

data class LessonAdditional(
    val start: LocalDateTime,
    val end: LocalDateTime,
    val date: LocalDate,
    val subject: String,
)

suspend fun mergeTimetable(timetable: List<Lesson>, timetableChanges: List<ChangedLesson>): List<MergedLesson> {
    return timetable.map { lesson ->
        val change: ChangedLesson? = timetableChanges.find { it.lessonDate == lesson.date && it.id == lesson.id }
        MergedLesson(
            id = lesson.id,
            timeSlot = lesson.timeSlot,
            date = lesson.date,
            changeDate = change?.changeDate,
            subject = change?.subject,
            subjectOld = lesson.subject,
            group = lesson.distribution,
            room = change?.room,
            roomOld = lesson.room,
            teacherPrimary = change?.teacherPrimary,
            teacherSecondary = change?.teacherSecondary,
            teacherPrimaryOld = lesson.teacherPrimary,
            teacherSecondaryOld = lesson.teacherSecondary,
            teacherAbsenceEffectName = change?.teacherAbsenceEffectName,
            reason = change?.reason,
            changes = (change != null),
            canceled = false,
        )
    }
}

suspend fun generateDayHeadersAndAdditionals(mergedLessons: List<MergedLesson>): Timetable {
    val dayHeaders = mergedLessons.groupBy { it.date?.date }.map { (date, _) ->
        TimetableDayHeader(
            date = date ?: LocalDate.now(),
            content = "Dzień" // Customize this content as needed
        )
    }

   return Timetable(
       headers = dayHeaders,
       lessons = mergedLessons,
       additional = mutableListOf<LessonAdditional>()
   )
}

