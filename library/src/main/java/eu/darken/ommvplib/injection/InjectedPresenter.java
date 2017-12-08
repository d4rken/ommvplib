package eu.darken.ommvplib.injection;

import android.app.Activity;
import android.app.Fragment;

import eu.darken.ommvplib.base.Presenter;
import eu.darken.ommvplib.base.PresenterSource;
import eu.darken.ommvplib.injection.activity.HasManualActivityInjector;
import eu.darken.ommvplib.injection.fragment.HasManualFragmentInjector;
import eu.darken.ommvplib.injection.fragment.support.HasManualSupportFragmentInjector;


public class InjectedPresenter<
        ViewT extends Presenter.View,
        PresenterT extends ComponentPresenter<ViewT, ComponentT>,
        ComponentT extends PresenterComponent<ViewT, PresenterT>>
        implements PresenterSource<PresenterT> {

    private final Activity activity;
    private final android.support.v4.app.Fragment supportFragment;
    private final Fragment fragment;

    public InjectedPresenter(Activity activity) {
        this.activity = activity;
        this.supportFragment = null;
        this.fragment = null;
    }

    public InjectedPresenter(android.support.v4.app.Fragment supportFragment) {
        this.supportFragment = supportFragment;
        this.activity = null;
        this.fragment = null;
    }

    public InjectedPresenter(Fragment fragment) {
        this.fragment = fragment;
        this.activity = null;
        this.supportFragment = null;
    }

    @Override
    public PresenterT create() {
        ComponentT component;
        if (activity != null) {
            HasManualActivityInjector injectorSource = (HasManualActivityInjector) activity.getApplication();
            //noinspection unchecked
            component = (ComponentT) injectorSource.activityInjector().get(activity);
        } else if (supportFragment != null) {
            HasManualSupportFragmentInjector injectorSource = (HasManualSupportFragmentInjector) supportFragment.getActivity();
            //noinspection unchecked,ConstantConditions
            component = (ComponentT) injectorSource.supportFragmentInjector().get(supportFragment);
        } else if (fragment != null) {
            HasManualFragmentInjector injectorSource = (HasManualFragmentInjector) fragment.getActivity();
            //noinspection unchecked
            component = (ComponentT) injectorSource.fragmentInjector().get(fragment);
        } else {
            throw new RuntimeException("No injection source.");
        }

        final PresenterT presenter = component.getPresenter();
        presenter.setComponent(component);

        return presenter;
    }
}
