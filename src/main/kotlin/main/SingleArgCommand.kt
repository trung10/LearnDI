package main

abstract class SingleArgCommand : Command{

    override fun handleInput(input: List<String>): Command.Result {
        return if (input.isNotEmpty()) {
            handleArg(input[0])
        } else {
            Command.Result.invalid()
        }
    }

    /** Handles the single argument to the command. */
    abstract fun handleArg(arg: String): Command.Result
}