package eu.darken.ommvplib.injection;

import android.support.annotation.NonNull;

import eu.darken.ommvplib.BaseView;
import eu.darken.ommvplib.core.BasePresenterActivity;
import eu.darken.ommvplib.core.PresenterLoader;

public abstract class ComponentPresenterActivity<
        ViewT extends BaseView,
        PresenterT extends ComponentPresenter<ViewT, ComponentT>,
        ComponentT extends BaseComponent<ViewT, PresenterT>>
        extends BasePresenterActivity<ViewT, PresenterT>
        implements PresenterLoader.PresenterFactory<PresenterT> {

    @NonNull
    @Override
    public PresenterLoader.PresenterFactory<PresenterT> getPresenterFactory() {
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

    public abstract void inject(ComponentT component);

    protected abstract ComponentT createComponent();

}