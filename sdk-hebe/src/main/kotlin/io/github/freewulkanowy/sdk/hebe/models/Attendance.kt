package io.github.freewulkanowy.sdk.hebe.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attendance(
    @SerialName("LessonId")
    val lessonId: Int?,
    @SerialName("Id")
    val id: Int?,
    @SerialName("LessonNumber")
    val lessonNumber: Int?,
    @SerialName("GlobalKey")
    val globalKey: String?,
    @SerialName("LessonClassId")
    val lessonClassId: Int?,
    @SerialName("LessonClassGlobalKey")
    val lessonClassGlobalKey: String?,
    @SerialName("CalculatePresence")
    val calculatePresence: Boolean?,
    @SerialName("Replacement")
    val replacement: Boolean?,
    @SerialName("Subject")
    val subject: Subject?,
    @SerialName("Topic")
    val topic: String?,
    @SerialName("TeacherPrimary")
    val teacher: Teacher?,
    @SerialName("TeacherSecondary")
    val secondTeacher: Teacher?,
    @SerialName("TeacherMod")
    val mainTeacher: Teacher?,
    @SerialName("Clazz")
    val teamClass: TeamClass?,
    @SerialName("GroupDefinition")
    val classAlias: String?,
    @SerialName("Day")
    val date: Date?,
    @SerialName("TimeSlot")
    val time: TimeSlot?,
    @SerialName("DateModify")
    val dateModified: Date?,
    @SerialName("AuxPresenceId")
    val auxPresenceId: Int?,
    @SerialName("JustificationStatus")
    val justificationStatus: String?,
    @SerialName("PresenceType")
    val presenceType: PresenceType?,
    @SerialName("Note")
    val note: String?,
    @SerialName("PublicResources")
    val publicResources: String?,
    @SerialName("RemoteResources")
    val remoteResources: String?,
    @SerialName("Distribution")
    val group: ClassGroup?,
    @SerialName("Visible")
    val visible: Boolean?,
)
