package jp.takuji31.dagger2android.example

import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Takuji Nishibayashi
 */
@Singleton
public class B @Inject constructor(
        val a: A
) {
}