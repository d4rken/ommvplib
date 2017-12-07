package eu.darken.ommvplib.injection;

import eu.darken.ommvplib.base.Presenter;
import eu.darken.ommvplib.base.PresenterFactory;
import eu.darken.ommvplib.base.PresenterPreferenceFragment;
import eu.darken.ommvplib.extra.Preconditions;
import eu.darken.ommvplib.injection.fragment.support.HasManualSupportFragmentInjector;
import eu.darken.ommvplib.injection.fragment.support.SupportFragmentComponent;

public abstract class ComponentPresenterPreferenceFragment<
        ViewT extends Presenter.View,
        PresenterT extends ComponentPresenter<ViewT, ComponentT>,
        ComponentT extends SupportFragmentComponent & PresenterComponent<ViewT, PresenterT>>
        extends PresenterPreferenceFragment<ViewT, PresenterT>
        implements PresenterFactory<PresenterT> {

    @Override
    protected PresenterFactory<PresenterT> getPresenterFactory() {
        return this;
    }

    @Override
    public PresenterT create() {
        HasManualSupportFragmentInjector injectorSource = (HasManualSupportFragmentInjector) Preconditions.checkNotNull(getActivity());
        //noinspection unchecked
        final ComponentT component = (ComponentT) injectorSource.supportFragmentInjector().get(this);
        final PresenterT presenter = component.getPresenter();
        presenter.setComponent(component);
        return presenter;
    }

    @Override
    public void onPresenterReady(PresenterT presenter) {
        super.onPresenterReady(presenter);
        this.presenter = presenter;
        onComponentAvailable(presenter.getComponent());
    }

    public void onComponentAvailable(ComponentT component) {
        //noinspection unchecked
        component.inject(this);
    }
}
