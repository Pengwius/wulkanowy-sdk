package io.github.freewulkanowy.sdk.scrapper.notes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class NotesResponse(

    @SerialName("Uwagi")
    val notes: List<Note> = emptyList(),
)
