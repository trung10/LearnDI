package main

import dagger.*
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

/*@Module
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
}*/

@Singleton
@Component(modules =  [UserCommandsModule::class, SystemOutModule::class] )
interface CommandProcessorFactory {
    fun processor(): CommandProcessor
}

@Subcomponent(modules = [UserCommandsModule::class])
interface UserCommandsRouter{
    fun router(): CommandRouter

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance account: Account): UserCommandsRouter
    }

    @Module(subcomponents = [UserCommandsModule::class])
    interface InstallationModule{}
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