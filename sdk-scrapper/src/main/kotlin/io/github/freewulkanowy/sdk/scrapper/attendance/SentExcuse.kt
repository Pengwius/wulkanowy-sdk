package io.github.freewulkanowy.sdk.scrapper.attendance

import io.github.freewulkanowy.sdk.scrapper.adapter.CustomDateAdapter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
internal class SentExcuse {

    @SerialName("Status")
    var status: Int = 0

    @SerialName("Dzien")
    @Serializable(with = CustomDateAdapter::class)
    lateinit var date: LocalDateTime

    @SerialName("IdPoraLekcji")
    var timeId: Int? = null
}

enum class SentExcuseStatus(val id: Int) {
    WAITING(0),
    ACCEPTED(1),
    DENIED(2),
    ;

    companion object {
        fun getByValue(value: Int) = entries.firstOrNull { it.id == value }
    }
}
