package jp.takuji31.dagger2android.lifecycle

import android.os.Bundle

/**
 * Created by takuji on 2015/09/22.
 */
interface ActivityLifecycle {
    fun onSaveInstanceState(outState: Bundle)
    fun onRestoreInstanceState(savedInstanceStete: Bundle)
    fun onDestroy()
}