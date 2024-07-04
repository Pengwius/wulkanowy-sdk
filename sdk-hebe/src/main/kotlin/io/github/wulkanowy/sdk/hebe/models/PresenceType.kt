package io.github.wulkanowy.sdk.hebe.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PresenceType(
    @SerialName("Id")
    val id: Int,
    @SerialName("Name")
    val name: String?,
    @SerialName("Symbol")
    val symbol: String?,
    @SerialName("CategoryId")
    val category_id: Int?,
    @SerialName("CategoryName")
    val category_name: String?,
    @SerialName("Position")
    val position: Int?,
    @SerialName("Presence")
    val presence: Boolean?,
    @SerialName("Absence")
    val absence: Boolean?,
    @SerialName("LegalAbsence")
    val exemption: Boolean?,
    @SerialName("Late")
    val late: Boolean?,
    @SerialName("AbsenceJustified")
    val justified: Boolean?,
    @SerialName("Removed")
    val deleted: Boolean?,
)
