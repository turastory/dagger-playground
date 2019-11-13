package base

import base.qualifiers.MaxWithdrawal
import base.scope.PerSession
import java.math.BigDecimal
import javax.inject.Inject

@PerSession
class WithdrawalLimiter @Inject constructor(
    @MaxWithdrawal withdrawalLimit: BigDecimal
) {
    var remainingWithdrawalLimit: BigDecimal = withdrawalLimit

    fun recordDeposit(amount: BigDecimal) {
        remainingWithdrawalLimit += amount
    }

    fun recordWithdrawal(amount: BigDecimal) {
        remainingWithdrawalLimit -= amount
    }
}
