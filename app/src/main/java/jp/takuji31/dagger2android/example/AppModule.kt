package jp.takuji31.dagger2android.example

import android.content.Context
import dagger.Module
import dagger.Provides
import jp.takuji31.dagger2android.lifecycle.Lifecycles
import jp.takuji31.dagger2android.lifecycle.OnDestroyLifecycle
import javax.inject.Singleton

/**
 * @author Takuji Nishibayashi
 */
@Module
public class AppModule(
        context: Context? = null
) {
    lateinit val context: Context
    @Provides @Singleton get

    init {
        this.context = context!!
    }

    @Provides @Singleton fun destroyLifecycle(a : A) : OnDestroyLifecycle {
        return Lifecycles.onDestroy(a)
    }
}