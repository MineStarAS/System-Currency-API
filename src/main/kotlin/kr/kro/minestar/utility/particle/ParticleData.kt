package kr.kro.minestar.utility.particle

import kr.kro.minestar.utility.material.item
import org.bukkit.*
import org.bukkit.block.data.BlockData
import org.bukkit.entity.Player


class ParticleData {
    var particle = Particle.FLAME
    var count = 1
    var offsetX = 0.0
    var offsetY = 0.0
    var offsetZ = 0.0
    var speed = 0.0
    var data: Any? = null
    var force = false

    /**
     * 파티클을 재생합니다.
     */
    fun play(location: Location) = location.world.spawnParticle(particle, location, count, offsetX, offsetY, offsetZ, speed, data, force)

    fun play(player: Player, location: Location) = player.spawnParticle(particle, location, count, offsetX, offsetY, offsetZ, speed, data)

    fun play(players: Collection<Player>, location: Location) {
        for (player in players) player.spawnParticle(particle, location, count, offsetX, offsetY, offsetZ, speed, data)
    }

    /**
     * offset 을 설정합니다.
     */
    fun offset(radius: Double): ParticleData {
        val r = if (radius < 0) 0.0
        else radius
        offsetX = r
        offsetY = r
        offsetZ = r

        return this
    }

    /**
     * data 를 설정합니다.
     */
    fun colorData(color: Color): ParticleData {
        particle = Particle.REDSTONE
        data = Particle.DustOptions(color, 1F)
        return this
    }

    fun colorData(color: Color, size: Float): ParticleData {
        particle = Particle.REDSTONE
        data = Particle.DustOptions(color, size)
        return this
    }

    fun transitionColorData(startColor: Color, endColor: Color): ParticleData {
        particle = Particle.DUST_COLOR_TRANSITION
        data = Particle.DustTransition(startColor, endColor, 1F)
        return this
    }

    fun transitionColorData(startColor: Color, endColor: Color, size: Float): ParticleData {
        particle = Particle.DUST_COLOR_TRANSITION
        data = Particle.DustTransition(startColor, endColor, size)
        return this
    }

    fun blockData(blockMaterial: Material) : ParticleData {
        if (!blockMaterial.isBlock) return this
        data = Bukkit.createBlockData(blockMaterial)
        return this
    }

    fun itemData(itemMaterial: Material) : ParticleData {
        if (!itemMaterial.isItem) return this
        data = itemMaterial.item()
        return this
    }

    /**
     * 같은 내용의  [ParticleData] 를 생성합니다.
     */
    fun clone() = ParticleData().also {
        it.particle = this.particle
        it.count = this.count
        it.offsetX = this.offsetX
        it.offsetY = this.offsetY
        it.offsetZ = this.offsetZ
        it.speed = this.speed
        it.data = this.data
        it.force = this.force
    }
}