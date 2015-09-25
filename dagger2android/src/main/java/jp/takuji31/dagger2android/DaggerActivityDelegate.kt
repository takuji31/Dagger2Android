package jp.takuji31.dagger2android

import android.os.Bundle
import jp.takuji31.dagger2android.component.ActivityLifecycleComponent

/**
 * Dagger lifecycle delegation class for Activity
 * @author Takuji Nishibayashi
 */
class DaggerActivityDelegate<C : Any, F : ComponentStore<C>>(
        val createComponentListener: CreateComponentListener<C>,
        val fragmentStore: FragmentStore<F>
) {
    private lateinit var fragment: F

    val component: C
        get() {
            return fragment.component ?: {
                val component = createComponentListener.createComponent()
                fragment.component = component
                component
            }()
        }

    fun onCreate(savedInstanceState: Bundle? = null) {
        val f = fragmentStore.findFragment()
        if (f != null) {
            fragment = f
        } else {
            fragment = fragmentStore.createFragment()
        }
        val component: C = fragment.component ?: {
            val c = createComponentListener.createComponent()
            fragment.component = c
            c
        }()
        if (savedInstanceState != null && component is ActivityLifecycleComponent) {
            component.activityLifeCycle.onRestoreInstanceState(savedInstanceStete = savedInstanceState)
        }
    }

    fun onSaveInstanceState(outState: Bundle?) {
        val component = this.component
        if (outState != null && component is ActivityLifecycleComponent) {
            component.activityLifeCycle.onSaveInstanceState(outState = outState)
        }
    }

    fun onPostCreate() {
        if (!fragmentStore.hasFragment()) {
            fragmentStore.addFragment(fragment)
        }
    }

    fun onDestroy() {
        val component = component
        if (component is ActivityLifecycleComponent) {
            component.activityLifeCycle.onDestroy()
        }
    }
}