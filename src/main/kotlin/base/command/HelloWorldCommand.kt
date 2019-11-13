package base.command

import base.Command
import base.output.Outputter
import java.util.*
import javax.inject.Inject

class HelloWorldCommand @Inject constructor(
    private val outputter: Outputter
) : Command {
    override fun handleInput(input: MutableList<String>): Command.Result {
        return when {
            input.isNotEmpty() -> Command.Result.invalid()
            else -> {
                outputter.output("world!")
                Command.Result(Command.Status.HANDLED, Optional.empty())
            }
        }
    }
}