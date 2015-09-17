package jp.takuji31.dagger2android

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlin.properties.Delegates

/**
 * @author Takuji Nishibayashi
 */
public abstract class DaggerAppCompatActivity<T : Any>() : AppCompatActivity(), CreateComponentListener<T>, FragmentStore<DaggerAppCompatActivity.RetainFragment<T>> {

    val delegate: DaggerActivityDelegate<T, RetainFragment<T>> = DaggerActivityDelegate(this, this)

    public val component: T
        get() = delegate.component

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handleIntent(intent)

        delegate.onCreate()

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        delegate.onPostCreate()
    }

    override fun findFragment(): RetainFragment<T>? {
        @Suppress("UNCHECKED_CAST")
        return supportFragmentManager.findFragmentByTag(TAG) as? RetainFragment<T>
    }

    override fun createFragment(): RetainFragment<T> {
        return RetainFragment()
    }

    override fun addFragment(fragment: RetainFragment<T>) {
        supportFragmentManager.beginTransaction().add(fragment, TAG).commit()
    }

    abstract fun handleIntent(intent: Intent)

    public class RetainFragment<T : Any> : Fragment(), ComponentStore<T> {
          override lateinit var component: T

        init {
            retainInstance = true
        }
    }

    companion object {
        private val TAG = "DaggerRetainFragment"
    }

}