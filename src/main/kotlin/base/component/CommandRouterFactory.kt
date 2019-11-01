package base.component

import dagger.Component
import base.CommandRouter

@Component
interface CommandRouterFactory {
    fun router(): CommandRouter
}