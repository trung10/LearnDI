package main

import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommandProcessor @Inject constructor(firstCommandRouter: CommandRouter) {

    companion object {
        private val commandRouterStack: Deque<CommandRouter> = ArrayDeque()
    }

    init {
        commandRouterStack.push(firstCommandRouter)
    }

    fun process(input: String): Command.Status {
        if (commandRouterStack.isEmpty())
            throw IllegalStateException("No command router is available!")

        val result: Command.Result = commandRouterStack.peek().route(input)

        when (result.status) {
            Command.Status.INPUT_COMPLETED -> {
                commandRouterStack.pop()
                return if (commandRouterStack.isEmpty())
                    Command.Status.INPUT_COMPLETED
                else Command.Status.HANDLED
            }

            Command.Status.HANDLED -> {
                if (result.nestedCommandRouter != null)
                    commandRouterStack.push(result.nestedCommandRouter)
            }

            Command.Status.INVALID -> {
                return result.status
            }
        }

        return Command.Status.HANDLED
    }

}