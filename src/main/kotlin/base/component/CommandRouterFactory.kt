package base.component

import dagger.Component
import base.CommandRouter
import base.module.HelloWorldModule

@Component(modules = [HelloWorldModule::class])
interface CommandRouterFactory {
    fun router(): CommandRouter
}