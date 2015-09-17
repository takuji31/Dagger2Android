package jp.takuji31.dagger2android.example

import jp.takuji31.dagger2android.lifecycle.OnDestroyLifecycle
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Takuji Nishibayashi
 */
@Singleton
public class A @Inject constructor() : OnDestroyLifecycle {
    var onDestroyCalled : Boolean = false
    override fun onDestroy() {
        onDestroyCalled = true
    }
}