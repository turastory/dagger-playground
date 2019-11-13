package base.command

import base.Command
import javax.inject.Inject

class LogoutCommand @Inject constructor() : Command {
    override fun handleInput(input: MutableList<String>): Command.Result {
        return when {
            input.size == 0 -> Command.Result.inputCompleted()
            else -> Command.Result.invalid()
        }
    }
}