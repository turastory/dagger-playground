package base.database

import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Database @Inject constructor() {
    private val accounts: MutableMap<String, Account> = mutableMapOf()

    fun getAccount(username: String) =
        accounts.computeIfAbsent(username) { name ->
            Account(name)
        }

    class Account(
        val username: String,
        var balance: BigDecimal = BigDecimal.ZERO
    ) {
        fun deposit(amount: BigDecimal) {
            this.balance += amount
        }

        fun withdraw(amount: BigDecimal) {
            this.balance -= amount
        }
    }
}