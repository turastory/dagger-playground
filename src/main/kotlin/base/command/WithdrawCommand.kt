package base.command

import base.Command
import base.SingleArgCommand
import base.database.Database
import base.output.Outputter
import base.qualifiers.MaxWithdrawal
import base.qualifiers.MinimumBalance
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

class WithdrawCommand @Inject constructor(
    private val account: Database.Account,
    private val outputter: Outputter,
    @MinimumBalance private val minimumBalance: BigDecimal,
    @MaxWithdrawal private val maxWithdrawal: BigDecimal
) : SingleArgCommand {
    override fun handleArg(argument: String): Command.Result {
        val amount = BigDecimal(argument)
        return when {
            amount.signum() < 0 ||
                    amount > maxWithdrawal ||
                    account.balance - amount < minimumBalance -> Command.Result.invalid()
            else -> {
                account.withdraw(amount)
                outputter.output("your new balance is: " + account.balance)
                Command.Result(Command.Status.HANDLED, Optional.empty())
            }
        }
    }
}