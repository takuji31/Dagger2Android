package jp.takuji31.dagger2android.example

import android.content.Context
import dagger.Module
import dagger.Provides
import jp.takuji31.dagger2android.lifecycle.ActivityLifecycle
import jp.takuji31.dagger2android.lifecycle.Lifecycles
import javax.inject.Singleton

/**
 * @author Takuji Nishibayashi
 */
@Module
public class MainModule(
        context: Context? = null
) {
    lateinit var context: Context
        @Provides @Singleton get

    init {
        this.context = context!!
    }

    @Provides @Singleton fun destroyLifecycle(a: A): ActivityLifecycle {
        return Lifecycles.activity(a)
    }
}