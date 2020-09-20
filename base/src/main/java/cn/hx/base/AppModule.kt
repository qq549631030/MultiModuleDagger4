package cn.hx.base

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
class AppModule {
    @IntoSet
    @Provides
    fun provideString(): String {
        return "app"
    }
}