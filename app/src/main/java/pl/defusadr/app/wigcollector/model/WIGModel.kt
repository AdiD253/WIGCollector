package pl.defusadr.app.wigcollector.model

import com.google.gson.annotations.SerializedName

data class WIGModel(
        @SerializedName("Symbol")
var symbol: String? = null,

        @SerializedName("Data")
        var date: String?  = null,

        @SerializedName("Czas")
        var time: String? = null,

        @SerializedName("Otwarcie")
        var open: String? = null,

        @SerializedName("Najwyzszy")
        var highest: String? = null,

        @SerializedName("Najnizszy")
        var lowest: String? = null,

        @SerializedName("Zamkniecie")
        var close: String? = null,

        @SerializedName("Wolumen")
        var volume: String? = null,

        var type: Int? = 0
) {
        object TYPE {
                val WIG = 0
                val WIG20 = 1
                val MWIG40 = 2
                val SWIG80 = 3
        }
}

