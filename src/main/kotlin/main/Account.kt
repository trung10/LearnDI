package main

import java.math.BigDecimal

data class Account (val username: String, val balance: BigDecimal = BigDecimal.ZERO ) {
    fun deposit(amount: BigDecimal){

    }
}