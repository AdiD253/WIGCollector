package pl.defusadr.app.wigcollector.api

import pl.defusadr.app.wigcollector.model.WIGModel
import pl.defusadr.app.wigcollector.service.WIGService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Inject

class WIGApi @Inject constructor() {

    private val wigService: WIGService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://stooq.pl/q/l/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()

        wigService = retrofit.create(WIGService::class.java)
    }

    fun getWIGValue(): Call<String> {
        return wigService.getWIGValue()
    }

    fun getWIG20Value(): Call<String> {
        return wigService.getWIG20Value()
    }

    fun getMWIG40Value(): Call<String> {
        return wigService.getMWIG40Value()
    }

    fun getSWIG80Value(): Call<String> {
        return wigService.getSWIG80Value()
    }
}
