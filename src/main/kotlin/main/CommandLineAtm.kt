package main

import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import javax.inject.Inject
import javax.inject.Singleton

/*@Singleton
@Component(
    modules = [HelloWorldModule::class,
        SystemOutModule::class,
        LoginCommandModule::class,
        DepositCommandModule::class]
)
interface CommandRouterFactory {
    fun router(): CommandRouter
}*/

@Module
class SystemOutModule {

    @Provides
    fun textOutputter(): Outputter {
        //return System.out::println
        return OutputterImp()
    }
}

@Module
abstract class HelloWorldModule {
    @Binds
    @IntoMap
    @StringKey("hello")
    abstract fun helloWorldCommand(command: HelloWorldCommand): Command
}

@Module
abstract class LoginCommandModule {
    @Binds
    @IntoMap
    @StringKey("login")
    abstract fun loginCommand(command: LoginCommand): Command
}

@Module
abstract class DepositCommandModule {
    @Binds
    @IntoMap
    @StringKey("deposit")
    abstract fun loginCommand(command: DepositCommand): Command
}

@Singleton
@Component(modules =  [DepositCommandModule::class, LoginCommandModule::class, HelloWorldModule::class, SystemOutModule::class] )
interface CommandProcessorFactory {
    fun processor(): CommandProcessor
}

fun main() {
    //val commandRouter: CommandRouter = CommandRouter()
    //lateinit var commandRouter: CommandRouter

    //val commandRouterFactory: CommandRouterFactory = DaggerCommandRouterFactory.create()
    //val commandRouter: CommandRouter = commandRouterFactory.router()

    //commandRouter.route("hello aaa")
    //commandRouter.route("login jesse")
    //commandRouter.route("deposit colin 2")
    //commandRouter.route("login colin")

    val commandProcessor : CommandProcessor = DaggerCommandProcessorFactory.create().processor()

    commandProcessor.process("deposit colin 2")
    commandProcessor.process("login colin")
}