package main

import dagger.Binds
import dagger.BindsOptionalOf
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
interface CommandsModule {
    @Binds
    @IntoMap
    @StringKey("hello")
    fun helloWorldCommand(command: HelloWorldCommand): Command

    @Binds
    @IntoMap
    @StringKey("login")
    fun loginCommand(command: LoginCommand): Command

    /**
     * Declare an optional binding for {@link Account}. This allows other bindings to change their
     * behavior depending on whether an {@link Account} is bound in the current (sub)component.
     */
    @BindsOptionalOf
    fun loggedInAccount(): Database.Account
}