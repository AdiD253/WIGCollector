package pl.defusadr.app.wigcollector.ui.main

import pl.defusadr.app.wigcollector.di.PerActivity

@PerActivity
interface IMainActivityPresenter<V : IMainActivityView> {

    fun attachView(view: V)
    fun detachView()
    fun getWIGValuesWithPeriod(period: Long)
}