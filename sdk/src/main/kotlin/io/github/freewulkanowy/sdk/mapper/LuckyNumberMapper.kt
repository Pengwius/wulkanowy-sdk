package io.github.freewulkanowy.sdk.mapper

import io.github.freewulkanowy.sdk.pojo.LuckyNumber
import io.github.freewulkanowy.sdk.scrapper.home.LuckyNumber as ScrapperLuckyNumber

internal fun List<ScrapperLuckyNumber>.mapLuckyNumbers() = map {
    LuckyNumber(
        unitName = it.unitName,
        school = it.school,
        number = it.number,
    )
}
