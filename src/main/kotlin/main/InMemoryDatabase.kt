package main

import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryDatabase @Inject constructor(): Database{
    private val accounts: Map<String, Database.Account> = HashMap()

    override fun getAccount(username: String): Database.Account {
        return (accounts as HashMap).computeIfAbsent(username) { name ->
            InMemoryAccount(name)
        }
    }

    companion object{
        private class InMemoryAccount(private val username: String): Database.Account{
            private var balance: BigDecimal = BigDecimal.ZERO

            override fun username(): String {
                return username
            }

            override fun deposit(amount: BigDecimal) {
                checkNonNegative(amount, "deposit")
                balance = balance.add(amount)
            }

            override fun withdraw(amount: BigDecimal) {
                checkNonNegative(amount, "withdraw")
                balance = balance.subtract(amount)
            }

            override fun balance(): BigDecimal {
                return balance
            }


            fun checkNonNegative(amount: BigDecimal, action: String){
                if (amount.signum() == -1) {// Negative number
                    throw IllegalArgumentException("Cannot $action negative amounts: $amount")
                }
            }
        }
    }
}