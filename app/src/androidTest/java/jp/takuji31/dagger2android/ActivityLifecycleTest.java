package jp.takuji31.dagger2android;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import jp.takuji31.dagger2android.example.A;
import jp.takuji31.dagger2android.example.MainActivity;
import jp.takuji31.dagger2android.example.MainComponent;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by takuji on 2015/09/22.
 */
@RunWith(AndroidJUnit4.class)
public class ActivityLifecycleTest {

    @Rule
    public ActivityTestRule<MainActivity> testRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public ActivityTestRule<MainActivity> testRule2 = new ActivityTestRule<>(MainActivity.class, false, false);

    @Test
    public void testOnDestroy() throws Exception {
        MainActivity activity = testRule.getActivity();
        MainComponent component = activity.getComponent();
        A a = component.getA();
        //XXX: onSaveInstanceState called before test
        a.setSaved(false);
        assertThat(a.getOnDestroyCalled()).isFalse();
        assertThat(a.getRestored()).isFalse();
        assertThat(a.getSaved()).isFalse();

        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();

        activity.finish();
        instrumentation.waitForIdleSync();

        assertThat(a.getOnDestroyCalled()).isTrue();
        assertThat(a.getRestored()).isFalse();
        assertThat(a.getSaved()).isFalse();
    }

    @Test
    public void testSaveInstanceState() throws Exception {
        final MainActivity activity = testRule.getActivity();
        MainComponent component = activity.getComponent();
        A a = component.getA();
        //XXX: onSaveInstanceState called before test
        a.setSaved(false);
        assertThat(a.getOnDestroyCalled()).isFalse();
        assertThat(a.getRestored()).isFalse();
        assertThat(a.getSaved()).isFalse();

        final Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        final Bundle outState = new Bundle();
        instrumentation.runOnMainSync(new Runnable() {
            @Override
            public void run() {
                instrumentation.callActivityOnSaveInstanceState(activity, outState);
            }
        });
        instrumentation.waitForIdleSync();

        assertThat(a.getOnDestroyCalled()).isFalse();
        assertThat(a.getRestored()).isFalse();
        assertThat(a.getSaved()).isTrue();
    }
}
