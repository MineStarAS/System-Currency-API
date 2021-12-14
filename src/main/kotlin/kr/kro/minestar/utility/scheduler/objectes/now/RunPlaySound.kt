package kr.kro.minestar.utility.scheduler.objectes.now

import kr.kro.minestar.utility.scheduler.objectes.interfaces.RunTask
import kr.kro.minestar.utility.sound.PlaySound

class RunPlaySound(playSound: PlaySound) : RunTask {
    override val delay = 0L
    override val runnable = Runnable { playSound.play() }
}