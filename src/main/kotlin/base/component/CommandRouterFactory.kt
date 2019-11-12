package base.component

import dagger.Component
import base.CommandRouter
import base.module.HelloWorldModule
import base.module.LoginModule
import base.module.SystemOutModule
import base.module.UserCommandsModule
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
interface CommandRouterFactory {
    fun router(): CommandRouter
}