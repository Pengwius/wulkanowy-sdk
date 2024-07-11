package io.github.freewulkanowy.sdk.hebe

import io.github.freewulkanowy.sdk.hebe.models.ApiRequest
import io.github.freewulkanowy.sdk.hebe.models.LuckyNumber
import io.github.freewulkanowy.sdk.hebe.models.MessagePayload
import kotlinx.coroutines.test.runTest
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.UUID
import kotlin.reflect.typeOf

class HebeRemoteTest {

    private val hebe = Hebe()

    @Before
    fun setUp() {
        with(hebe) {
            logLevel = HttpLoggingInterceptor.Level.BODY
            keyId = "///"
            privatePem = "///"
            baseUrl = "https://lekcjaplus.vulcan.net.pl/nowysacz"
            pupilId = 0
            schoolId = "00000"
            loginId = 0
            /*
                wszystko znajdziesz gdzieś tam w logach
             */
            deviceModel = "Freewulkanowy"
        }
    }

    @Ignore
    @Test
    fun `register device`() = runTest {
        val res = hebe.register(
            firebaseToken = "",
            token = "",
            pin = "",
            symbol = "",
        )

        print(res.privatePem)
        assertTrue(res.privatePem.isNotEmpty())
    }

    @Test
    fun `get students`() = runTest {
        val res = hebe.getStudents(hebe.baseUrl)
        assertTrue(res.isNotEmpty())
    }

    @Test
    fun `get grades`() = runTest {
        val grades = hebe.getGrades(2137)
        print(grades)
        assertTrue(grades.isNotEmpty())
    }

    @Test
    fun `get grades summary`() = runTest {
        val summaries = hebe.getGradesSummary(2137)
        assertTrue(summaries.isNotEmpty())
    }

    @Test
    fun `get grades average`() = runTest {
        val averages = hebe.getGradesAverage(2137)
        assertTrue(averages.isNotEmpty())
    }

    @Test
    fun `get exams`() = runTest {
        val exams = hebe.getExams(LocalDate.of(2023, 4, 1), LocalDate.of(2023, 5, 1))
        assertTrue(exams.isNotEmpty())
    }

    @Test
    fun `get homeworks`() = runTest {
        val homeworks = hebe.getHomeworks()
        assertTrue(homeworks.isNotEmpty())
    }

    @Test
    fun `get timetable`() = runTest {
        val timetable = hebe.getTimetable(LocalDate.of(2024, 20, 5), LocalDate.of(2024, 27, 5))
        assertTrue(timetable.isNotEmpty())
    }

    @Test
    fun `get timetable changes`() = runTest {
        val timetableChanges = hebe.getTimetableChanges(LocalDate.of(2024, 4, 1), LocalDate.of(2024, 5, 1))
        assertTrue(timetableChanges.isNotEmpty())
    }

    @Test
    fun `get merged timetable`() = runTest {
        val mergedTimetable = hebe.getMergedTimetable(LocalDate.of(2024, 4, 1), LocalDate.of(2024, 5, 1))
        print(mergedTimetable)
        assertTrue(mergedTimetable != null)
    }

    @Test
    fun `get attendance`() = runTest {
        val attendance = hebe.getAttendance(LocalDate.of(2024, 4, 1), LocalDate.of(2024, 5, 1))
        assertTrue(attendance.isNotEmpty())
    }

    @Test
    fun `get lucky number`() = runTest {
        val luckyNumber = hebe.getLuckyNumber(LocalDate.of(2024, 4, 1))
        print("${luckyNumber}\n") // ja nie mam szczęśliwego numerka w szkole to stestować nie moge, zwraca mi 1970-1-1
        assertTrue(luckyNumber is LuckyNumber)
    }

    @Test
    fun `get message boxes`() = runTest {
        val messageBox = hebe.getMessageBoxes()
        assertTrue(messageBox.isNotEmpty())
    }

    @Test
    fun `get address book`() = runTest {
        val addressBook = hebe.getAddressBook("") //twój global key tutaj
        assertTrue(addressBook.isNotEmpty())
    }

    @Test
    fun `get messages`() = runTest {
        val messages = hebe.getMessages("", 1) //twój global key tutaj
        assertTrue(messages.isNotEmpty())
        /*
            folder to cyferka 1, 2, lub 3:
                1: odebrane
                2: wysłane
                3: usunięte
         */
    }

    @Test
    fun `send message`() = runTest {
        val envelopeGlobalKey = UUID.randomUUID().toString()

        val payload: ApiRequest<MessagePayload> = ApiRequest(
            certificateId = hebe.keyId,
            envelope = MessagePayload(
                id = UUID.randomUUID().toString(),
                globalKey = envelopeGlobalKey,
                threadKey = envelopeGlobalKey,
                subject = "Testowy subject 2",
                content = "Testowy content 2",
                dateSent = null,
                dateRead = null,
                status = null,
                sender = MessagePayload.Correspondent(
                    globalKey = "", //twój global key
                    owner = "", //twój global key
                    hasRead = null,
                ),
                receiver = listOf(
                    MessagePayload.Correspondent(
                        globalKey = "", //global key odbiorcy
                        owner = "", //twój global key
                        hasRead = null,
                    ),
                ),
                attachments = listOf(),
            ),
            firebaseToken = "",
        )

        val response = hebe.sendMessage(payload)
        print(response)
        assertTrue(response.sender?.globalKey == "") //tu daj swój global key
    }
}
