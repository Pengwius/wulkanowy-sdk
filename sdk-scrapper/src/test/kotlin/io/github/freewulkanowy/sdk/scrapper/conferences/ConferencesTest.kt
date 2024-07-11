package io.github.freewulkanowy.sdk.scrapper.conferences

import io.github.freewulkanowy.sdk.scrapper.BaseLocalTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ConferencesTest : BaseLocalTest() {

    private val conferences by lazy {
        runBlocking { getStudentRepo(ConferencesTest::class.java, "Zebrania.json").getConferences() }
    }

    @Test
    fun getConferences() {
        assertEquals(2, conferences.size)

        with(conferences[0]) {
            assertEquals("ZSW", place)
            assertEquals("Pierwsze - organizacyjne zebranie z rodzicami klas pierwszych", topic)
            assertEquals("", agenda)
            assertEquals("Kowalski Mieczysław", presentOnConference)
            assertEquals(2121, id)
            assertEquals(getLocalDateTime(2019, 9, 6, 16, 30, 0), date)
        }

        with(conferences[1]) {
            assertEquals("Spotkanie z rodzicami/opiekunami. ", place)
            assertEquals("Podsumowanie I semestru - średnia klasy, oceny, frekwencja, zachowanie.", topic)
            assertEquals("", agenda)
            assertEquals("", presentOnConference)
            assertEquals(3737, id)
            assertEquals(getLocalDateTime(2020, 1, 8, 17, 0, 0), date)
        }
    }
}
