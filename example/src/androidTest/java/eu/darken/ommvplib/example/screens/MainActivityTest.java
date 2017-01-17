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

import eu.darken.ommvplib.example.ExampleApplicationMock;

import static org.mockito.ArgumentMatchers.any;
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

    @Mock
    MainPagerAdapter adapter;

    private MainComponent mainActivityComponent = new MainComponent() {
        @Override
        public MainPresenter getPresenter() {
            return presenter;
        }

        @Override
        public void injectMembers(MainActivity instance) {
            instance.adapter = null;
        }
    };

    @Before
    public void setUp() {
        when(builder.build()).thenReturn(mainActivityComponent);
        when(builder.activityModule(any(MainModule.class))).thenReturn(builder);

        ExampleApplicationMock app = (ExampleApplicationMock) InstrumentationRegistry.getTargetContext().getApplicationContext();
        app.putActivityComponentBuilder(builder, MainActivity.class);
    }

    @Test
    public void checkTextView() {

        activityRule.launchActivity(new Intent());

    }
}
