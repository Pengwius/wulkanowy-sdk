package io.github.freewulkanowy.sdk.hebe

import io.github.freewulkanowy.sdk.hebe.models.Address
import io.github.freewulkanowy.sdk.hebe.models.ApiRequest
import io.github.freewulkanowy.sdk.hebe.models.ApiResponse
import io.github.freewulkanowy.sdk.hebe.models.Attendance
import io.github.freewulkanowy.sdk.hebe.models.ChangedLesson
import io.github.freewulkanowy.sdk.hebe.models.Exam
import io.github.freewulkanowy.sdk.hebe.models.Grade
import io.github.freewulkanowy.sdk.hebe.models.GradeAverage
import io.github.freewulkanowy.sdk.hebe.models.GradeSummary
import io.github.freewulkanowy.sdk.hebe.models.Homework
import io.github.freewulkanowy.sdk.hebe.models.Lesson
import io.github.freewulkanowy.sdk.hebe.models.LessonAdditional
import io.github.freewulkanowy.sdk.hebe.models.LuckyNumber
import io.github.freewulkanowy.sdk.hebe.models.MergedLesson
import io.github.freewulkanowy.sdk.hebe.models.Message
import io.github.freewulkanowy.sdk.hebe.models.MessageBox
import io.github.freewulkanowy.sdk.hebe.models.MessagePayload
import io.github.freewulkanowy.sdk.hebe.models.Timetable
import io.github.freewulkanowy.sdk.hebe.models.TimetableDayHeader
import io.github.freewulkanowy.sdk.hebe.models.generateDayHeadersAndAdditionals
import io.github.freewulkanowy.sdk.hebe.models.mergeTimetable
import io.github.freewulkanowy.sdk.hebe.register.RegisterDevice
import io.github.freewulkanowy.sdk.hebe.register.StudentInfo
import io.github.freewulkanowy.sdk.hebe.repository.RepositoryManager
import io.github.wulkanowy.signer.hebe.generateKeyPair
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import java.time.LocalDate

class Hebe {

    private val resettableManager = resettableManager()

    var logLevel = HttpLoggingInterceptor.Level.BASIC
        set(value) {
            field = value
            resettableManager.reset()
        }

    var keyId = ""
        set(value) {
            field = value
            resettableManager.reset()
        }

    var privatePem = ""
        set(value) {
            field = value
            resettableManager.reset()
        }

    var baseUrl = ""
        set(value) {
            field = value
            resettableManager.reset()
        }

    var schoolId = ""
        set(value) {
            field = value
            resettableManager.reset()
        }

    var pupilId = -1
        set(value) {
            field = value
            resettableManager.reset()
        }

    var loginId = -1
        set(value) {
            field = value
            resettableManager.reset()
        }

    var deviceModel = ""
        set(value) {
            field = value
            resettableManager.reset()
        }

    private val appInterceptors: MutableList<Pair<Interceptor, Boolean>> = mutableListOf()

    fun addInterceptor(interceptor: Interceptor, network: Boolean = false) {
        appInterceptors.add(interceptor to network)
    }

    private val serviceManager by resettableLazy(resettableManager) {
        RepositoryManager(
            logLevel = logLevel,
            keyId = keyId,
            privatePem = privatePem,
            deviceModel = deviceModel,
        ).apply {
            appInterceptors.forEach { (interceptor, isNetwork) ->
                setInterceptor(interceptor, isNetwork)
            }
        }
    }

    private val routes by resettableLazy(resettableManager) { serviceManager.getRoutesRepository() }

    private val studentRepository by resettableLazy(resettableManager) {
        serviceManager.getStudentRepository(
            baseUrl = baseUrl,
            schoolId = schoolId,
        )
    }

