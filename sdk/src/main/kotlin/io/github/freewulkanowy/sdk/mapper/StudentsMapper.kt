package io.github.freewulkanowy.sdk.mapper

import io.github.freewulkanowy.sdk.Sdk
import io.github.freewulkanowy.sdk.hebe.register.RegisterDevice
import io.github.freewulkanowy.sdk.hebe.register.StudentInfo
import io.github.freewulkanowy.sdk.pojo.RegisterEmployee
import io.github.freewulkanowy.sdk.pojo.RegisterStudent
import io.github.freewulkanowy.sdk.pojo.RegisterSubject
import io.github.freewulkanowy.sdk.pojo.RegisterSymbol
import io.github.freewulkanowy.sdk.pojo.RegisterUnit
import io.github.freewulkanowy.sdk.pojo.RegisterUser
import io.github.freewulkanowy.sdk.pojo.Semester
import io.github.freewulkanowy.sdk.toLocalDate
import java.time.LocalDate
import io.github.freewulkanowy.sdk.scrapper.register.RegisterEmployee as ScrapperRegisterEmploye
import io.github.freewulkanowy.sdk.scrapper.register.RegisterStudent as ScrapperRegisterStudent
import io.github.freewulkanowy.sdk.scrapper.register.RegisterSubject as ScrapperRegisterSubject
import io.github.freewulkanowy.sdk.scrapper.register.RegisterSymbol as SdkRegisterSymbol
import io.github.freewulkanowy.sdk.scrapper.register.RegisterUnit as ScrapperRegisterUnit
import io.github.freewulkanowy.sdk.scrapper.register.RegisterUser as ScrapperRegisterUser

internal fun ScrapperRegisterUser.mapUser(): RegisterUser = RegisterUser(
    email = email,
    login = login,
    scrapperBaseUrl = baseUrl,
    loginType = loginType,
    loginMode = Sdk.Mode.SCRAPPER,
    symbols = symbols.map { it.mapSymbol() },
)

internal fun SdkRegisterSymbol.mapSymbol(): RegisterSymbol = RegisterSymbol(
    symbol = symbol,
    userName = userName,
    error = error,
    keyId = null,
    privatePem = null,
    hebeBaseUrl = null,
    schools = schools.map { it.mapUnit() },
)

internal fun ScrapperRegisterUnit.mapUnit(): RegisterUnit = RegisterUnit(
    userLoginId = userLoginId,
    schoolId = schoolId,
    schoolName = schoolName,
    schoolShortName = schoolShortName,
    parentIds = parentIds,
    studentIds = studentIds,
    employeeIds = employeeIds,
    error = error,
    subjects = subjects.map { it.mapSubject() },
)

internal fun ScrapperRegisterSubject.mapSubject(): RegisterSubject {
    return when (this) {
        is ScrapperRegisterStudent -> mapStudent()
        is ScrapperRegisterEmploye -> mapEmployee()
    }
}

internal fun ScrapperRegisterEmploye.mapEmployee(): RegisterEmployee = RegisterEmployee(
    employeeId = employeeId,
    employeeName = employeeName,
)

internal fun ScrapperRegisterStudent.mapStudent(): RegisterStudent = RegisterStudent(
    studentId = studentId,
    studentName = studentName,
    studentSecondName = studentSecondName,
    studentSurname = studentSurname,
    className = className,
    classId = classId,
    isParent = isParent,
    semesters = semesters.mapSemesters(),
    isAuthorized = isAuthorized,
    isEduOne = isEduOne,
)

fun List<StudentInfo>.mapHebeUser(
    device: RegisterDevice,
): RegisterUser = RegisterUser(
    email = device.userName,
    login = device.userLogin,
    scrapperBaseUrl = null,
    loginType = null,
    loginMode = Sdk.Mode.HEBE,
    symbols = this
        .groupBy { it.topLevelPartition }
        .mapNotNull { (symbol, students) ->
            RegisterSymbol(
                symbol = symbol ?: "Default",
                error = null,
                keyId = device.certificateHash,
                privatePem = device.privatePem,
                hebeBaseUrl = device.restUrl,
                userName = students.firstOrNull()?.login?.displayName ?: return@mapNotNull null,
                schools = students.mapUnit(),
            )
        },
)

private fun List<StudentInfo>.mapUnit(): List<RegisterUnit> {
    return this
        .groupBy { it.unit?.symbol }
        .mapNotNull { (schoolId, students) ->
            val firstStudent = students.firstOrNull() ?: return@mapNotNull null
            RegisterUnit(
                userLoginId = firstStudent.login?.id ?: 0,
                schoolId = schoolId ?: "00000",
                schoolName = firstStudent.constituentUnit?.name ?: "Nieznany",
                schoolShortName = firstStudent.constituentUnit?.short ?: "Nieznany",
                parentIds = listOf(),
                studentIds = listOf(),
                employeeIds = listOf(),
                error = null,
                subjects = students.map { student ->
                    RegisterStudent(
                        studentId = student.pupil?.id ?: 0,
                        studentName = student.pupil.let { pupil -> "${pupil?.firstName} ${pupil?.surname}" },
                        studentSecondName = student.pupil?.secondName ?: "Nieznany",
                        studentSurname = student.pupil?.surname ?: "Nieznany",
                        className = student.classDisplay ?: "Nieznany",
                        classId = -1, // todo
                        isParent = student.login?.loginRole != "Uczen",
                        isAuthorized = true,
                        isEduOne = false,
                        semesters = student.periods?.map { period ->
                            Semester(
                                diaryId = student.journal?.id ?: 0,
                                kindergartenDiaryId = 0,
                                diaryName = student.classDisplay ?: "Nieznany",
                                schoolYear = period?.start?.timestamp?.toLocalDate()?.year ?: 0,
                                semesterId = period?.id ?: 0,
                                semesterNumber = period?.number ?: 0,
                                start = period?.start?.timestamp?.toLocalDate() ?: LocalDate.parse("1970-1-1"),
                                end = period?.end?.timestamp?.toLocalDate() ?: LocalDate.parse("1970-1-1"),
                                classId = -1, // todo
                                className = student.classDisplay,
                                unitId = student.unit?.id ?: 0, // todo: is needed?
                            )
                        } ?: emptyList(),
                    )
                },
            )
        }
}
