package jp.takuji31.dagger2android.example

import dagger.Component
import jp.takuji31.dagger2android.component.ActivityLifecycleComponent
import javax.inject.Singleton

/**
 * @author Takuji Nishibayashi
 */
@Singleton
@Component(modules = arrayOf(MainModule::class))
public interface MainComponent : ActivityLifecycleComponent {
    val a : A
    fun inject(t : MainActivity)
    fun inject(t : KotlinActivity)
}