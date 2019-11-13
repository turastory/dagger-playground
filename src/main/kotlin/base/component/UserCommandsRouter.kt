package base.component

import base.CommandRouter
import base.database.Database
import base.module.UserCommandsModule
import dagger.BindsInstance
import dagger.Module
import dagger.Subcomponent

@Subcomponent(modules = [UserCommandsModule::class])
interface UserCommandsRouter {
    // CommandRouter is hidden from its parent component.
    fun router(): CommandRouter

    // Factory is used to create this subcomponent.
    // This factory can be injected in parent component
    @Subcomponent.Factory
    interface Factory {
        // Like the name implies, @BindsInstance bind an instance of the type, "within the subcomponent".
        // -> Modules defined in @Subcomponent annotation can access this type.
        // The instance of the type should be provided from outside.
        fun create(@BindsInstance account: Database.Account): UserCommandsRouter
    }

    @Module(subcomponents = [UserCommandsRouter::class])
    interface InstallationModule
}