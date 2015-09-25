package jp.takuji31.dagger2android.lifecycle

import android.os.Bundle

/**
 * @author Takuji Nishibayashi
 */
object Lifecycles {
    @JvmStatic
    fun activity(vararg lifecycles: ActivityLifecycle): ActivityLifecycle {
        return object : ActivityLifecycle {
            override fun onDestroy() {
                lifecycles.forEach { it.onDestroy() }
            }

            override fun onSaveInstanceState(outState: Bundle) {
                lifecycles.forEach { it.onSaveInstanceState(outState = outState) }
            }

            override fun onRestoreInstanceState(savedInstanceStete: Bundle) {
                lifecycles.forEach { it.onRestoreInstanceState(savedInstanceStete = savedInstanceStete) }
            }
        }
    }
}