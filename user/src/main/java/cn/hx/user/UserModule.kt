package cn.hx.user

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
class UserModule {
    @IntoSet
    @Provides
    fun provideString(): String {
        return "user"
    }
}