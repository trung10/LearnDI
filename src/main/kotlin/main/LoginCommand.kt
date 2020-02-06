package main

import javax.inject.Inject

class LoginCommand @Inject constructor(database: Database, outputter: Outputter) : SingleArgCommand() {
    private val myOutputter: Outputter = outputter
    private val database: Database = database

    override fun handleArg(username: String): Command.Result {
        val account = database.getAccount(username, null)
        myOutputter.output(username + " is logged in with balance: " + account.balance)

        return Command.Result.handled()
    }

    override fun key(): String {
        return "login"
    }
}