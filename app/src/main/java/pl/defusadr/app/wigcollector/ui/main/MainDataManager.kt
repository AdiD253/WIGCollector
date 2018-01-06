package pl.defusadr.app.wigcollector.ui.main

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.schedulers.Schedulers
import pl.defusadr.app.wigcollector.api.WIGApi
import pl.defusadr.app.wigcollector.model.WIGModel
import retrofit2.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainDataManager @Inject constructor(private val wigApi: WIGApi) : IMainDataManager {

    private var period = 0L

    override fun setCallPeriod(period: Long) {
        this.period = period
    }

    override fun getWIGValue(): Observable<WIGModel> =
            Observable.create<WIGModel> { emitter ->
                callApiByType(WIGModel.TYPE.WIG, emitter)
            }

    override fun getWIG20Value(): Observable<WIGModel> =
            Observable.create<WIGModel> { emitter ->
                callApiByType(WIGModel.TYPE.WIG20, emitter)
            }

    override fun getMWIG40Value(): Observable<WIGModel> =
            Observable.create<WIGModel> { emitter ->
                callApiByType(WIGModel.TYPE.MWIG40, emitter)
            }

    override fun getSWIG80Value(): Observable<WIGModel> =
            Observable.create<WIGModel> { emitter ->
                callApiByType(WIGModel.TYPE.SWIG80, emitter)
            }

    private fun callApiByType(type: Int, emitter: ObservableEmitter<WIGModel>) {
        Schedulers.newThread().schedulePeriodicallyDirect(
                {
                    val response: Response<String> = when (type) {
                        WIGModel.TYPE.WIG -> wigApi.getWIGValue().execute()
                        WIGModel.TYPE.WIG20 -> wigApi.getWIG20Value().execute()
                        WIGModel.TYPE.MWIG40 -> wigApi.getMWIG40Value().execute()
                        else -> wigApi.getSWIG80Value().execute()
                    }

                    if (response.isSuccessful) {
                        emitter.onNext(parseCSVtoWIGModel(response.body(), type))
                    } else {
                        emitter.onError(Throwable(response.message()))
                    }
                },
                0, period, TimeUnit.SECONDS
        )
    }

    private fun parseCSVtoWIGModel(response: String?, type: Int): WIGModel {

        val wigModel = WIGModel()

        response?.let {
            val rows = it.split("\r\n")
            val positions = rows[1].split(",")

            wigModel.symbol = positions[0]
            wigModel.date = positions[1]
            wigModel.time = positions[2]
            wigModel.open = positions[3]
            wigModel.highest = positions[4]
            wigModel.lowest = positions[5]
            wigModel.close = positions[6]
            wigModel.volume = positions[7]
            wigModel.type = type
        }
        return wigModel
    }
}
