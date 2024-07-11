package io.github.freewulkanowy.sdk.mapper

import io.github.freewulkanowy.sdk.pojo.GovernmentMember
import io.github.freewulkanowy.sdk.pojo.GovernmentUnit
import io.github.freewulkanowy.sdk.scrapper.home.GovernmentMember as ScrapperGovernmentMember
import io.github.freewulkanowy.sdk.scrapper.home.GovernmentUnit as ScrapperGovernmentUnit

internal fun List<ScrapperGovernmentUnit>.mapToUnits() = map {
    GovernmentUnit(
        unitName = it.unitName,
        people = it.people.mapToMembers(),
    )
}

internal fun List<ScrapperGovernmentMember>.mapToMembers() = map {
    GovernmentMember(
        name = it.name,
        division = it.division,
        position = it.position,
        id = it.id,
    )
}
