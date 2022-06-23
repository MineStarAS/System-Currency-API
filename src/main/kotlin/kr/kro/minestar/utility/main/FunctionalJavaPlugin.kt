package kr.kro.minestar.utility.main

import org.bukkit.plugin.java.JavaPlugin

abstract class FunctionalJavaPlugin : JavaPlugin() {
    lateinit var prefix : String
}