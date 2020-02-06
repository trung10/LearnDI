package main

import java.util.*

interface Command {

    fun handleInput(input: List<String>): Result

    class Result(val status: Status, val nestedCommandRouter: Optional<CommandRouter>){
        companion object {
            fun enterNestedCommandSet(nestedCommandRouter: CommandRouter): Result{
                return Result(Status.HANDLED, Optional.of(nestedCommandRouter))
            }

            fun invalid(): Result{
                return Result(Status.INVALID, Optional.empty())
            }

            fun handled(): Result {
                return Result(Status.HANDLED, Optional.empty())
            }

            fun inputCompleted(): Result {
                return Result(Status.INPUT_COMPLETED, Optional.empty())
            }
        }
    }

    /**
     * - deposit
     * - withdraw
     */
    fun key(): String

    //fun handleInput(input: List<String>): Status

    enum class Status {
        INVALID,
        HANDLED,
        INPUT_COMPLETED
    }
}