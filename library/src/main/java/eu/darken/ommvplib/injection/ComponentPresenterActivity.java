package eu.darken.ommvplib.injection;

import android.support.annotation.NonNull;

import eu.darken.ommvplib.base.Presenter;
import eu.darken.ommvplib.base.PresenterActivity;
import eu.darken.ommvplib.base.PresenterFactory;

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
        final ComponentT component = createComponent();
        final PresenterT presenter = component.getPresenter();
        presenter.component = component;
        return presenter;
    }

    @Override
    public void onPresenterReady(@NonNull PresenterT presenter) {
        super.onPresenterReady(presenter);
        this.presenter = presenter;
        inject(presenter.component);
    }

    public void inject(@NonNull ComponentT component) {

    }

    protected abstract ComponentT createComponent();

}