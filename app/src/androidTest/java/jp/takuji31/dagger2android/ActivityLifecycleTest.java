package jp.takuji31.dagger2android;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
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

        activity.finish();
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        instrumentation.waitForIdleSync();

        assertThat(a.getOnDestroyCalled()).isTrue();
        assertThat(a.getRestored()).isFalse();
        assertThat(a.getSaved()).isFalse();
    }
}
