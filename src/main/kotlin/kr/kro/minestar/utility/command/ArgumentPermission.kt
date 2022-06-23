package kr.kro.minestar.utility.command

import org.bukkit.permissions.Permissible
import org.bukkit.plugin.java.JavaPlugin

class ArgumentPermission {
    /**
     * null 일 경우 아무나 사용 가능하게 됩니다.
     */
    val permission : String?

    /**
     * [ArgumentPermission] 을 사용 중인 [FunctionalCommand.isSimplePermission] 의 반환 값이 true 일 때
     * onlyOp 가 true 일 때, 사용자가 Op일 경우에만 사용 가능,
     * false 일 때, 아무나 사용이 가능하게 합니다.
     */
    val onlyOp : Boolean

    constructor() {
        permission = null
        onlyOp = false
    }

    constructor(permission : String) {
        this.permission = permission
        onlyOp = false
    }

    constructor(onlyOp : Boolean) {
        this.permission = null
        this.onlyOp = onlyOp
    }

    constructor(permission : String, onlyOp : Boolean) {
        this.permission = permission
        this.onlyOp = onlyOp
    }

    constructor(plugin: JavaPlugin, simplePermission: String) {
        permission = "${plugin.javaClass.packageName}.$simplePermission"
        onlyOp = false
    }

    constructor(plugin: JavaPlugin, simplePermission: String, onlyOp : Boolean) {
        permission = "${plugin.javaClass.packageName}.$simplePermission"
        this.onlyOp = onlyOp
    }

    fun hasPermission(permissible: Permissible, functionalCommand: FunctionalCommand): Boolean {
        return hasPermission(permissible, functionalCommand.isSimplePermission())
    }

    fun hasPermission(permissible: Permissible, isSimplePermission: Boolean): Boolean {
        if (permissible.isOp) return true
        if (isSimplePermission) {
            if (!onlyOp) return true
            return false
        }
        if (permission != null) return permissible.isPermissionSet(permission)
        return true
    }
}