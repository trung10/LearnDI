package main

import javax.inject.Inject

class CommandRouter @Inject constructor(
    private val commands: Map<String, @JvmSuppressWildcards Command>,
    private val outputter: Outputter) {

    fun route(input: String): Command.Result {
        val splitInput: List<String> = split(input)

        if (commands.isEmpty()){
            return invalidCommand(input)
        }

        val commandKey: String = splitInput[0]
        val command: Command = commands[commandKey] ?: return invalidCommand(input)

        val result: Command.Result =
        command.handleInput(splitInput.subList(1, splitInput.size))
        /*if (result == Command.Status.INVALID) {
            print("$commandKey: invalid arguments")
        }*/

        return if (result.status == Command.Status.INVALID){
            invalidCommand(input)
        } else {
            result
        }
    }

    private fun invalidCommand(input: String): Command.Result{
        outputter.output(String.format("Couldn't understand \"%s\". Please try again.", input))
        return Command.Result.invalid()
    }


    private fun split(string: String): List<String> {
        return string.split(" ")
    }
}
