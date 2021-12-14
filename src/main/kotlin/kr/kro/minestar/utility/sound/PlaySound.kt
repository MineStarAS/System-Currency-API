package kr.kro.minestar.utility.sound

import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.entity.Player

/**
 * playSound 를 저장하기 위한 클래스 입니다.
 */
class PlaySound {
    var players: Collection<Player> = listOf()
    var location: Location? = null
    var sound: Sound = Sound.BLOCK_STONE_BREAK
    var soundCategory: SoundCategory = SoundCategory.MASTER
    var volume: Float = 1.0F
    var pitch: Float = 1.0F
    var scale: Scale? = null

    fun play() {
        pitch = scale?.float ?: pitch
        if (location != null) location!!.world.playSound(location!!, sound, soundCategory, volume, pitch)
        else for (p in players) p.playSound(p.location, sound, soundCategory, volume, pitch)
    }

    fun clone(): PlaySound {
        val playSound = PlaySound()
        playSound.players = players
        playSound.location = location
        playSound.sound = sound
        playSound.soundCategory = soundCategory
        playSound.volume = volume
        playSound.pitch = pitch
        return playSound
    }
}