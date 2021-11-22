package main

import java.math.BigDecimal
import javax.inject.Inject

class WithdrawCommand @Inject constructor(
    private val account: Database.Account,
    private val outputter: Outputter,
    @MinimumBalance private val minimumBalance: BigDecimal,
    private val withdrawalLimiter: WithdrawalLimiter)
    : BigDecimalCommand(outputter) {

    override fun handleAmount(amount: BigDecimal) {
        val remainingWithdrawalLimit = withdrawalLimiter.remainingWithdrawalLimit
        if(amount > remainingWithdrawalLimit){
            outputter.output("you may not withdraw $amount; " +
                    "you may withdraw $remainingWithdrawalLimit more in this session")
            return
        }

        val newBalance = account.balance().subtract(amount)
        if(newBalance < minimumBalance){
            outputter.output("you don't have sufficient funds to withdraw $amount." +
                    " Our balance is ${account.balance()} and the minimum balance is $minimumBalance")
        } else {
            account.withdraw(amount)
            withdrawalLimiter.recordWithdraw(amount)
            outputter.output("your new balance is: ${account.balance()}")
        }

    }

}
