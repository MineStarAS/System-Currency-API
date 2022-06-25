package kr.kro.minestar.utility.command

/**
 * enum class 가 아닌 상태의 클래스를 만들기 위한 클래스입니다.
 */
class CustomArgument : Argument {
    override val name: String
    override val howToUse: String
    override val permission: ArgumentPermission
    override val aliases: List<String>?

    constructor(name: String, howToUse: String, permission: ArgumentPermission) {
        this.name = name
        this.howToUse = howToUse
        this.permission = permission
        this.aliases = null
    }

    constructor(name: String, howToUse: String, permission: ArgumentPermission, aliases: List<String>) {
        this.name = name
        this.howToUse = howToUse
        this.permission = permission
        this.aliases = aliases
    }
}