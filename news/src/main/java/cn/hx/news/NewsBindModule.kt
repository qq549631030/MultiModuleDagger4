package cn.hx.news

import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = [AndroidInjectionModule::class])
abstract class NewsBindModule {
    @ContributesAndroidInjector
    abstract fun newsActivity(): NewsActivity
}