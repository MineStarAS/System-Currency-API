package kr.kro.minestar.utility.scheduler

import kr.kro.minestar.utility.scheduler.objectes.interfaces.RunRepeat
import kr.kro.minestar.utility.scheduler.objectes.interfaces.RunTask
import kr.kro.minestar.utility.string.toServer
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask

/**
 * RunTask List 를 작성하여 시간 순서 및 간격에 맞게 실행시키는 클래스입니다.
 */
class Scheduler(val pl: JavaPlugin) {
    private val runTasks = mutableListOf<RunTask>()
    private val repeats = hashMapOf<String, BukkitTask>()
    private val fields = hashMapOf<String, Any?>()

    /**
     * RunTask List 를 실행합니다.
     */
    fun play() {
        if (runTasks.isEmpty()) return
        var time = 0L
        for (runTask in runTasks) {
            time += runTask.delay
            if (runTask is RunRepeat) repeats[runTask.key] = runTask.run(pl, time)
            else runTask.run(pl, time)
        }
    }

    /**
     * RunTask List 에 RunTask 를 추가합니다.
     */
    fun addRun(runTask: RunTask) {
        runTasks.add(runTask)
    }

    /**
     * 반복 RunTask 를 가져옵니다.
     */
    fun getRepeats(key: String): BukkitTask? {
        return repeats[key]
    }

    /**
     * 반복 RunTask 를 취소 및 삭제합니다.
     */
    fun cancelRepeats(key: String): Boolean {
        repeats[key]?.cancel() ?: return false
        repeats.remove(key)
        return true
    }

    /**
     * 저장된 클래스를 T 로 캐스팅 하여 가져옵니다.
     */
    fun <T> getField(key: String): T {
        return fields[key] as T
    }

    /**
     * 클래스를 Map 에 저장합니다.
     */
    fun addField(key: String, any: Any?) {
        fields[key] = any
    }

    /**
     * Map 에 있는 클래스를 삭제합니다.
     */
    fun removeField(key: String) {
        fields.remove(key)
    }
}