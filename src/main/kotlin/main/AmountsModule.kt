package main

import dagger.Module
import dagger.Provides
import java.math.BigDecimal

@Module
object AmountsModule {
    @Provides
    @MinimumBalance
    fun minimumBalance() = BigDecimal.ZERO

    @Provides
    @MaximumWithdrawal
    fun maximumWithdrawal() = BigDecimal(500)
}