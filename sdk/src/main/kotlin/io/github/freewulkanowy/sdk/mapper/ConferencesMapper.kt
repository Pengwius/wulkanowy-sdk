package io.github.freewulkanowy.sdk.mapper

import io.github.freewulkanowy.sdk.pojo.Conference
import java.time.ZoneId
import io.github.freewulkanowy.sdk.scrapper.conferences.Conference as ScrapperConference

internal fun List<ScrapperConference>.mapConferences(zoneId: ZoneId) = map {
    Conference(
        place = it.place,
        topic = it.topic,
        agenda = it.agenda,
        presentOnConference = it.presentOnConference,
        online = it.online,
        date = it.date.atZone(zoneId),
        id = it.id,
    )
}
