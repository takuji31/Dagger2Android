package jp.takuji31.dagger2android

import kotlin.properties.Delegates

/**
 * Dagger lifecycle delegation class for Activity
 * @author Takuji Nishibayashi
 */
public class DaggerActivityDelegate<C : Any, F : ComponentStore<C>>(
        val createComponentListener: CreateComponentListener<C>, val fragmentStore: FragmentStore<F>) {
    private lateinit var fragment: F

    public val component: C
        get() = fragment.component

    public fun onCreate() {

        @Suppress("UNCHECKED_CAST")
        val f = fragmentStore.findFragment()
        if (f != null) {
            fragment = f
        } else {
            fragment = fragmentStore.createFragment()
            fragment.component = createComponentListener.createComponent()
        }
    }

    public fun onPostCreate() {
        if (!fragmentStore.hasFragment()) {
            fragmentStore.addFragment(fragment)
        }
    }
}