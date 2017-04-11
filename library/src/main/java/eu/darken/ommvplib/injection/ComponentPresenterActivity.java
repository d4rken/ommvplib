package eu.darken.ommvplib.injection;

import android.support.annotation.NonNull;

import eu.darken.ommvplib.base.Presenter;
import eu.darken.ommvplib.base.PresenterActivity;
import eu.darken.ommvplib.base.PresenterFactory;
import eu.darken.ommvplib.injection.activity.HasManualActivityInjector;

public abstract class ComponentPresenterActivity<
        ViewT extends Presenter.View,
        PresenterT extends ComponentPresenter<ViewT, ComponentT>,
        ComponentT extends PresenterComponent<ViewT, PresenterT>>
        extends PresenterActivity<ViewT, PresenterT>
        implements PresenterFactory<PresenterT> {

    @NonNull
    @Override
    public PresenterFactory<PresenterT> getPresenterFactory() {
        return this;
    }

    @Override
    public PresenterT create() {
        HasManualActivityInjector injectorSource = (HasManualActivityInjector) getApplication();
        //noinspection unchecked
        final ComponentT component = (ComponentT) injectorSource.activityInjector().get(this);
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