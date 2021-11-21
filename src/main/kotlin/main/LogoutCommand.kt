package main

import javax.inject.Inject

class LogoutCommand @Inject constructor(
    private val account: Database.Account,
    private val outputter: Outputter
    ) : Command {

    override fun handleInput(input: List<String>): Command.Result {
        if (input.isEmpty())
            return Command.Result.invalid()

        outputter.output("logged out ${account.username()}")
        return Command.Result.inputCompleted()
    }

}