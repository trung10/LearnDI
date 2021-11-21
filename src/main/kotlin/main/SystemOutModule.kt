package main

import dagger.Module
import dagger.Provides

@Module
class SystemOutModule {
    @Provides
    fun textOutputter(): Outputter {
        return OutputterImp()
    }
}
