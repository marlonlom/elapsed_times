package com.github.marlonlom.utilities.elapsed_time

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.fail

/**
 * Test utility class for elapsed time functionality.
 *
 * @author marlonlom
 */
class ElapsedTimeUtilityTest {

    private val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    val obtainElapsedTime: (String, String) -> TimeCount = { startingDateText: String, endingDateText: String ->
        ElapsedTimes.from(sdf.parse(startingDateText)).to(sdf.parse(endingDateText)).compare()
    }

    @Test fun `Should return five months and seven days`() {
        val elapsed = obtainElapsedTime("24/7/2014", "31/12/2014")
        assertEquals(0, elapsed.years, "expected zero years")
        assertEquals(5, elapsed.months, "expected five months")
        assertEquals(7, elapsed.days, "expected seven days")
    }

    @Test fun `Should return one year, one month and eleven days`() {
        val elapsed = obtainElapsedTime("23/09/2015", "04/11/2016")
        assertEquals(1, elapsed.years, "expected one year")
        assertEquals(1, elapsed.months, "expected one months")
        assertEquals(11, elapsed.days, "expected eleven days")
    }

    @Test fun `Should return seven days`() {
        val elapsed = obtainElapsedTime("17/11/2016", "24/11/2016")
        assertEquals(0, elapsed.years, "expected zero years")
        assertEquals(0, elapsed.months, "expected zero months")
        assertEquals(7, elapsed.days, "expected seven days")
    }

    @Test fun `Should return three months and nineteen days`() {
        val elapsed = obtainElapsedTime("12/12/2016", "31/03/2017")
        assertEquals(0, elapsed.years, "expected zero years")
        assertEquals(3, elapsed.months, "expected three months")
        assertEquals(19, elapsed.days, "expected nineteen days")
    }

    @Test fun `Should return one year and twenty four days`() {
        val elapsed = obtainElapsedTime("9/10/2017", "02/11/2018")
        assertEquals(1, elapsed.years, "expected one year")
        assertEquals(0, elapsed.months, "expected zero months")
        assertEquals(24, elapsed.days, "expected twenty four days")
    }

    @Test fun `Should throw exception when comparing a starting date that is after the ending date`() {
        try {
            obtainElapsedTime("12/11/2019", "08/06/2010")
            fail()
        } catch (exception: Exception) {
            assertEquals("Start date must not be after the end date.", exception.message)
        }
    }

    @Test fun `Should return two days from now`() {
        val twoDaysBeforeNow = Calendar.getInstance().apply { add(Calendar.DATE, -2) }
        val elapsed = ElapsedTimes.from(twoDaysBeforeNow.time).toNow().compare()
        assertEquals(0, elapsed.years, "expected zero years")
        assertEquals(0, elapsed.months, "expected zero months")
        assertEquals(2, elapsed.days, "expected two days")
    }
}
