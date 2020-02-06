package main

import java.math.BigDecimal
import java.util.function.Function
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Database @Inject constructor(){
    private val accounts: Map<String, Account> = HashMap()

    fun getAccount(username: String, balance: BigDecimal?): Account {
        return (accounts as HashMap).computeIfAbsent(username) { a ->
            if (balance != null) {
                Account(username, balance)
            } else {
                Account(username)
            }
        }
    }
}