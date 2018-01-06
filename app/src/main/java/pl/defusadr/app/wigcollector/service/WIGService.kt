package pl.defusadr.app.wigcollector.service

import retrofit2.Call
import retrofit2.http.GET

interface WIGService {

    @GET("?s=wig&f=sd2t2ohlcv&h&e=csv")
    fun getWIGValue() : Call<String>

    @GET("?s=wig20&f=sd2t2ohlcv&h&e=csv")
    fun getWIG20Value() : Call<String>

    @GET("?s=mWIG40&f=sd2t2ohlcv&h&e=csv")
    fun getMWIG40Value() : Call<String>

    @GET("?s=sWIG80&f=sd2t2ohlcv&h&e=csv")
    fun getSWIG80Value() : Call<String>
}