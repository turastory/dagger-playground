package base.module

import base.output.Outputter
import dagger.Module
import dagger.Provides

@Module
class SystemOutModule {
    @Provides
    fun provideOutputter(): Outputter {
        return object : Outputter {
            override fun output(string: String) {
                System.out.println(string)
            }
        }
    }
}