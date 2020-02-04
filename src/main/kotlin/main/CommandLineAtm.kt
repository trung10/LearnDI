package main

import dagger.Component
import javax.inject.Inject

@Component
interface CommandRouterFactory {
    fun router(): CommandRouter
}

fun main() {
    //val commandRouter: CommandRouter = CommandRouter()
    //lateinit var commandRouter: CommandRouter

    val commandRouterFactory: CommandRouterFactory = DaggerCommandRouterFactory.create()
    val commandRouter: CommandRouter = commandRouterFactory.router()

    commandRouter.route("Hello asda bb")
}