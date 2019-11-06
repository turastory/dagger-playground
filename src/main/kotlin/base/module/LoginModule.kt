package base.module

import base.Command
import base.command.LoginCommand
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
interface LoginModule {
    @Binds
    @IntoMap
    @StringKey("login")
    fun loginCommand(command: LoginCommand): Command
}