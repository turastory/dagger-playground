package base.command

import base.Command
import base.SingleArgCommand
import base.WithdrawalLimiter
import base.database.Database
import base.output.Outputter
import base.qualifiers.MaxWithdrawal
import base.qualifiers.MinimumBalance
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

class WithdrawCommand @Inject constructor(
    private val account: Database.Account,
    private val withdrawalLimiter: WithdrawalLimiter,
    private val outputter: Outputter,
    @MinimumBalance private val minimumBalance: BigDecimal,
    @MaxWithdrawal private val maxWithdrawal: BigDecimal
) : SingleArgCommand {
    override fun handleArg(argument: String): Command.Result {
        val amount = BigDecimal(argument)
        val remainingWithdrawalLimit = withdrawalLimiter.remainingWithdrawalLimit
        if (amount > remainingWithdrawalLimit) {
            outputter.output(
                String.format(
                    "you may not withdraw %s; you may withdraw %s more in this session",
                    amount, remainingWithdrawalLimit
                )
            )
        }

        return when {
            amount.signum() < 0 ||
                    amount > maxWithdrawal ||
                    account.balance - amount < minimumBalance -> Command.Result.invalid()
            else -> {
                account.withdraw(amount)
                withdrawalLimiter.recordWithdrawal(amount)
                outputter.output("your new balance is: " + account.balance)
                Command.Result(Command.Status.HANDLED, Optional.empty())
            }
        }
    }
}