package base.module

import base.Command
import base.command.DepositCommand
import base.command.WithdrawCommand
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
internal abstract class UserCommandsModule {
    @Binds
    @IntoMap
    @StringKey("deposit")
    internal abstract fun depositCommand(command: DepositCommand): Command

    @Binds
    @IntoMap
    @StringKey("withdraw")
    internal abstract fun withdrawCommand(command: WithdrawCommand): Command
}