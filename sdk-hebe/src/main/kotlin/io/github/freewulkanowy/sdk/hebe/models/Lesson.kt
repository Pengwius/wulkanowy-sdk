package io.github.freewulkanowy.sdk.hebe.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Lesson (
    @SerialName("Id")
    val id: Int?,
    @SerialName("Date")
    val date: Date?,
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
    @SerialName("Clazz")
    val clazz: TeamClass?,
    @SerialName("PupilAlias")
    val pupilAlias: String?,
    @SerialName("Distribution")
    val distribution: ClassGroup?,
    @SerialName("Visible")
    val visible: Boolean?,
)
