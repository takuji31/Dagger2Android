package jp.takuji31.dagger2android.component

import jp.takuji31.dagger2android.lifecycle.OnDestroyLifecycle

/**
 * @author Takuji Nishibayashi
 */
public interface ActivityComponent {
    val onDestroyLifecycle : OnDestroyLifecycle
}