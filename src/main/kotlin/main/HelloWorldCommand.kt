package main

import javax.inject.Inject

class HelloWorldCommand @Inject constructor(): Command {

    override fun handleInput(input: List<String>): Command.Status {
        if (input.isEmpty()) {
            return Command.Status.INVALID
        }

        print("world!")

        return Command.Status.HANDLED
    }

    override fun key(): String {
        return "Hello"
    }

}