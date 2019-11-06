package base.component

import dagger.Component
import base.CommandRouter
import base.module.HelloWorldModule
import base.module.LoginCommandModule
import base.module.SystemOutModule

@Component(modules = [LoginCommandModule::class, SystemOutModule::class])
interface CommandRouterFactory {
    fun router(): CommandRouter
}