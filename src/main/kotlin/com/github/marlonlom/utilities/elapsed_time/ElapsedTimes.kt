package com.github.marlonlom.utilities.elapsed_time

import java.lang.IllegalArgumentException
import java.util.Calendar
import java.util.Date

/**
* Utility class for, given a start and a end dates, obtain the elapsed time between the two dates
* in terms of years, months and days.
*
* @author marlonlom
*/
class ElapsedTimes private constructor() {

    interface IWithStartDate {
      fun to(endingDate: Date): IWithEndDate
      fun toNow(): IWithEndDate
    }

    interface IWithEndDate {
      @Throws(IllegalArgumentException::class)
      fun compare(): TimeCount
    }

    protected class Builder constructor(aDate: Date) : IWithStartDate, IWithEndDate {
      private var startingDate: Date? = null
      private var endingDate: Date? = null

      init {
        startingDate = aDate
      }

      public override fun to(endingDate: Date): IWithEndDate {
        this.endingDate = endingDate
        return this
      }

      public override fun toNow(): IWithEndDate {
        this.endingDate = Date()
        return this
      }

      @Throws(IllegalArgumentException::class)
      public override fun compare(): TimeCount {
        return getDateDiff(startingDate!!, endingDate!!)
      }

      @Throws(IllegalArgumentException::class)
      private fun getDateDiff(from: Date, to: Date): TimeCount {
        val parts = intArrayOf(0, 0, 0)
        val fromDate = Calendar.getInstance()
        val toDate = Calendar.getInstance()
        fromDate.setTime(from)
        toDate.setTime(to)
        var increment = 0
        if (!fromDate.before(toDate)) {
          throw IllegalArgumentException("Start date must not be after the end date.")
        }
        if (fromDate.get(Calendar.DAY_OF_MONTH) > toDate.get(Calendar.DAY_OF_MONTH)) {
          increment = fromDate.getActualMaximum(Calendar.DAY_OF_MONTH)
        }
        if (increment != 0) {
          parts[2] = (toDate.get(Calendar.DAY_OF_MONTH) + increment) - fromDate.get(Calendar.DAY_OF_MONTH)
          increment = 1
        } else {
          parts[2] = toDate.get(Calendar.DAY_OF_MONTH) - fromDate.get(Calendar.DAY_OF_MONTH)
        }
        if ((fromDate.get(Calendar.MONTH) + increment) > toDate.get(Calendar.MONTH)) {
          parts[1] = (toDate.get(Calendar.MONTH) + 12) - (fromDate.get(Calendar.MONTH) + increment)
          increment = 1
        } else {
          parts[1] = (toDate.get(Calendar.MONTH)) - (fromDate.get(Calendar.MONTH) + increment)
          increment = 0
        }
        parts[0] = toDate.get(Calendar.YEAR) - (fromDate.get(Calendar.YEAR) + increment)
        return TimeCount(parts)
      }
    }

    companion object {
    fun from(startingDate: Date): IWithStartDate {
        return ElapsedTimes.Builder(startingDate)
      }
    }
  }
