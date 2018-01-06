//package pl.defusadr.app.wigcollector.ui.base
//
//import android.support.v7.app.AppCompatActivity
//import pl.defusadr.app.wigcollector.di.component.ActivityComponent
//import pl.defusadr.app.wigcollector.di.component.DaggerActivityComponent
//import pl.defusadr.app.wigcollector.di.module.ActivityModule
//
//
//abstract class BaseActivity : AppCompatActivity() {
//
//    val activityComponent: ActivityComponent by lazy {
//        DaggerActivityComponent.builder()
//                .activityModule(ActivityModule(this))
//                .build()
//    }
//}