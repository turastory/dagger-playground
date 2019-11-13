package base.module

import base.Command
import base.command.HelloWorldCommand
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
interface HelloWorldModule {
    @Binds
    @IntoMap
    @StringKey("hello")
    fun helloWorld(command: HelloWorldCommand): Command
}