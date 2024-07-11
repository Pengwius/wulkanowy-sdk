package io.github.freewulkanowy.sdk.hebe.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Grade(
    @SerialName("Column")
    val column: Column,
    @SerialName("Comment")
    val comment: String,
    @SerialName("Content")
    val content: String,
    @SerialName("ContentRaw")
    val contentRaw: String,
    @SerialName("Creator")
    val creator: Teacher,
    @SerialName("DateCreated")
    val dateCreated: Date,
    @SerialName("DateModify")
    val dateModify: Date,
    // @SerialName("Denominator")
    // val denominator: Any?,
    @SerialName("Id")
    val id: Int,
    @SerialName("Key")
    val key: String,
    @SerialName("Modifier")
    val modifier: Modifier,
    // @SerialName("Numerator")
    // val numerator: Any?,
    @SerialName("PupilId")
    val pupilId: Int,
    @SerialName("Value")
    val value: Double? = null,
) {
    @Serializable
    data class Column(
        @SerialName("Category")
        val category: Category? = null,
        @SerialName("Code")
        val code: String,
        @SerialName("Color")
        val color: Int,
        @SerialName("Group")
        val group: String? = null,
        @SerialName("Id")
        val id: Int,
        @SerialName("Key")
        val key: String,
        @SerialName("Name")
        val name: String,
        @SerialName("Number")
        val number: Int,
        @SerialName("PeriodId")
        val periodId: Int,
        @SerialName("Subject")
        val subject: Subject,
        @SerialName("Weight")
        val weight: Double,
    ) {
        @Serializable
        data class Category(
            @SerialName("Code")
            val code: String,
            @SerialName("Id")
            val id: Int,
            @SerialName("Name")
            val name: String,
        )
    }

    @Serializable
    data class Modifier(
        @SerialName("DisplayName")
        val displayName: String,
        @SerialName("Id")
        val id: Int,
        @SerialName("Name")
        val name: String,
        @SerialName("Surname")
        val surname: String,
    )
}
