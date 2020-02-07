package main

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
interface UserCommandsModule {
    @Binds
    @IntoMap
    @StringKey("hello")
    abstract fun helloWorldCommand(command: HelloWorldCommand): Command

    @Binds
    @IntoMap
    @StringKey("login")
    abstract fun loginCommand(command: LoginCommand): Command

    @Binds
    @IntoMap
    @StringKey("deposit")
    abstract fun loginCommand(command: DepositCommand): Command
}