package io.github.freewulkanowy.sdk.scrapper.conferences

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")

internal fun List<Conference>.mapConferences(): List<Conference> = map {
    val dateString = it.place.split(",")[1].trim().replace(" godzina", "")
    it.copy(
        place = it.place.substringAfter(", ").substringAfter(", "),
        date = LocalDateTime.parse(dateString, dateFormatter),
    )
}.sortedBy { it.date }
