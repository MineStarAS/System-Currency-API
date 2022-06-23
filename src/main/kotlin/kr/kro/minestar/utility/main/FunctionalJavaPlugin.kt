package kr.kro.minestar.utility.main

import org.bukkit.plugin.java.JavaPlugin

abstract class FunctionalJavaPlugin : JavaPlugin() {
    protected var privatePrefix: String = ""
    val prefix = privatePrefix
}