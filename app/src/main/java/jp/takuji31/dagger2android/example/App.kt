package jp.takuji31.dagger2android.example

import android.app.Application
import kotlin.properties.Delegates

/**
 * @author Takuji Nishibayashi
 */
public class App : Application() {
    val component : AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(context = this)).build()
    }
    override fun onCreate() {
        super.onCreate()
    }
}