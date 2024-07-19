package io.github.freewulkanowy.sdk.hebe.service

import io.github.freewulkanowy.sdk.hebe.models.ApiResponse
import io.github.freewulkanowy.sdk.hebe.models.Address
import io.github.freewulkanowy.sdk.hebe.models.ApiRequest
import io.github.freewulkanowy.sdk.hebe.models.Attendance
import io.github.freewulkanowy.sdk.hebe.models.ChangedLesson
import io.github.freewulkanowy.sdk.hebe.models.Exam
import io.github.freewulkanowy.sdk.hebe.models.Grade
import io.github.freewulkanowy.sdk.hebe.models.GradeAverage
import io.github.freewulkanowy.sdk.hebe.models.GradeSummary
import io.github.freewulkanowy.sdk.hebe.models.Homework
import io.github.freewulkanowy.sdk.hebe.models.Lesson
import io.github.freewulkanowy.sdk.hebe.models.LuckyNumber
import io.github.freewulkanowy.sdk.hebe.models.Message
import io.github.freewulkanowy.sdk.hebe.models.MessageBox
import io.github.freewulkanowy.sdk.hebe.models.MessagePayload
import io.github.freewulkanowy.sdk.hebe.models.Note
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

@JvmSuppressWildcards
internal interface StudentService {

    @GET("api/mobile/grade/byPupil")
    suspend fun getGrades(@QueryMap query: Map<String, Any?>): ApiResponse<List<Grade>>

    @GET("api/mobile/grade/summary/byPupil")
    suspend fun getGradesSummary(@QueryMap query: Map<String, Any?>): ApiResponse<List<GradeSummary>>

    @GET("api/mobile/grade/average/byPupil")
    suspend fun getGradesAverage(@QueryMap query: Map<String, Any?>): ApiResponse<List<GradeAverage>>

    @GET("api/mobile/exam/byPupil")
    suspend fun getExams(@QueryMap query: Map<String, Any?>): ApiResponse<List<Exam>>

    @GET("api/mobile/homework/byPupil")
    suspend fun getHomeworks(@QueryMap query: Map<String, Any?>): ApiResponse<List<Homework>>

    @GET("api/mobile/schedule/byPupil")
    suspend fun getTimetable(@QueryMap query: Map<String, Any?>): ApiResponse<List<Lesson>>

    @GET("api/mobile/schedule/changes/byPupil")
    suspend fun getTimetableChanges(@QueryMap query: Map<String, Any?>): ApiResponse<List<ChangedLesson>>

    @GET("api/mobile/lesson/byPupil")
    suspend fun getAttendance(@QueryMap query: Map<String, Any?>): ApiResponse<List<Attendance>>

    @GET("api/mobile/school/lucky")
    suspend fun getLuckyNumber(@QueryMap query: Map<String, Any?>): ApiResponse<LuckyNumber>

    @GET("api/mobile/messagebox")
    suspend fun getMessageBoxes(@QueryMap query: Map<String, Any?>): ApiResponse<List<MessageBox>>

    @GET("api/mobile/messagebox/addressbook")
    suspend fun getAddressBook(@QueryMap query: Map<String, Any?>): ApiResponse<List<Address>>

    @GET("api/mobile/messagebox/message/byBox")
    suspend fun getMessages(@QueryMap query: Map<String, Any?>): ApiResponse<List<Message>>

    @POST("api/mobile/messagebox/message")
    suspend fun sendMessage(@Body payload: ApiRequest<MessagePayload>): ApiResponse<MessagePayload>

    @GET("api/mobile/note/byPupil")
    suspend fun getNotes(@QueryMap query: Map<String, Any?>): ApiResponse<List<Note>>
}
