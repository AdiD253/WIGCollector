package pl.defusadr.app.wigcollector.ui.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import pl.defusadr.app.wigcollector.R
import pl.defusadr.app.wigcollector.model.WIGModel
import pl.defusadr.app.wigcollector.ui.main.adapter.MainActivityAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), IMainActivityView {

    @Inject
    lateinit var presenter: IMainActivityPresenter<IMainActivityView>

    private val mainAdapter: MainActivityAdapter by lazy {
        MainActivityAdapter(
                mutableListOf(
                        WIGModel(symbol = "WIG", date = "", time = "", lowest = "0"),
                        WIGModel(symbol = "WIG20", date = "", time = "", lowest = "0"),
                        WIGModel(symbol = "WIG40", date = "", time = "", lowest = "0"),
                        WIGModel(symbol = "WIG80", date = "", time = "", lowest = "0")
                )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        recyclerViewData?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }

        btnSetUpdateTime.setOnClickListener { presenter.getWIGValuesWithPeriod(8) }
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
    }

    override fun parseWIGValue(type: Int, wigModel: WIGModel) {
        mainAdapter.wigValues[type] = wigModel
        mainAdapter.notifyItemChanged(type)
    }

    override fun showError(message: String?) {
        Snackbar.make(mainView, message.toString(), Snackbar.LENGTH_SHORT).show()
    }
}
