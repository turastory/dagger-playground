package base.command

import base.Command
import base.SingleArgCommand
import base.database.Database
import base.output.Outputter
import javax.inject.Inject

class LoginCommand @Inject constructor(
    private val database: Database,
    private val outputter: Outputter
) : SingleArgCommand {
    override fun handleArg(argument: String): Command.Status {
        val account = database.getAccount(argument)
        outputter.output("$argument is logged in with balance: ${account.balance}")
        return Command.Status.HANDLED
    }
}