package io.github.freewulkanowy.sdk.scrapper.service

import io.github.freewulkanowy.sdk.scrapper.login.ADFSFormResponse
import io.github.freewulkanowy.sdk.scrapper.register.HomePageResponse
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

internal interface LoginService {

    @POST("/{symbol}/Account/LogOn")
    @FormUrlEncoded
    suspend fun sendCredentials(@Path("symbol") symbol: String, @Query("ReturnUrl") returnUrl: String, @FieldMap credentials: Map<String, String>): String

    @POST
    @FormUrlEncoded
    suspend fun sendCertificate(@Url url: String, @FieldMap certificate: Map<String, String>): String

    @POST
    @FormUrlEncoded
    suspend fun sendCertificate(@Header("Referer") referer: String, @Url url: String, @FieldMap certificate: Map<String, String>): HomePageResponse

    @GET
    fun getModuleStart(@Url url: String): Call<String>

    @POST
    @FormUrlEncoded
    fun sendCertificateModule(@Header("Referer") referer: String, @Url url: String, @FieldMap certificate: Map<String, String>): Call<String>

    @GET
    suspend fun switchLogin(@Url url: String): HomePageResponse

    @GET
    suspend fun logout(@Url url: String): String

    // ADFS

    @GET
    suspend fun getForm(@Url url: String): ADFSFormResponse

    @POST
    @FormUrlEncoded
    suspend fun sendADFSForm(@Url url: String, @FieldMap values: Map<String, String>): String

    @POST
    @FormUrlEncoded
    suspend fun sendADFSMSForm(@Url url: String, @FieldMap values: Map<String, String>): String
}
