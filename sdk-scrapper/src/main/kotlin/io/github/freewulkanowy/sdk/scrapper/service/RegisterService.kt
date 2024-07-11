package io.github.freewulkanowy.sdk.scrapper.service

import io.github.freewulkanowy.sdk.scrapper.register.LoginForm
import retrofit2.http.GET
import retrofit2.http.Url

internal interface RegisterService {

    @GET
    suspend fun getFormType(@Url url: String): LoginForm
}
