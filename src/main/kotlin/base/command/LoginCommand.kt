package base.command

import base.Command
import base.SingleArgCommand
import base.output.Outputter
import javax.inject.Inject

class LoginCommand @Inject constructor(
    private val outputter: Outputter
) : SingleArgCommand {
    override fun handleArg(argument: String): Command.Status {
        outputter.output("$argument is logged in.")
        return Command.Status.HANDLED;
    }
}