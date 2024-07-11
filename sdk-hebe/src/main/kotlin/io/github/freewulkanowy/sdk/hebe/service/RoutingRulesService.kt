package io.github.freewulkanowy.sdk.hebe.service

import retrofit2.http.GET

internal interface RoutingRulesService {

    @GET("/UonetPlusMobile/RoutingRules.txt")
    suspend fun getRoutingRules(): String
}
