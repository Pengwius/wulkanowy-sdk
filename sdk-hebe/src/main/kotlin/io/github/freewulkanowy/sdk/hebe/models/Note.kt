package io.github.freewulkanowy.sdk.hebe.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Note(
    @SerialName("Id")
    val id: Int?,
    @SerialName("Key")
    val key: String?,
    @SerialName("IdPupil")
    val idPupil: Int?,
    @SerialName("Positive")
    val positive: Boolean?,
    @SerialName("DateValid")
    val dateValid: Date?,
    @SerialName("DateModify")
    val dateModify: Date?,
    @SerialName("Creator")
    val creator: Teacher?,
    @SerialName("Category")
    val category: Category?,
    @SerialName("Content")
    val content: String?,
    @SerialName("Points")
    val points: Int?,
) {
    @Serializable
    data class Category(
        @SerialName("Id")
        val id: Int?,
        @SerialName("Name")
        val name: String?,
        @SerialName("Type")
        val type: String?,
        @SerialName("DefaultPoints")
        val defaultPoints: Int?,
    )
}
