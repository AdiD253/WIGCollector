package pl.defusadr.app.wigcollector.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pl.defusadr.app.wigcollector.ui.main.MainActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun bindMainActivity(): MainActivity
}
