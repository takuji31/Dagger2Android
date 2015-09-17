package jp.takuji31.dagger2android.lifecycle

/**
 * @author Takuji Nishibayashi
 */
public object Lifecycles {
    @JvmStatic
    fun onDestroy(vararg lifecycles: OnDestroyLifecycle): OnDestroyLifecycle {
        return object : OnDestroyLifecycle {
            override fun onDestroy() {
                lifecycles.forEach { onDestroy() }
            }
        }
    }
}