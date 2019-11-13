package base.component

import base.CommandProcessor
import base.module.HelloWorldModule
import base.module.LoginModule
import base.module.SystemOutModule
import base.module.UserCommandsModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        HelloWorldModule::class,
        LoginModule::class,
        UserCommandsModule::class,
        SystemOutModule::class
    ]
)
interface CommandProcessorFactory {
    fun commandProcessor(): CommandProcessor
}