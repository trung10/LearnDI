package main

import dagger.BindsInstance
import dagger.Module
import dagger.Subcomponent

// The @Subcomponent annotation defines what modules Dagger should know about
// when creating instances for this subcomponent only. Just like @Component,
// it can take a list of modules: we’ve moved the UserCommandsModule
// that declares our DepositCommand here.
@Subcomponent(modules = [AccountModule::class, UserCommandsModule::class])
interface UserCommandsRouter{

    // method declares what object we want Dagger to create.
    fun router(): CommandRouter


    // The @Subcomponent.Factory annotation annotates a factory type for this subcomponent. I
    // t’s an interface we define.
    //
    //    It has a single method that creates an instance of the subcomponent.
    //    That method has a @BindsInstance parameter,
    //    which tells Dagger that the Account instance we pass as an argument should be requestable by any @Inject constructor,
    //    @Binds method, or @Provides method in this subcomponent.
    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance @Username username: String): UserCommandsRouter
    }

    // We have a module that declares the subcomponent.
    // Including this module in another component will make the @Subcomponent.Factory available there.
    // That’s our bridge between the two components.



    // If we include UserCommandsRouter.InstallationModule in our @Component annotation,
    // we can start to use it in LoginCommand:
    @Module(subcomponents = [UserCommandsRouter::class])
    interface InstallationModule{}
}