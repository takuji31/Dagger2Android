package jp.takuji31.dagger2android

import android.app.Application
import android.test.ApplicationTestCase

/**
 * [Testing Fundamentals](http://d.android.com/tools/testing/testing_android.html)
 */
public class ApplicationTest : ApplicationTestCase<Application>(javaClass<Application>())