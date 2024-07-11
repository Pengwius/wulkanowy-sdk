package io.github.freewulkanowy.sdk.hebe.interceptor

import okhttp3.Interceptor
import okhttp3.Response

internal class ErrorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}
