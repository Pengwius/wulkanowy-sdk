package io.github.freewulkanowy.sdk.scrapper.student

import io.github.freewulkanowy.sdk.scrapper.BaseLocalTest
import io.github.freewulkanowy.sdk.scrapper.register.RegisterTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ContextStudentInfoPlusTest : BaseLocalTest() {

    private val info by lazy {
        runBlocking {
            getStudentPlusRepo {
                it.enqueue("Context-all-enabled.json", RegisterTest::class.java)
                it.enqueue("DaneUcznia.json")
            }.getStudentInfo(1, 2, 3)
        }
    }

    @Test
    fun getStudentInfoTest() {
        with(info) {
            assertEquals("Jan", name)
        }
    }
}
