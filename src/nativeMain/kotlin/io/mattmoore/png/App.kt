package io.mattmoore.png

import kotlinx.cinterop.*
import libpng.*

fun main(args: Array<String>) {
    println(png_access_version_number())
}
