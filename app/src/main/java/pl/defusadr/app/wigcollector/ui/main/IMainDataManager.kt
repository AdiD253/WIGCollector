package pl.defusadr.app.wigcollector.ui.main

import io.reactivex.Observable
import pl.defusadr.app.wigcollector.model.WIGModel

interface IMainDataManager {

    fun setCallPeriod(period: Long)
    fun getWIGValue(): Observable<WIGModel>
    fun getWIG20Value(): Observable<WIGModel>
    fun getMWIG40Value(): Observable<WIGModel>
    fun getSWIG80Value(): Observable<WIGModel>
}