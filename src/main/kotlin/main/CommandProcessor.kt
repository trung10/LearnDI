package main

import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommandProcessor @Inject constructor(firstCommandRouter: CommandRouter){
    private val commandRouterStack: Deque<CommandRouter> = ArrayDeque()

    init {
        commandRouterStack.push(firstCommandRouter)
    }

    fun process(input: String): Command.Status {
        val result: Command.Result = commandRouterStack.peek().route(input)
        if (result.status.equals(Command.Status.INPUT_COMPLETED)) {
            commandRouterStack.pop()
            return if (commandRouterStack.isEmpty()){
                Command.Status.INPUT_COMPLETED
            } else {
                Command.Status.HANDLED
            }
        }

        result.nestedCommandRouter.ifPresent(commandRouterStack::push)
        return result.status
    }

}