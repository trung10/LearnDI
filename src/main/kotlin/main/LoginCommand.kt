package main

import javax.inject.Inject

class LoginCommand @Inject constructor(database: Database,
                                       outputter: Outputter,
                                       userCommandsRouterFactory: UserCommandsRouter.Factory ) : SingleArgCommand() {
    private val myOutputter: Outputter = outputter
    private val database: Database = database
    private val userCommandsRouterFactory: UserCommandsRouter.Factory = userCommandsRouterFactory

    override fun handleArg(username: String): Command.Result {
        val account = database.getAccount(username, null)
        myOutputter.output(username + " is logged in with balance: " + account.balance)

        return Command.Result.enterNestedCommandSet(userCommandsRouterFactory.create(account).router())
    }

    override fun key(): String {
        return "login"
    }
}