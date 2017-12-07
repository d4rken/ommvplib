package eu.darken.ommvplib.example.screens;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.darken.ommvplib.example.R;
import eu.darken.ommvplib.injection.ComponentPresenterActivity;
import eu.darken.ommvplib.injection.ComponentSource;
import eu.darken.ommvplib.injection.ManualInjector;
import eu.darken.ommvplib.injection.fragment.support.HasManualSupportFragmentInjector;


public class MainActivity extends ComponentPresenterActivity<MainPresenter.View, MainPresenter, MainComponent>
        implements MainPresenter.View, HasManualSupportFragmentInjector {

    @Inject ComponentSource<Fragment> componentSource;

    @BindView(R.id.container) ViewGroup container;
    @BindView(R.id.bindcounter) TextView bindCounter;

    @Override
    public Class<? extends MainPresenter> getTypeClazz() {
        return MainPresenter.class;
    }

    @Override
    public ManualInjector<Fragment> supportFragmentInjector() {
        return componentSource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void showFragment(Class<? extends Fragment> fragmentClass) {
        final Fragment fragment = Fragment.instantiate(this, fragmentClass.getName());
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commitNow();
    }

    @Override
    public void showBinderCounter(int count) {
        bindCounter.setText(String.format(Locale.US, "Activity Rotation Count: %d", count));
    }
}
