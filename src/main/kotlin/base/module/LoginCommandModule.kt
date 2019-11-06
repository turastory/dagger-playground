package base.module

import base.Command
import base.command.LoginCommand
import dagger.Binds
import dagger.Module

@Module
interface LoginCommandModule {
    @Binds
    fun loginCommand(command: LoginCommand): Command
}