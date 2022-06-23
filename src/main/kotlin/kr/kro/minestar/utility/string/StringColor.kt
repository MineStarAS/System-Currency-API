package kr.kro.minestar.utility.string

enum class StringColor(val string: String) {
    BLACK("§0"),
    DARK_BLUE("§1"),
    GREEN("§2"),
    DARK_AQUA("§3"),
    DARK_RED("§4"),
    PURPLE("§5"),
    ORANGE("§6"),
    GRAY("§7"),
    DARK_GRAY("§8"),
    BLUE("§9"),
    LIME("§a"),
    AQUA("§b"),
    RED("§c"),
    MAGENTA("§d"),
    YELLOW("§e"),
    WHITE("§f");

    override fun toString() = string
}