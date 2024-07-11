package io.github.freewulkanowy.sdk.scrapper.register

import io.github.freewulkanowy.sdk.scrapper.BaseLocalTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test
import java.time.LocalDate

class SemestersPlusTest : BaseLocalTest() {

    @Test
    fun `get semesters when diary and unit id are not set`() = runTest {
        val repo = getStudentPlusRepo {
            // ~ ❯❯❯ echo "MS0yLTEtMw==" | base64 -d
            // 1-2-1-3
            it.enqueue("Context-all-disabled.json", RegisterTest::class.java)
            it.enqueue("OkresyKlasyfikacyjne.json", RegisterTest::class.java)
        }
        val semesters = repo.getSemesters(1)

        assertEquals(2, semesters.size)
    }

    @Ignore("thanks to VULCAN")
    @Test(expected = NoSuchElementException::class)
    fun `get semesters when there is no matching student list`() = runTest {
        val repo = getStudentPlusRepo {
            // ~ ❯❯❯ echo "MS0yLTEtMw==" | base64 -d
            // 1-2-1-3
            it.enqueue("Context-all-disabled.json", RegisterTest::class.java)
        }
        repo.getSemesters(2)
    }

    @Test
    fun `get semesters when there is empty semesters list`() = runTest {
        val repo = getStudentPlusRepo {
            it.enqueue("Context-all-disabled.json", RegisterTest::class.java)
            it.enqueueContent("[]")
        }
        val semesters = repo.getSemesters(1)

        assertEquals(1, semesters.size)
        assertEquals(LocalDate.of(2023, 9, 1), semesters.single().start)
        assertEquals(LocalDate.of(2024, 8, 31), semesters.single().end)
    }
}
