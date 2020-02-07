package main

import java.math.BigDecimal

abstract class BigDecimalCommand constructor(outputter: Outputter): SingleArgCommand() {
    private val outputter: Outputter = outputter

    override fun handleArg(arg: String): Command.Result {
        val amount: BigDecimal? = tryParse(arg)

        if (amount == null){
            outputter.output("$arg is not a valid number")
        } else if (amount <= BigDecimal.ZERO){
            outputter.output("amount must be positive")
        } else {
            handleAmount(amount)
        }

        return Command.Result.handled()
    }

    private fun tryParse(arg: String): BigDecimal? {
        try {
            return BigDecimal(arg)
        } catch (e: NumberFormatException) {
            return null
        }
    }

    /** Handles the given (positive) {@code amount} of money. */
    protected abstract fun handleAmount(amount: BigDecimal)
}