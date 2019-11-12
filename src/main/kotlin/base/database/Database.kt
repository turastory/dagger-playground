package base.database

import java.math.BigDecimal
import javax.inject.Inject

class Database @Inject constructor() {
    private val accounts: MutableMap<String, Account> = mutableMapOf()

    fun getAccount(username: String) =
        accounts.computeIfAbsent(username) { name ->
            Account(name)
        }

    class Account(
        val username: String,
        val balance: BigDecimal = BigDecimal.ZERO
    )
}