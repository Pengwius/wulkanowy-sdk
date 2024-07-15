package io.github.freewulkanowy.sdk.mapper

import io.github.freewulkanowy.sdk.pojo.CompletedLesson
import io.github.freewulkanowy.sdk.pojo.Lesson as PojoLesson
import io.github.freewulkanowy.sdk.pojo.LessonAdditional
import io.github.freewulkanowy.sdk.pojo.Timetable
import io.github.freewulkanowy.sdk.pojo.TimetableDayHeader
import io.github.freewulkanowy.sdk.toLocalDateTime
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import io.github.freewulkanowy.sdk.scrapper.timetable.CompletedLesson as ScrapperCompletedLesson
import io.github.freewulkanowy.sdk.scrapper.timetable.Lesson as ScrapperTimetable
import io.github.freewulkanowy.sdk.scrapper.timetable.LessonAdditional as ScrapperTimetableAdditional
import io.github.freewulkanowy.sdk.scrapper.timetable.Timetable as ScrapperTimetableFull
import io.github.freewulkanowy.sdk.scrapper.timetable.TimetableDayHeader as ScrapperTimetableDayHeader
import io.github.freewulkanowy.sdk.hebe.models.MergedLesson as HebeMergedLesson
import io.github.freewulkanowy.sdk.hebe.models.Timetable as HebeTimetableFull
import io.github.freewulkanowy.sdk.hebe.models.TimetableDayHeader as HebeTimetableDayHeader
import io.github.freewulkanowy.sdk.hebe.models.LessonAdditional as HebeLessonAdditional

internal fun ScrapperTimetableFull.mapTimetableFull(zoneId: ZoneId) = Timetable(
    headers = headers.mapTimetableDayHeaders(),
    lessons = lessons.mapTimetable(zoneId),
    additional = additional.mapTimetableAdditional(zoneId),
)

internal fun HebeTimetableFull.mapHebeTimetableFull(zoneId: ZoneId) = Timetable(
    headers = headers.mapHebeTimetableDayHeaders(),
    lessons = lessons.mapHebeTimetable(zoneId),
    additional = additional.mapHebeTimetableAdditional(zoneId),
)

internal fun List<ScrapperTimetable>.mapTimetable(zoneId: ZoneId) = map {
    PojoLesson(
        canceled = it.canceled,
        changes = it.changes,
        date = it.date,
        start = it.start.atZone(zoneId),
        end = it.end.atZone(zoneId),
        group = it.group,
        info = it.info,
        number = it.number,
        room = it.room,
        roomOld = it.roomOld,
        subject = it.subject,
        subjectOld = it.subjectOld,
        studentPlan = true,
        teacher = it.teacher,
        teacherOld = it.teacherOld,
    )
}

internal fun List<HebeMergedLesson>.mapHebeTimetable(zoneId: ZoneId) = map {
    PojoLesson(
        canceled = it.canceled ?: false,
        changes = it.changes ?: false,
        date = it.date?.date ?: LocalDate.now(),
        start = parseTimeToDateTime(it.timeSlot!!.start ?: "00:00").atZone(zoneId),
        end = parseTimeToDateTime(it.timeSlot!!.end ?: "00:00").atZone(zoneId),
        group = it.group?.name ?: "",
        info = "${it.reason ?: ""} ${it.teacherAbsenceEffectName ?: ""}",
        number = it.timeSlot?.position ?: 0,
        room = it.room?.code ?: "",
        roomOld = it.roomOld?.code ?: "",
        subject = it.subject?.name ?: "",
        subjectOld = it.subjectOld?.name ?: "",
        studentPlan = true,
        teacher = it.teacherPrimary?.displayName ?: "",
        teacherOld = it.teacherPrimaryOld?.displayName ?: "",
    )
}

internal fun List<ScrapperTimetableDayHeader>.mapTimetableDayHeaders() = map {
    TimetableDayHeader(
        date = it.date,
        content = it.content,
    )
}

internal fun List<HebeTimetableDayHeader>.mapHebeTimetableDayHeaders() = map {
    TimetableDayHeader(
        date = it.date,
        content = it.content,
    )
}

internal fun List<ScrapperTimetableAdditional>.mapTimetableAdditional(zoneId: ZoneId) = map {
    LessonAdditional(
        subject = it.subject,
        date = it.date,
        start = it.start.atZone(zoneId),
        end = it.end.atZone(zoneId),
    )
}

internal fun List<HebeLessonAdditional>.mapHebeTimetableAdditional(zoneId: ZoneId) = map {
    LessonAdditional(
        subject = it.subject,
        date = it.date,
        start = it.start.atZone(zoneId),
        end = it.end.atZone(zoneId),
    )
}

internal fun List<ScrapperCompletedLesson>.mapCompletedLessons() = map {
    CompletedLesson(
        date = it.date.toLocalDate(),
        number = it.number,
        subject = it.subject.orEmpty(),
        topic = it.topic.orEmpty(),
        teacher = it.teacher.orEmpty(),
        teacherSymbol = it.teacherSymbol.orEmpty(),
        substitution = it.substitution.orEmpty(),
        absence = it.absence.orEmpty(),
        resources = it.resources.orEmpty(),
    )
}

fun parseTimeToDateTime(timeString: String, date: LocalDate = LocalDate.now()): LocalDateTime {
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val parsedTime = LocalTime.parse(timeString, timeFormatter)
    return LocalDateTime.of(date, parsedTime)
}
