package io.github.wulkanowy.sdk.hebe.repository

import io.github.wulkanowy.sdk.hebe.getEnvelopeOrThrowError
import io.github.wulkanowy.sdk.hebe.models.Address
import io.github.wulkanowy.sdk.hebe.models.ApiRequest
import io.github.wulkanowy.sdk.hebe.models.Attendance
import io.github.wulkanowy.sdk.hebe.models.ChangedLesson
import io.github.wulkanowy.sdk.hebe.models.Exam
import io.github.wulkanowy.sdk.hebe.models.Grade
import io.github.wulkanowy.sdk.hebe.models.GradeAverage
import io.github.wulkanowy.sdk.hebe.models.GradeSummary
import io.github.wulkanowy.sdk.hebe.models.Homework
import io.github.wulkanowy.sdk.hebe.models.Lesson
import io.github.wulkanowy.sdk.hebe.models.LuckyNumber
import io.github.wulkanowy.sdk.hebe.models.Message
import io.github.wulkanowy.sdk.hebe.models.MessageBox
import io.github.wulkanowy.sdk.hebe.models.MessagePayload
import io.github.wulkanowy.sdk.hebe.service.StudentService
import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal class StudentRepository(private val studentService: StudentService) {

    suspend fun getGrades(pupilId: Int, periodId: Int): List<Grade> {
        return studentService.getGrades(
            createQueryMap(pupilId = pupilId, periodId = periodId),
        ).getEnvelopeOrThrowError().orEmpty()
    }

    suspend fun getGradesSummary(pupilId: Int, periodId: Int): List<GradeSummary> {
        return studentService.getGradesSummary(
            createQueryMap(pupilId = pupilId, periodId = periodId),
        ).getEnvelopeOrThrowError().orEmpty()
    }

    suspend fun getGradesAverage(pupilId: Int, periodId: Int): List<GradeAverage> {
        return studentService.getGradesAverage(
            createQueryMap(pupilId = pupilId, periodId = periodId),
        ).getEnvelopeOrThrowError().orEmpty()
    }

    suspend fun getExams(pupilId: Int, startDate: LocalDate, endDate: LocalDate): List<Exam> {
        return studentService.getExams(
            createQueryMap(pupilId = pupilId, dateFrom = startDate),
        ).getEnvelopeOrThrowError().orEmpty().filter {
            it.deadline.date in startDate..endDate
        }
    }

    suspend fun getHomeworks(pupilId: Int, dateFrom: LocalDate, dateTo: LocalDate): List<Homework> {
        return studentService.getHomeworks(
            createQueryMap(pupilId = pupilId, dateFrom = dateFrom, dateTo = dateTo)
        ).getEnvelopeOrThrowError().orEmpty()
    }

    suspend fun getTimetable(pupilId: Int, dateFrom: LocalDate, dateTo: LocalDate): List<Lesson> {
        return studentService.getTimetable(
            createQueryMap(pupilId = pupilId, dateFrom = dateFrom, dateTo = dateTo)
        ).getEnvelopeOrThrowError().orEmpty()
    }

    suspend fun getTimetableChanges(pupilId: Int, dateFrom: LocalDate, dateTo: LocalDate): List<ChangedLesson> {
        return studentService.getTimetableChanges(
            createQueryMap(pupilId = pupilId, dateFrom = dateFrom, dateTo = dateTo)
        ).getEnvelopeOrThrowError().orEmpty()
    }

    suspend fun getAttendance(pupilId: Int, dateFrom: LocalDate, dateTo: LocalDate): List<Attendance> {
        return studentService.getAttendance(
            createQueryMap(pupilId = pupilId, dateFrom = dateFrom, dateTo = dateTo)
        ).getEnvelopeOrThrowError().orEmpty()
    }

    suspend fun getLuckyNumber(constituentId: String, day: LocalDate): LuckyNumber {
        val response = studentService.getLuckyNumber(
            createQueryMap(constituentId = constituentId, day = day)
        ).getEnvelopeOrThrowError()
        return response ?: LuckyNumber("0", "0")
    }

    suspend fun getMessageBoxes(): List<MessageBox> {
        return studentService.getMessageBoxes(
            createQueryMap()
        ).getEnvelopeOrThrowError().orEmpty()
    }

    suspend fun getAddressBook(messageBox: String): List<Address> {
        return studentService.getAddressBook(
            createQueryMap(box = messageBox)
        ).getEnvelopeOrThrowError().orEmpty()
    }

    suspend fun getMessages(messageBox: String, folder: Int): List<Message> {
        return studentService.getMessages(
            createQueryMap(box = messageBox, folder = folder)
        ).getEnvelopeOrThrowError().orEmpty()
    }

    suspend fun sendMessage(payload: ApiRequest<MessagePayload>): MessagePayload {
        return studentService.sendMessage(payload)
            .getEnvelopeOrThrowError()!!
    }

    private fun createQueryMap(
        pupilId: Int? = null,
        periodId: Int? = null,
        loginId: Int? = null,
        dateFrom: LocalDate? = null,
        dateTo: LocalDate? = null,
        box: String? = null,
        folder: Int? = null,
        constituentId: String? = null,
        day: LocalDate? = null,
    ): Map<String, Any?> = mapOf(
        "pupilId" to pupilId,
        "periodId" to periodId,
        "loginId" to loginId,
        "lastSyncDate" to "1970-01-01 01:00:00",
        "lastId" to Int.MIN_VALUE,
        "pageSize" to 500,
        "dateFrom" to dateFrom?.format(DateTimeFormatter.ISO_DATE),
        "dateTo" to dateTo?.format(DateTimeFormatter.ISO_DATE),
        "box" to box,
        "folder" to folder,
        "constituentId" to constituentId,
        "day" to day?.format(DateTimeFormatter.ISO_DATE),
    ).filterValues { it != null }
}
