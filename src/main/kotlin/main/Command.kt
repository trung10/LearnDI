package main

interface Command {

    fun handleInput(input: List<String>): Result

    class Result(val status: Status, val nestedCommandRouter: CommandRouter? = null){
        companion object {
            fun enterNestedCommandSet(nestedCommandRouter: CommandRouter?): Result{
                return Result(Status.HANDLED, nestedCommandRouter)
            }

            fun invalid(): Result{
                return Result(Status.INVALID)
            }

            fun handled(): Result {
                return Result(Status.HANDLED)
            }

            fun inputCompleted(): Result {
                return Result(Status.INPUT_COMPLETED)
            }
        }
    }

    enum class Status {
        INVALID,
        HANDLED,
        INPUT_COMPLETED
    }
}