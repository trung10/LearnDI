package main

import java.math.BigDecimal

interface Database {

    fun getAccount(username: String): Account

    interface Account{
        fun username(): String

        fun deposit(amount: BigDecimal)

        fun withdraw(amount: BigDecimal)

        fun balance(): BigDecimal
    }
}