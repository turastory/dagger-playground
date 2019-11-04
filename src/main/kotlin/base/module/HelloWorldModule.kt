package base.module

import base.Command
import base.command.HelloWorldCommand
import dagger.Binds
import dagger.Module

@Module
interface HelloWorldModule {
    @Binds
    fun helloWorld(command: HelloWorldCommand): Command
}