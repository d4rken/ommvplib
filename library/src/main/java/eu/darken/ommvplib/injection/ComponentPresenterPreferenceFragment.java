package eu.darken.ommvplib.injection;

import android.support.annotation.NonNull;

import eu.darken.ommvplib.base.Presenter;
import eu.darken.ommvplib.base.PresenterFactory;
import eu.darken.ommvplib.base.PresenterPreferenceFragment;
import eu.darken.ommvplib.injection.fragment.support.HasManualSupportFragmentInjector;
import eu.darken.ommvplib.injection.fragment.support.SupportFragmentComponent;

public abstract class ComponentPresenterPreferenceFragment<
        ViewT extends Presenter.View,
        PresenterT extends ComponentPresenter<ViewT, ComponentT>,
        ComponentT extends SupportFragmentComponent & PresenterComponent<ViewT, PresenterT>>
        extends PresenterPreferenceFragment<ViewT, PresenterT>
        implements PresenterFactory<PresenterT> {

    @NonNull
    @Override
    protected PresenterFactory<PresenterT> getPresenterFactory() {
        return this;
    }

    @Override
    public PresenterT create() {
        HasManualSupportFragmentInjector injectorSource = (HasManualSupportFragmentInjector) getActivity();
        //noinspection unchecked
        final ComponentT component = (ComponentT) injectorSource.supportFragmentInjector().get(this);
        final PresenterT presenter = component.getPresenter();
        presenter.component = component;
        return presenter;
    }

    @Override
    public void onPresenterReady(@NonNull PresenterT presenter) {
        super.onPresenterReady(presenter);
        this.presenter = presenter;
        onComponentAvailable(presenter.component);
    }

    public void onComponentAvailable(ComponentT component) {
        //noinspection unchecked
        component.inject(this);
    }
}
