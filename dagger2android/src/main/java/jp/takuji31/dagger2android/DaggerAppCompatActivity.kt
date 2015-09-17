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
        super<AppCompatActivity>.onCreate(savedInstanceState)

        handleIntent(getIntent())

        delegate.onCreate()

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onPostCreate(savedInstanceState)
        delegate.onPostCreate()
    }

    override fun findFragment(): RetainFragment<T>? {
        @suppress("UNCHECKED_CAST")
        return getSupportFragmentManager().findFragmentByTag(TAG) as? RetainFragment<T>
    }

    override fun createFragment(): RetainFragment<T> {
        return RetainFragment()
    }

    override fun addFragment(fragment: RetainFragment<T>) {
        getSupportFragmentManager().beginTransaction().add(fragment, TAG).commit()
    }

    abstract fun handleIntent(intent: Intent)

    public class RetainFragment<T : Any> : Fragment(), ComponentStore<T> {
          override lateinit var component: T

        init {
            setRetainInstance(true)
        }
    }

    companion object {
        private val TAG = "DaggerRetainFragment"
    }

}