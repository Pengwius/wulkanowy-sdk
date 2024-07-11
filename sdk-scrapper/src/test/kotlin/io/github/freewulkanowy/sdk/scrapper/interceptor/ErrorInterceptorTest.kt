package io.github.freewulkanowy.sdk.scrapper.interceptor

import io.github.freewulkanowy.sdk.scrapper.BaseLocalTest
import io.github.freewulkanowy.sdk.scrapper.Scrapper
import io.github.freewulkanowy.sdk.scrapper.exception.ServiceUnavailableException
import io.github.freewulkanowy.sdk.scrapper.exception.VulcanException
import io.github.freewulkanowy.sdk.scrapper.login.LoginTest
import io.github.freewulkanowy.sdk.scrapper.login.PasswordChangeRequiredException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ErrorInterceptorTest : BaseLocalTest() {

    @Test(expected = ServiceUnavailableException::class)
    fun offline_databaseUpdate() = runTest {
        getStudentRepo(
            testClass = ErrorInterceptorTest::class.java,
            fixture = "AktualizacjaBazyDanych.html",
            loginType = Scrapper.LoginType.STANDARD,
        ).getNotes()
    }

    @Test(expected = ServiceUnavailableException::class)
    fun offline_databaseUpdatePlus() = runTest {
        getStudentRepo(
            testClass = ErrorInterceptorTest::class.java,
            fixture = "AktualizacjaBazyDanychPlus.html",
            loginType = Scrapper.LoginType.STANDARD,
        ).getNotes()
    }

    @Test
    fun `eduOne update`() = runTest {
        runCatching {
            getStudentRepo(ErrorInterceptorTest::class.java, "AktualizacjaEduOne.html", Scrapper.LoginType.STANDARD).getNotes()
        }.onFailure {
            assertTrue(it is ServiceUnavailableException)
            assertEquals("Przerwa techniczna", it.message)
            // assertEquals("Trwa aktualizacja bazy danych.", it.message)
            return@runTest
        }
        assert(false)
    }

    @Test
    fun passwordChangeRequired() {
        try {
            runBlocking { getStudentRepo(LoginTest::class.java, "PrzywracanieDostepu.html", Scrapper.LoginType.STANDARD).getNotes() }
        } catch (e: Throwable) {
            assertTrue(e is PasswordChangeRequiredException)
        }
    }

    @Test
    fun error_unknown() {
        try {
            runBlocking { getStudentRepo(ErrorInterceptorTest::class.java, "Błąd-adfs.html", Scrapper.LoginType.STANDARD).getNotes() }
        } catch (e: Throwable) {
            assertTrue(e is VulcanException)
            assertTrue(e.message?.startsWith("Błąd") == true)
        }
    }

    @Test
    fun `technical break eduOne`() = runTest {
        runCatching {
            getStudentRepo(ErrorInterceptorTest::class.java, "Przerwa.html", Scrapper.LoginType.STANDARD).getNotes()
        }.onFailure {
            assertTrue(it is ServiceUnavailableException)
            assertEquals("Przerwa", it.message)
            return@runTest
        }
        assert(false)
    }
}
