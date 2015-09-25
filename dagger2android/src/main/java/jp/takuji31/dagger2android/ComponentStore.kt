package jp.takuji31.dagger2android

/**
 * @author Takuji Nishibayashi
 */
public interface ComponentStore<T : Any> {
    var component: T?
}