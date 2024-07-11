package io.github.freewulkanowy.sdk.scrapper.login

internal data class LoginResult(
    val isStudentSchoolUseEduOne: Boolean,
    val studentSchools: List<String>,
)
