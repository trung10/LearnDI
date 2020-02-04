package main

import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class CommandRouter @Inject constructor(helloWorldCommand: HelloWorldCommand) {
    private val commands: Map<String, Command> = HashMap()

    init {
        (commands as HashMap).put(helloWorldCommand.key(), helloWorldCommand)
    }

    fun route(input: String): Command.Status {
        val splitInput: List<String> = split(input)

        if (commands.isEmpty()){
            return invalidCommand(input)
        }

        val commandKey: String = splitInput[0]
        val command: Command = commands[commandKey] ?: return invalidCommand(input)

        val status: Command.Status =
        command.handleInput(splitInput.subList(0, splitInput.size - 1))
        if (status == Command.Status.INVALID) {
            print("$commandKey: invalid arguments")
        }
        return status

    }

    private fun invalidCommand(input: String): Command.Status{
        print(String.format("couldn't understand \"%s\". please try again.", input))
        return Command.Status.INVALID
    }


    private fun split(string: String): List<String> {
        return string.split(" ")
    }
}
