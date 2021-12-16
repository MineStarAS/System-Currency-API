package kr.kro.minestar.utility.bool

fun Boolean.addScript(script: String): BooleanScript {
    return BooleanScript(this, script)
}