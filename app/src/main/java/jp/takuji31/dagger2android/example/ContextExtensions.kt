package jp.takuji31.dagger2android.example

import android.content.Context

/**
 * @author Takuji Nishibayashi
 */

val Context.appComponent : AppComponent
    get() = (applicationContext as App).component
