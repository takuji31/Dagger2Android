package jp.takuji31.dagger2android.example

import android.app.Activity
import android.os.Bundle

/**
 * @author Takuji Nishibayashi
 */
public class KotlinActivity : Activity() {
    lateinit val b : B
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}