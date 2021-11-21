package main

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [CommandsModule::class,
        InMemoryDatabaseModule::class,
        UserCommandsRouter.InstallationModule::class,
        SystemOutModule::class] )
interface CommandProcessorFactory {
    fun processor(): CommandProcessor

    companion object {
        fun create(): CommandProcessorFactory = DaggerCommandProcessorFactory.create()
    }
}