package kr.kro.minestar.utility.scheduler.objectes.interfaces

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask

/**
 * BukkitTask를 이용하는 반복 스케줄러 인터페이스 입니다.
 */
interface RunRepeat : RunTask {
    val period: Long
    val key: String

    /**
     * delay 값 만큼 대기 후 Runnable을 반복 실행합니다.
     */
    override fun run(pl: JavaPlugin, delay: Long): BukkitTask {
        return Bukkit.getScheduler().runTaskTimer(pl, runnable, delay, period)
    }
}