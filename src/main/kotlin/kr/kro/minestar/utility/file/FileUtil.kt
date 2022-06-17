package kr.kro.minestar.utility.file

import java.io.File

fun File.child(fileName: String) = File(this, fileName)