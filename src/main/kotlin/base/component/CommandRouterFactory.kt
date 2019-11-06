package base.component

import dagger.Component
import base.CommandRouter
import base.module.HelloWorldModule
import base.module.LoginModule
import base.module.SystemOutModule

@Component(modules = [HelloWorldModule::class, LoginModule::class, SystemOutModule::class])
interface CommandRouterFactory {
    fun router(): CommandRouter
}