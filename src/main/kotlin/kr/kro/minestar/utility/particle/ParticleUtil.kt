package kr.kro.minestar.utility.particle

import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.entity.Player


class ParticleUtil {
    var particle = Particle.FLAME
    var count = 1
    var offsetX = 0.0
    var offsetY = 0.0
    var offsetZ = 0.0
    var speed = 0.0
    val data: Any? = null
    var force = false
    val players = mutableListOf<Player>()

    /**
     * 파티클을 재생합니다.
     */
    fun play(location: Location) {
        if (players.isEmpty()) return location.world.spawnParticle(particle, location, count, offsetX, offsetY, offsetZ, speed, data, force)
        for (p in players) p.spawnParticle(particle, location, count, offsetX, offsetY, offsetZ, speed, force)
    }
}