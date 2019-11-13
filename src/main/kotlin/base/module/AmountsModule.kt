package base.module

import base.qualifiers.MaxWithdrawal
import base.qualifiers.MinimumBalance
import dagger.Module
import dagger.Provides
import java.math.BigDecimal

@Module
class AmountsModule {
    @Provides
    @MinimumBalance
    fun minimumBalance(): BigDecimal {
        return BigDecimal.ZERO
    }

    @Provides
    @MaxWithdrawal
    fun maxWithdrawal(): BigDecimal {
        return BigDecimal(1000)
    }
}
