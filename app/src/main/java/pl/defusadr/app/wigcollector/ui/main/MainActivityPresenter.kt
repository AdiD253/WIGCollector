package pl.defusadr.app.wigcollector.ui.main

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityPresenter <V : IMainActivityView> @Inject constructor(
        private val dataManager: MainDataManager
) : IMainActivityPresenter<V> {

    private var view: V? = null
    private var disposable: CompositeDisposable = CompositeDisposable()

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        disposable.clear()
        this.view = null
    }

    override fun getWIGValuesWithPeriod(period: Long) {
        dataManager.setCallPeriod(period)
        disposable.add(
                Observable.merge(
                        dataManager.getWIGValue(),
                        dataManager.getWIG20Value(),
                        dataManager.getMWIG40Value(),
                        dataManager.getSWIG80Value()
                )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                                onError = {
                                    view?.showError(it.message)

                                },
                                onNext = {
                                    val type = it.type

                                    if (type != null)
                                        view?.parseWIGValue(type, it)
                                }
                        )

        )
    }
}