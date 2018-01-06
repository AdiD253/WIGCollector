package pl.defusadr.app.wigcollector.di.module

import dagger.Binds
import dagger.Module
import pl.defusadr.app.wigcollector.ui.main.*

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun provideMainActivityPresenter(presenter: MainActivityPresenter<IMainActivityView>)
            : IMainActivityPresenter<IMainActivityView>

    @Binds
    abstract fun provideDataManager(dataManager: MainDataManager): IMainDataManager
}