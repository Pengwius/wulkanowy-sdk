package io.github.freewulkanowy.sdk.mapper

import io.github.freewulkanowy.sdk.pojo.Semester
import io.github.freewulkanowy.sdk.scrapper.register.Semester as ScrapperSemester

@JvmName("mapScrapperSemesters")
internal fun List<ScrapperSemester>.mapSemesters() = map {
    Semester(
        diaryId = it.diaryId,
        kindergartenDiaryId = it.kindergartenDiaryId,
        diaryName = it.diaryName,
        schoolYear = it.schoolYear,
        semesterId = it.semesterId,
        semesterNumber = it.semesterNumber,
        start = it.start,
        end = it.end,
        classId = it.classId,
        className = it.className,
        unitId = it.unitId,
    )
}
