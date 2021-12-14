package kr.kro.minestar.utility.scheduler.objectes.later

import kr.kro.minestar.utility.scheduler.objectes.interfaces.RunTask

class RunLater(override val runnable: Runnable, override val delay: Long) : RunTask {
}
