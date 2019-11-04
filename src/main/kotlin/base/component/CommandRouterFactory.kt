package base.component

import dagger.Component
import base.CommandRouter
import base.module.HelloWorldModule
import base.module.SystemOutModule

@Component(modules = [HelloWorldModule::class, SystemOutModule::class])
interface CommandRouterFactory {
    fun router(): CommandRouter
}