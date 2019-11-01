package base.command

import base.Command
import javax.inject.Inject

class HelloWorldCommand @Inject constructor() : Command {
    override fun key(): String {
        return "hello"
    }

    override fun handleInput(input: MutableList<String>): Command.Status {
        return when {
            input.isNotEmpty() -> Command.Status.INVALID
            else -> {
                println("Hello World")
                Command.Status.HANDLED
            }
        }
    }
}