package jp.takuji31.dagger2android

/**
 * @author Takuji Nishibayashi
 */
public interface CreateComponentListener<T> {
    fun createComponent() : T
}