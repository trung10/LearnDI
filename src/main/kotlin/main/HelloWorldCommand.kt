package main

import javax.inject.Inject

class HelloWorldCommand @Inject constructor(outputter: Outputter): Command {

    private val myOutputter: Outputter = outputter

    override fun handleInput(input: List<String>): Command.Result {
        if (input.isEmpty()) {
            return Command.Result.invalid()
        }

        myOutputter.output("world!")

        return Command.Result.handled()
    }

    override fun key(): String {
        return "Hello"
    }
}