package main

import dagger.Binds
import dagger.Module

@Module
interface InMemoryDatabaseModule {
    @Binds
    fun imMemory(inMemoryDatabase: InMemoryDatabase): Database
}