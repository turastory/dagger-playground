package base.command

import base.Command
import base.SingleArgCommand
import base.database.Database
import base.output.Outputter
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

class DepositCommand @Inject constructor(
    private val account: Database.Account,
    private val outputter: Outputter
) : SingleArgCommand {
    override fun handleArg(argument: String): Command.Result {
        account.deposit(BigDecimal(argument))
        outputter.output("${account.username} now has: ${account.balance}")

        return Command.Result(Command.Status.HANDLED, Optional.empty())
    }
}