package base

import base.component.DaggerCommandRouterFactory
import java.util.*

fun main() {
    println("Hello World")

    val scanner = Scanner(System.`in`)
    val commandRouterFactory = DaggerCommandRouterFactory.create()
    val commandRouter = commandRouterFactory.router()

    while (scanner.hasNextLine()) {
        commandRouter.route(scanner.nextLine())
    }
}