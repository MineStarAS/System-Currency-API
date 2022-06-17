package kr.kro.minestar.utility.command

import org.bukkit.entity.Player

class ArgumentPermission {
    private val permissionSet : Set<String>

    private val opOnly : Boolean

    constructor(permission : String) {
        permissionSet = setOf(permission)
        opOnly = false
    }

    constructor(set : Set<String>) {
        permissionSet = set
        opOnly = false
    }

    constructor(opOnly : Boolean) {
        this.opOnly = opOnly
        permissionSet = setOf()
    }

    fun hasPermission(player: Player): Boolean {
        if (opOnly) {
            if (player.isOp) return true
            return false
        }

        for (permission in permissionSet) if (player.hasPermission(permission)) return true

        return false
    }
}