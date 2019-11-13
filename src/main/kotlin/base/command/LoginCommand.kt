package base.command

import base.Command
import base.SingleArgCommand
import base.database.Database
import base.output.Outputter
import java.util.*
import javax.inject.Inject

class LoginCommand @Inject constructor(
    private val database: Database,
    private val outputter: Outputter
) : SingleArgCommand {
    override fun handleArg(argument: String): Command.Result {
        val account = database.getAccount(argument)
        outputter.output("$argument is logged in with balance: ${account.balance}")
        return Command.Result(Command.Status.HANDLED, Optional.empty())
    }
}