package kr.kro.minestar.utility.command

import org.bukkit.entity.Player

class ArgumentPermission {
    private val permissionSet : Set<String>

    constructor() {
        permissionSet = setOf()
    }

    constructor(permission : String) {
        permissionSet = setOf(permission)
    }

    constructor(set : Set<String>) {
        permissionSet = set
    }

    fun hasPermission(player: Player): Boolean {
        if (player.isOp) return true
        if (permissionSet.isEmpty()) return true

        for (permission in permissionSet) if (player.hasPermission(permission)) return true

        return false
    }
}