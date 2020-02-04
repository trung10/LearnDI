package main

interface Command {
    /**
     * - deposit
     * - withdraw
     */
    fun key(): String

    fun handleInput(input: List<String>): Status

    enum class Status {
        INVALID,
        HANDLED
    }
}