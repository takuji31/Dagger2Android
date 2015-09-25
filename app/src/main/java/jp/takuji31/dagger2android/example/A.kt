package jp.takuji31.dagger2android.example

import android.os.Bundle
import jp.takuji31.dagger2android.lifecycle.ActivityLifecycle
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Takuji Nishibayashi
 */
@Singleton
public class A @Inject constructor() : ActivityLifecycle {
    var onDestroyCalled = false
    var saved = false
    var restored = false

    override fun onSaveInstanceState(outState: Bundle) {
        saved = true
    }

    override fun onRestoreInstanceState(savedInstanceStete: Bundle) {
        restored = true
    }

    override fun onDestroy() {
        onDestroyCalled = true
    }
}