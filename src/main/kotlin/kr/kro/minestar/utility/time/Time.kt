package kr.kro.minestar.utility.time

import kr.kro.minestar.utility.string.remove
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask

class Time(val pl: JavaPlugin) {
    var day = 0
    var hour = 0
    var min = 0
    var sec = 0
    var tick = 0

    var task: BukkitTask? = null

    init {
        overflowCheck()
    }

    /**
     * 스톱워치 기능을 실행합니다.
     */
    fun startStopwatch(): Boolean {
        if (task != null) return false
        overflowCheck()
        task = Bukkit.getScheduler().runTaskTimer(pl, Runnable {
            ++tick
            if (tick <= 20) return@Runnable
            tick = 0

            ++sec
            if (sec <= 60) return@Runnable
            sec = 0

            ++min
            if (min <= 60) return@Runnable
            min = 0

            ++hour
            if (hour <= 24) return@Runnable
            hour = 0

            ++day
        }, 0, 1)
        return true
    }

    /**
     * 타이머 기능을 실행합니다.
     */
    fun startTimer(): Boolean {
        if (task != null) return false
        overflowCheck()
        task = Bukkit.getScheduler().runTaskTimer(pl, Runnable {
            if (day == 0 && hour == 0 && min == 0 && sec == 0 && tick == 0) {
                pause()
                return@Runnable
            }
            --tick
            if (tick >= 0) return@Runnable
            tick = 19

            --sec
            if (sec >= 0) return@Runnable
            sec = 59

            --min
            if (min >= 0) return@Runnable
            min = 59

            --hour
            if (hour >= 0) return@Runnable
            hour = 23

            --day
        }, 0, 1)
        return true
    }

    /**
     * Task를 캔슬 및 삭제 합니다.
     */
    fun pause(): Boolean {
        task?.cancel() ?: return false
        task = null
        return true
    }

    /**
     * 시간을 0으로 설정합니다.
     */
    fun clear(): Boolean {
        if (task != null) return false
        day = 0
        hour = 0
        min = 0
        sec = 0
        tick = 0
        return true
    }

    /**
     * 시간을 문자열로 출력합니다.
     */
    override fun toString(): String {
        if (day > 0) {
            if (tick % 2 == 0) return "$day D $hour H $min M $sec.${(tick / 20.0).toString().remove("0.")}0 S"
            return "$day D $hour H $min M $sec.${(tick / 20.0).toString().remove("0.")} S"
        }
        if (hour > 0) {
            if (tick % 2 == 0) return "$hour H $min M $sec.${(tick / 20.0).toString().remove("0.")}0 S"
            return "$hour H $min M $sec.${(tick / 20.0).toString().remove("0.")} S"
        }
        if (tick % 2 == 0) return "$min M $sec.${(tick / 20.0).toString().remove("0.")}0 S"
        return "$min M $sec.${(tick / 20.0).toString().remove("0.")} S"
    }

    /**
     * 시간을 단위에 맞게 계산 하여 숫자로 출력합니다.
     */
    fun toDay(): Double {
        return day + (hour / 24.0) + (min / (24.0 * 60)) + (sec / (24.0 * 60 * 60)) + (tick / (24.0 * 60 * 60 * 20))
    }

    fun toHour(): Double {
        return (day * 24) + hour + (min / 60) + (sec / (60.0 * 60)) + (tick / (60.0 * 60 * 20))
    }

    fun toMin(): Double {
        return (day * 24 * 60) + (hour * 60) + min + (sec / 60.0) + (tick / (60.0 * 20))
    }

    fun toSec(): Double {
        return (day * 24 * 60 * 60) + (hour * 60 * 60) + (min * 60) + sec + (tick / 20.0)
    }

    fun toTick(): Int {
        return (day * 24 * 60 * 60 * 20) + (hour * 60 * 60 * 20) + (min * 60 * 20) + (sec * 20) + tick
    }

    /**
     * 양수인 정수를 단위에 맞게 계산하여 시간을 설정합니다.
     */
    fun setByDay(int: Int): Boolean {
        if (int < 0) return false
        day = int
        return true
    }

    fun setByHour(int: Int): Boolean {
        if (int < 0) return false
        hour = int
        overflowCheck()
        return true
    }

    fun setByMin(int: Int): Boolean {
        if (int < 0) return false
        min = int
        overflowCheck()
        return true
    }

    fun setBySec(int: Int): Boolean {
        if (int < 0) return false
        sec = int
        overflowCheck()
        return true
    }

    fun setByTick(int: Int): Boolean {
        if (int < 0) return false
        tick = int
        overflowCheck()
        return true
    }

    /**
     * 양수인 정수를 선택 단위로 시간에 더 합니다.
     */
    fun addDay(int: Int): Boolean {
        if (int <= 0) return false
        day += int
        return true
    }

    fun addHour(int: Int): Boolean {
        if (int <= 0) return false
        hour += int
        overflowCheck()
        return true
    }

    fun addMin(int: Int): Boolean {
        if (int <= 0) return false
        min += int
        overflowCheck()
        return true
    }

    fun addSec(int: Int): Boolean {
        if (int <= 0) return false
        sec += int
        overflowCheck()
        return true
    }

    fun addTick(int: Int): Boolean {
        if (int <= 0) return false
        tick += int
        overflowCheck()
        return true
    }

    /**
     * 양수인 정수를 선택 단위로 시간에 더 합니다.
     */
    fun subDay(int: Int): Boolean {
        if (int <= 0) return false
        day -= int
        if (day < 0) day = 0
        return true
    }

    fun subHour(int: Int): Boolean {
        if (int <= 0) return false
        hour -= int
        if (hour < 0) hour = 0
        overflowCheck()
        return true
    }

    fun subMin(int: Int): Boolean {
        if (int <= 0) return false
        min -= int
        if (min < 0) min = 0
        overflowCheck()
        return true
    }

    fun subSec(int: Int): Boolean {
        if (int <= 0) return false
        sec -= int
        if (sec < 0) sec = 0
        overflowCheck()
        return true
    }

    fun subTick(int: Int): Boolean {
        if (int <= 0) return false
        tick -= int
        if (tick < 0) tick = 0
        overflowCheck()
        return true
    }

    /**
     * 각 단위 값을 단위 최대값과 비교하여 올림 처리 합니다.
     */
    fun overflowCheck() {
        if (tick >= 20) {
            sec += tick / 20
            tick %= 20
        } else if (tick < 0) tick = 0
        if (sec >= 60) {
            min += sec / 60
            sec %= 60
        } else if (sec < 0) sec = 0
        if (min >= 60) {
            hour += min / 60
            min %= 60
        } else if (min < 0) min = 0
        if (hour >= 24) {
            day += hour / 24
            hour %= 24
        } else if (hour < 0) hour = 0
        if (day < 0) day = 0
    }
}