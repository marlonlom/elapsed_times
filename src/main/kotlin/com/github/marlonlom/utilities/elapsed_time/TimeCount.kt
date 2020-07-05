package com.github.marlonlom.utilities.elapsed_time

/**
 * Data Class that describes the elapsed time count (in terms of years,months,days) between two dates.
 *
 * @author marlonlom
 */
data class TimeCount(
  private var _years: Int = 0,
  private var _months: Int = 0,
  private var _days: Int = 0
) {

    val years: Int get() = _years
    val months: Int get() = _months
    val days: Int get() = _days

    constructor(parts: IntArray) : this() {
      _years = parts[0]
      _months = parts[1]
      _days = parts[2]
    }

    public override fun toString(): String = StringBuilder().apply {
        append(if (_years > 1) String.format("%d years, ", _years) else (if (_years == 1) String.format("%d year, ", _years) else ""))
        append(if (_months > 1) String.format("%d months, ", _months) else (if (_months == 1) String.format("%d month, ", _months) else ""))
        append(if (_days > 1) String.format("%d days", _days) else (if (_days == 1) String.format("%d day", _days) else ""))
      }.toString()
  }
