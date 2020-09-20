package cn.hx.news

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet

@Module
class NewsModule {
    @IntoSet
    @Provides
    fun provideString(): String {
        return "news"
    }
}