    suspend fun register(token: String, pin: String, symbol: String, firebaseToken: String? = null): RegisterDevice {
        val (publicPem, privatePem, publicHash) = generateKeyPair()

        this.keyId = publicHash
        this.privatePem = privatePem

        val envelope = serviceManager.getRegisterRepository(
            baseUrl = routes.getRouteByToken(token),
            symbol = symbol,
        ).register(
            firebaseToken = firebaseToken,
            token = token,
            pin = pin,
            certificatePem = publicPem,
            certificateId = publicHash,
            deviceModel = deviceModel,
        )

        return RegisterDevice(
            loginId = envelope.loginId,
            restUrl = envelope.restUrl,
            userLogin = envelope.userLogin,
            userName = envelope.userName,
            certificateHash = publicHash,
            privatePem = privatePem,
        )
    }

    suspend fun getStudents(url: String): List<StudentInfo> {
        return serviceManager
            .getRegisterRepository(url)
            .getStudentInfo()
    }

    suspend fun getGrades(periodId: Int): List<Grade> {
        return studentRepository.getGrades(
            pupilId = pupilId,
            periodId = periodId,
        )
    }

    suspend fun getGradesSummary(periodId: Int): List<GradeSummary> {
        return studentRepository.getGradesSummary(
            pupilId = pupilId,
            periodId = periodId,
        )
    }

    suspend fun getGradesAverage(periodId: Int): List<GradeAverage> {
        return studentRepository.getGradesAverage(
            pupilId = pupilId,
            periodId = periodId,
        )
    }

    suspend fun getExams(startDate: LocalDate, endDate: LocalDate): List<Exam> {
        return studentRepository.getExams(
            pupilId = pupilId,
            startDate = startDate,
            endDate = endDate,
        )
    }

    suspend fun getHomeworks(): List<Homework> {
        return studentRepository.getHomeworks(
            pupilId = pupilId,
        )
    }

    suspend fun getTimetable(dateFrom: LocalDate, dateTo: LocalDate): List<Lesson> {
        return studentRepository.getTimetable(
            pupilId = pupilId,
            dateFrom = dateFrom,
            dateTo = dateTo,
        )
    }

    suspend fun getTimetableChanges(dateFrom: LocalDate, dateTo: LocalDate): List<ChangedLesson> {
        return studentRepository.getTimetableChanges(
            pupilId = pupilId,
            dateFrom = dateFrom,
            dateTo = dateTo,
        )
    }

    suspend fun getMergedTimetable(dateFrom: LocalDate, dateTo: LocalDate): Timetable {
        val timetable = studentRepository.getTimetable(
            pupilId = pupilId,
            dateFrom = dateFrom,
            dateTo = dateTo,
        )

        val timetableChanges = studentRepository.getTimetableChanges(
            pupilId = pupilId,
            dateFrom = dateFrom,
            dateTo = dateTo,
        )

        val mergedLessons = mergeTimetable(timetable, timetableChanges)
            .filter { it.visible ?: true }

        return generateDayHeadersAndAdditionals(mergedLessons)
    }

    suspend fun getAttendance(dateFrom: LocalDate, dateTo: LocalDate): List<Attendance> {
        return studentRepository.getAttendance(
            pupilId = pupilId,
            dateFrom = dateFrom,
            dateTo = dateTo,
        )
    }

    suspend fun getLuckyNumber(day: LocalDate): LuckyNumber {
        return studentRepository.getLuckyNumber(
            constituentId = schoolId,
            day = day,
        )
    }

    suspend fun getMessageBoxes(): List<MessageBox> {
        return studentRepository.getMessageBoxes()
    }

    suspend fun getAddressBook(messageBox: String): List<Address> {
        return studentRepository.getAddressBook(
            messageBox = messageBox,
        )
    }

    suspend fun getMessages(messageBox: String, folder: Int): List<Message> {
        return studentRepository.getMessages(
            messageBox = messageBox,
            folder = folder,
        )
    }

    suspend fun sendMessage(payload: ApiRequest<MessagePayload>): MessagePayload {
        return studentRepository.sendMessage(payload)
    }
}
