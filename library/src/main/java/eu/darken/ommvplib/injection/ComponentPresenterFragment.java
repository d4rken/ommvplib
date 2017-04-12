package eu.darken.ommvplib.injection;

import android.support.annotation.NonNull;

import eu.darken.ommvplib.base.Presenter;
import eu.darken.ommvplib.base.PresenterFactory;
import eu.darken.ommvplib.base.PresenterFragment;
import eu.darken.ommvplib.injection.fragment.FragmentComponent;
import eu.darken.ommvplib.injection.fragment.HasManualFragmentInjector;

public abstract class ComponentPresenterFragment<
        ViewT extends Presenter.View,
        PresenterT extends ComponentPresenter<ViewT, ComponentT>,
        ComponentT extends FragmentComponent & PresenterComponent<ViewT, PresenterT>>
        extends PresenterFragment<ViewT, PresenterT>
        implements PresenterFactory<PresenterT> {

    @NonNull
    @Override
    protected PresenterFactory<PresenterT> getPresenterFactory() {
        return this;
    }

    @Override
    public PresenterT create() {
        HasManualFragmentInjector injectorSource = (HasManualFragmentInjector) getActivity();
        //noinspection unchecked
        final ComponentT component = (ComponentT) injectorSource.fragmentInjector().get(this);
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
