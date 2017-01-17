package eu.darken.ommvplib.example.screens;


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

import eu.darken.ommvplib.example.ExampleApplicationMock;
import eu.darken.ommvplib.example.R;
import eu.darken.ommvplib.example.screens.debug.DebugFragment;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, true, false);

    @Mock
    MainComponent.Builder builder;

    @Mock
    MainPresenter presenter;

    private MainComponent mainActivityComponent = new MainComponent() {
        @Override
        public MainPresenter getPresenter() {
            return presenter;
        }

        @Override
        public void injectMembers(MainActivity instance) {
            final List<MainPagerAdapter.FragmentObj> fragmentObjs = Collections.singletonList(new MainPagerAdapter.FragmentObj(DebugFragment.class, "Debug"));
            instance.adapter = new MainPagerAdapter(instance.getSupportFragmentManager(), fragmentObjs);
        }
    };

    @Before
    public void setUp() {
        when(builder.build()).thenReturn(mainActivityComponent);

        ExampleApplicationMock app = (ExampleApplicationMock) InstrumentationRegistry.getTargetContext().getApplicationContext();
        app.putActivityComponentBuilder(builder, MainActivity.class);
    }

    @Test
    public void checkTextView() {
        activityRule.launchActivity(new Intent());

        onView(withId(R.id.fragment_text)).check(matches(withText("Debug Text!")));
    }
}
