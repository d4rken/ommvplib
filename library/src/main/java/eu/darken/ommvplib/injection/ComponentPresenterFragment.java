package eu.darken.ommvplib.injection;

import android.support.annotation.NonNull;

import eu.darken.ommvplib.mvp.BasePresenterFragment;
import eu.darken.ommvplib.mvp.BaseView;
import eu.darken.ommvplib.mvp.PresenterLoader;

public abstract class ComponentPresenterFragment<
        ViewT extends BaseView,
        PresenterT extends ComponentPresenter<ViewT, ComponentT>,
        ComponentT extends BaseComponent<ViewT, PresenterT>>
        extends BasePresenterFragment<ViewT, PresenterT>
        implements PresenterLoader.PresenterFactory<PresenterT> {
    @NonNull
    @Override
    protected PresenterLoader.PresenterFactory<PresenterT> getPresenterFactory() {
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

    public abstract ComponentT createComponent();
}
