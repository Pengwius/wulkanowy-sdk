package io.github.freewulkanowy.sdk.mapper

import io.github.freewulkanowy.sdk.pojo.StudentGender
import io.github.freewulkanowy.sdk.pojo.StudentGuardian
import io.github.freewulkanowy.sdk.pojo.StudentInfo
import io.github.freewulkanowy.sdk.pojo.StudentPhoto
import java.time.LocalDate
import io.github.freewulkanowy.sdk.scrapper.student.StudentGuardian as ScrapperStudentGuardian
import io.github.freewulkanowy.sdk.scrapper.student.StudentInfo as ScrapperStudentInfo
import io.github.freewulkanowy.sdk.scrapper.student.StudentPhoto as ScrapperStudentPhoto

internal fun ScrapperStudentInfo.mapStudent() = StudentInfo(
    fullName = fullName.orEmpty(),
    address = address,
    birthDate = birthDate?.toLocalDate() ?: LocalDate.EPOCH,
    birthPlace = birthPlace.orEmpty(),
    cellPhoneNumber = cellPhone.orEmpty(),
    correspondenceAddress = correspondenceAddress,
    email = email.orEmpty(),
    familyName = familyName.orEmpty(),
    firstName = name,
    gender = if (gender) StudentGender.MALE else StudentGender.FEMALE,
    parentsNames = motherAndFatherNames.orEmpty(),
    phoneNumber = homePhone.orEmpty(),
    hasPolishCitizenship = polishCitizenship == 1,
    registeredAddress = registeredAddress,
    secondName = middleName.orEmpty(),
    surname = lastName,
    guardianFirst = guardianFirst?.toFamilyMember(),
    guardianSecond = guardianSecond?.toFamilyMember(),
)

internal fun ScrapperStudentPhoto.mapPhoto() = StudentPhoto(photoBase64 = photoBase64.orEmpty())

private fun ScrapperStudentGuardian.toFamilyMember() = StudentGuardian(
    fullName = fullName,
    email = email.orEmpty(),
    address = address,
    kinship = kinship.orEmpty(),
    phones = phone,
)
