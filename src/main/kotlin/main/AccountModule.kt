package main

import dagger.Module
import dagger.Provides

@Module
object AccountModule {
    @JvmStatic
    @Provides
    fun account(database: Database, @Username username: String): Database.Account {
        return database.getAccount(username)
    }
}