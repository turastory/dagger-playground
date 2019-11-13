package base

import base.component.DaggerCommandProcessorFactory
import java.util.*

fun main() {
    println("Hello World")

    val scanner = Scanner(System.`in`)
    val processor = DaggerCommandProcessorFactory
        .create()
        .commandProcessor()

    while (scanner.hasNextLine()) {
        processor.process(scanner.nextLine())
    }
}