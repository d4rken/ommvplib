package eu.darken.ommvplib.example.screens;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.darken.ommvplib.base.OMMVPLib;
import eu.darken.ommvplib.example.R;
import eu.darken.ommvplib.injection.ComponentSource;
import eu.darken.ommvplib.injection.InjectedPresenter;
import eu.darken.ommvplib.injection.ManualInjector;
import eu.darken.ommvplib.injection.PresenterInjectionCallback;
import eu.darken.ommvplib.injection.fragment.support.HasManualSupportFragmentInjector;


public class MainActivity extends AppCompatActivity
        implements MainPresenter.View, HasManualSupportFragmentInjector {

    @Inject ComponentSource<Fragment> componentSource;

    @BindView(R.id.container) ViewGroup container;
    @BindView(R.id.bindcounter) TextView bindCounter;

    private OMMVPLib.InstanceStatePublisher statePublisher;

    @Override
    public ManualInjector<Fragment> supportFragmentInjector() {
        return componentSource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statePublisher = new OMMVPLib.InstanceStatePublisher();
        statePublisher.onCreate(savedInstanceState);
        OMMVPLib.<MainPresenter.View, MainPresenter>builder()
                .statePublisher(statePublisher)
                .presenterCallback(new PresenterInjectionCallback<>(this))
                .presenterSource(new InjectedPresenter<>(this))
                .attach(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        statePublisher.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void showFragment(Class<? extends Fragment> fragmentClass) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragment == null) {
            fragment = Fragment.instantiate(this, fragmentClass.getName());
            getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commitNow();
        }
    }

    @Override
    public void showBinderCounter(int count) {
        bindCounter.setText(String.format(Locale.US, "Activity Rotation Count: %d", count));
    }
}
