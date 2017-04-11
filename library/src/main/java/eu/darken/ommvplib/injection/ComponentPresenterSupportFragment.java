package eu.darken.ommvplib.injection;

import android.support.annotation.NonNull;

import eu.darken.ommvplib.base.Presenter;
import eu.darken.ommvplib.base.PresenterFactory;
import eu.darken.ommvplib.base.support.PresenterSupportFragment;
import eu.darken.ommvplib.injection.fragment.support.HasManualSupportFragmentInjector;

public abstract class ComponentPresenterSupportFragment<
        ViewT extends Presenter.View,
        PresenterT extends ComponentPresenter<ViewT, ComponentT>,
        ComponentT extends PresenterComponent<ViewT, PresenterT>>
        extends PresenterSupportFragment<ViewT, PresenterT>
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

    }
}
