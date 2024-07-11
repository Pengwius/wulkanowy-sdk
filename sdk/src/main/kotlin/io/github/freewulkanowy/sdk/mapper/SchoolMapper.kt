package io.github.freewulkanowy.sdk.mapper

import io.github.freewulkanowy.sdk.pojo.School
import io.github.freewulkanowy.sdk.scrapper.school.School as ScrapperSchool

internal fun ScrapperSchool.mapSchool() = School(
    name = name,
    address = address,
    contact = contact,
    headmaster = headmaster,
    pedagogue = pedagogue,
)
