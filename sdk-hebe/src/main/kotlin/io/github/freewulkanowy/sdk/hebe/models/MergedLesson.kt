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
    val visible: Boolean?,
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
        val change: ChangedLesson? = timetableChanges.find { it.change?.id == lesson.change?.id }
        MergedLesson(
            id = lesson.id,
            timeSlot = lesson.timeSlot,
            date = lesson.date,
            changeDate = change?.changeDate,
            subject = change?.subject ?: lesson.subject ?: Subject(name = change?.teacherAbsenceEffectName ?: change?.event ?: change?.reason ?: lesson.event),
            subjectOld = lesson.subject,
            group = lesson.distribution,
            room = change?.room ?: lesson.room,
            roomOld = lesson.room,
            teacherPrimary = change?.teacherPrimary ?: lesson.teacherPrimary,
            teacherSecondary = change?.teacherSecondary ?: lesson.teacherSecondary,
            teacherPrimaryOld = if (change != null) lesson.teacherPrimary else null,
            teacherSecondaryOld = if (change != null) lesson.teacherSecondary else null,
            teacherAbsenceEffectName = change?.teacherAbsenceEffectName,
            reason = change?.reason,
            changes = (change != null),
            canceled = change?.classAbsence,
            visible = lesson.visible
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

