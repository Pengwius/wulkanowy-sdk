package io.github.freewulkanowy.sdk.mapper

import io.github.freewulkanowy.sdk.pojo.Subject
import io.github.freewulkanowy.sdk.scrapper.attendance.Subject as ScrapperSubject

internal fun List<ScrapperSubject>.mapSubjects(): List<Subject> = map {
    Subject(
        id = it.value,
        name = it.name,
    )
}
