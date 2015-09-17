package jp.takuji31.dagger2android.example

import dagger.Component
import jp.takuji31.dagger2android.component.ActivityComponent
import javax.inject.Singleton

/**
 * @author Takuji Nishibayashi
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
public interface AppComponent : ActivityComponent {
    fun inject(t : MainActivity)
    fun inject(t : KotlinActivity)
}