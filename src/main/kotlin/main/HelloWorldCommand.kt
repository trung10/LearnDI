package main

import javax.inject.Inject

class HelloWorldCommand @Inject constructor (private val outputter: Outputter): Command {

    override fun handleInput(input: List<String>): Command.Result {
        outputter.output("welcome to our service")
        return Command.Result.handled()
    }
}