package io.github.freewulkanowy.sdk.mapper

import io.github.freewulkanowy.sdk.pojo.Teacher
import io.github.freewulkanowy.sdk.scrapper.school.Teacher as ScrapperTeacher

internal fun List<ScrapperTeacher>.mapTeachers() = map {
    Teacher(
        name = it.name,
        short = it.short,
        subject = it.subject,
    )
}
