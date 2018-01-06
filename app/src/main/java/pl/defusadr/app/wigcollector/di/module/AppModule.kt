package pl.defusadr.app.wigcollector.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import pl.defusadr.app.wigcollector.api.WIGApi
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideWIGApi(): WIGApi = WIGApi()

}