package main

import java.util.*
import javax.inject.Inject

class LoginCommand @Inject constructor(
    private val account: Optional<Database.Account>,
    private val outputter: Outputter,
    private val userCommandsRouterFactory: UserCommandsRouter.Factory )
    : SingleArgCommand() {

    override fun handleArg(username: String): Command.Result {
        if (account.isPresent) {
            val loggedInUser = account.get().username()
            outputter.output("$loggedInUser is already logged in")

            if (loggedInUser != username){
                outputter.output("run `logout` first before trying to log in another user")
            }
            return Command.Result.handled()
        } else {
            return Command.Result.enterNestedCommandSet(userCommandsRouterFactory.create(username).router())
        }
    }
}
