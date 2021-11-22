package main

import java.math.BigDecimal
import javax.inject.Inject

@PerSession
class WithdrawalLimiter @Inject constructor(
    @MaximumWithdrawal maximumWithdrawal: BigDecimal){

    var  remainingWithdrawalLimit: BigDecimal = maximumWithdrawal

    fun recordDeposit(amount: BigDecimal){
        remainingWithdrawalLimit = remainingWithdrawalLimit.add(amount)
    }

    fun recordWithdraw(amount: BigDecimal){
        remainingWithdrawalLimit = remainingWithdrawalLimit.subtract(amount)
    }
}