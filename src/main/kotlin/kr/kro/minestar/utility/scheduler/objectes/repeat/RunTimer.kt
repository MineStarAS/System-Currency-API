package kr.kro.minestar.utility.scheduler.objectes.repeat

import kr.kro.minestar.utility.scheduler.objectes.interfaces.RunRepeat

/**
 * 반복문을 실행합니다.
 */
class RunTimer(override val runnable: Runnable, override val delay: Long, override val period: Long, override val key: String) : RunRepeat {
}