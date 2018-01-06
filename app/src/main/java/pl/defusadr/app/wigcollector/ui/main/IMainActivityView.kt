package pl.defusadr.app.wigcollector.ui.main

import pl.defusadr.app.wigcollector.model.WIGModel

interface IMainActivityView {

    fun showError(message: String?)
    fun parseWIGValue(type: Int, wigModel: WIGModel)
}