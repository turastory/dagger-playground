package base.command

import base.Command
import base.SingleArgCommand
import base.WithdrawalLimiter
import base.database.Database
import base.output.Outputter
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

class DepositCommand @Inject constructor(
    private val account: Database.Account,
    private val withdrawalLimiter: WithdrawalLimiter,
    private val outputter: Outputter
) : SingleArgCommand {
    override fun handleArg(argument: String): Command.Result {
        val amount = BigDecimal(argument)
        account.deposit(amount)
        withdrawalLimiter.recordDeposit(amount)
        outputter.output("${account.username} now has: ${account.balance}")

        return Command.Result(Command.Status.HANDLED, Optional.empty())
    }
}