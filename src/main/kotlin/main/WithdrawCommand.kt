package main

import java.math.BigDecimal
import javax.inject.Inject

class WithdrawCommand @Inject constructor(
    private val account: Database.Account,
    private val outputter: Outputter)
    : BigDecimalCommand(outputter) {

    override fun handleAmount(amount: BigDecimal) {
        account.withdraw(amount)
        outputter.output("your new balance is: ${account.balance()}")
    }

}
