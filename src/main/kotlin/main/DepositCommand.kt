package main

import java.math.BigDecimal
import javax.inject.Inject

class DepositCommand @Inject constructor(account: Account, outputter: Outputter) : BigDecimalCommand(outputter){

    override fun handleAmount(amount: BigDecimal) {
        account.deposit(amount)
        outputter.output(account.username + " now has: " + account.balance)
    }

    private val account: Account = account
    private val outputter: Outputter = outputter

    override fun key(): String {
        return "depositCommand"
    }

    /*override fun handleInput(input: List<String>): Command.Result {
        if (input.size != 2){
            return Command.Result.invalid()
        }

        val account = database.getAccount(input[0], input[1].toBigDecimal())
        outputter.output(account.username + " now has: " + account.balance)
        return Command.Result.handled()
    }*/
}