package io.github.freewulkanowy.sdk.hebe.service

import io.github.freewulkanowy.sdk.hebe.models.ApiRequest
import io.github.freewulkanowy.sdk.hebe.models.ApiResponse
import io.github.freewulkanowy.sdk.hebe.register.RegisterRequest
import io.github.freewulkanowy.sdk.hebe.register.RegisterResponse
import io.github.freewulkanowy.sdk.hebe.register.StudentInfo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

internal interface RegisterService {

    @POST("new")
    suspend fun registerDevice(@Body request: ApiRequest<RegisterRequest>): ApiResponse<RegisterResponse>

    @GET("hebe")
    suspend fun getStudentsInfo(): ApiResponse<List<StudentInfo>>
}
