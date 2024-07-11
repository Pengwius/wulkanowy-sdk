package io.github.freewulkanowy.sdk.scrapper.exams

import io.github.freewulkanowy.sdk.scrapper.adapter.CustomDateAdapter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
internal class ExamResponse {

    @SerialName("SprawdzianyGroupedByDayList")
    var weeks: List<ExamDay> = emptyList()

    @Serializable
    class ExamDay {

        @SerialName("Data")
        @Serializable(with = CustomDateAdapter::class)
        lateinit var date: LocalDateTime

        @SerialName("Sprawdziany")
        lateinit var exams: List<Exam>
    }
}
