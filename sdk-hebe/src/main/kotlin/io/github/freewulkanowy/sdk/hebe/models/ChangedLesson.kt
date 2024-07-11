package io.github.freewulkanowy.sdk.hebe.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChangedLesson (
    @SerialName("Id")
    val id: Int?,
    @SerialName("UnitID")
    val unitId: Int?,
    @SerialName("ScheduleId")
    val scheduleId: Int?,
    @SerialName("LessonDate")
    val lessonDate: Date?,
    @SerialName("Note")
    val note: String?,
    @SerialName("Reason")
    val reason: String?,
    @SerialName("TimeSlot")
    val timeSlot: TimeSlot?,
    @SerialName("Room")
    val room: LessonRoom?,
    @SerialName("TeacherPrimary")
    val teacherPrimary: Teacher?,
    @SerialName("TeacherSecondary")
    val teacherSecondary: Teacher?,
    @SerialName("Subject")
    val subject: Subject?,
    @SerialName("Event")
    val event: String?,
    @SerialName("Change")
    val change: LessonChange?,
    @SerialName("ChangeDate")
    val changeDate: Date?,
    @SerialName("Clazz")
    val clazz: TeamClass?,
    @SerialName("Distribution")
    val distribution: ClassGroup?,
    @SerialName("ClassAbsence")
    val classAbsence: Boolean?,
    @SerialName("TeacherAbsenceEffectName")
    val teacherAbsenceEffectName: String?,
)
