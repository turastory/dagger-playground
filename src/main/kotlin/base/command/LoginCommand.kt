package base.command

import base.Command
import base.SingleArgCommand
import base.component.UserCommandsRouter
import base.database.Database
import base.output.Outputter
import java.util.*
import javax.inject.Inject

class LoginCommand @Inject constructor(
    private val optionalAccount: Optional<Database.Account>,
    private val database: Database,
    private val outputter: Outputter,
    private val userCommandsRouterFactory: UserCommandsRouter.Factory
) : SingleArgCommand {
    override fun handleArg(argument: String): Command.Result {
        if (optionalAccount.isPresent) {
            return Command.Result.handled()
        }

        val account = database.getAccount(argument)
        outputter.output("$argument is logged in with balance: ${account.balance}")
        return Command.Result.enterNestedCommandSet(
            userCommandsRouterFactory.create(account).router()
        )
    }
}