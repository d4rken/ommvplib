package eu.darken.ommvplib.example.screens;


import android.app.Activity;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Collections;
import java.util.List;

import dagger.android.AndroidInjector;
import eu.darken.ommvplib.example.ExampleApplicationMock;
import eu.darken.ommvplib.example.R;
import eu.darken.ommvplib.example.screens.debug.DebugFragment;
import eu.darken.ommvplib.injection.ManualInjector;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, true, false);

    @Mock MainPresenter presenter;
    @Mock MainComponent mainComponent;
    private ExampleApplicationMock app;

    @Before
    public void setUp() {
        app = (ExampleApplicationMock) InstrumentationRegistry.getTargetContext().getApplicationContext();
        when(mainComponent.getPresenter()).thenReturn(presenter);
        app.setActivityComponentSource(new Injector());
    }

    public class Injector implements ManualInjector<Activity> {

        @Override
        public AndroidInjector get(Activity instance) {
            return mainComponent;
        }

        @Override
        public void inject(Activity instance) {
        }
    }

    @Test
    public void checkTextView() throws Throwable {
        activityRule.launchActivity(new Intent());
        final List<MainPagerAdapter.FragmentObj> fragmentObjs = Collections.singletonList(new MainPagerAdapter.FragmentObj(DebugFragment.class, "Debug"));
        activityRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activityRule.getActivity().showFragments(fragmentObjs);
            }
        });
        onView(withId(R.id.fragment_text)).check(matches(withText("Debug Text!")));
    }


}
