package jp.takuji31.dagger2android

/**
 * @author Takuji Nishibayashi
 */
public interface FragmentStore<T : Any> {
    fun findFragment(): T?
    fun createFragment(): T
    fun hasFragment(): Boolean {
        return findFragment() != null
    }
    fun addFragment(fragment: T)
}