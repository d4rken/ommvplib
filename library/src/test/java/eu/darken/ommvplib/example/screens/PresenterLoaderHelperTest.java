package eu.darken.ommvplib.example.screens;

import android.app.LoaderManager;
import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import eu.darken.ommvplib.base.Presenter;
import eu.darken.ommvplib.base.PresenterLoaderHelper;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

@RunWith(MockitoJUnitRunner.class)
public class PresenterLoaderHelperTest {
    @Mock Context context;
    @Mock LoaderManager loaderManager;

    @Test
    public void testFetch() {
        final PresenterLoaderHelper<Presenter.View, Presenter<Presenter.View>> loaderHelper = new PresenterLoaderHelper<>(context, loaderManager, null);
        assertThat(loaderHelper, is(not(nullValue())));
    }
}
