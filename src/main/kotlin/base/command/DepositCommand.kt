package base.command

import base.Command
import base.SingleArgCommand
import base.database.Database
import base.output.Outputter
import java.math.BigDecimal
import javax.inject.Inject

class DepositCommand @Inject constructor(
    private val database: Database,
    private val outputter: Outputter
) : Command {
    override fun handleInput(input: MutableList<String>): Command.Status {
        if (input.size != 2) {
            return Command.Status.INVALID
        }

        val account = database.getAccount(input[0])
        account.deposit(BigDecimal(input[1]))
        outputter.output("${account.username} now has: ${account.balance}")

        return Command.Status.HANDLED
    }
}