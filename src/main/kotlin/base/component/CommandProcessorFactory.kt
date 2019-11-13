package base.component

import base.CommandProcessor
import base.module.AmountsModule
import base.module.HelloWorldModule
import base.module.LoginModule
import base.module.SystemOutModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        HelloWorldModule::class,
        LoginModule::class,
        SystemOutModule::class,
        AmountsModule::class,
        UserCommandsRouter.InstallationModule::class
    ]
)
interface CommandProcessorFactory {
    fun commandProcessor(): CommandProcessor
}