package kr.kro.minestar.utility.command

/**
 * enum class 가 아닌 상태의 클래스를 만들기 위한 클래스입니다.
 */
class CustomArgument(override val name: String, override val howToUse: String, override val permission: ArgumentPermission) : Argument