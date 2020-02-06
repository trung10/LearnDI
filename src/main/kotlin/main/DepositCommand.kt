package main

import javax.inject.Inject

class DepositCommand @Inject constructor(database: Database, outputter: Outputter) : Command{
    private val database: Database = database
    private val outputter: Outputter = outputter

    override fun key(): String {
        return "depositCommand"
    }

    override fun handleInput(input: List<String>): Command.Result {
        if (input.size != 2){
            return Command.Result.invalid()
        }

        val account = database.getAccount(input[0], input[1].toBigDecimal())
        outputter.output(account.username + " now has: " + account.balance)
        return Command.Result.handled()
    }
}