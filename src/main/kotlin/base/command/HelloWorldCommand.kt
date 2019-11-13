package base.command

import base.Command
import base.output.Outputter
import javax.inject.Inject

class HelloWorldCommand @Inject constructor(
    private val outputter: Outputter
) : Command {
    override fun handleInput(input: MutableList<String>): Command.Result {
        return when {
            input.isNotEmpty() -> Command.Result.invalid()
            else -> {
                outputter.output("world!")
                Command.Result.handled()
            }
        }
    }
}