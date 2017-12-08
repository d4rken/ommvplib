package eu.darken.ommvplib.injection;

import dagger.android.AndroidInjector;
import eu.darken.ommvplib.base.LoaderFactory;
import eu.darken.ommvplib.base.Presenter;

public class PresenterInjectionCallback<
        TargetT extends ViewT,
        ViewT extends Presenter.View,
        PresenterT extends ComponentPresenter<ViewT, ComponentT>,
        ComponentT extends PresenterComponent<ViewT, PresenterT> & AndroidInjector<TargetT>>
        implements LoaderFactory.Callback<ViewT, PresenterT> {

    private final TargetT toInjectInto;

    public PresenterInjectionCallback(TargetT toInjectInto) {this.toInjectInto = toInjectInto;}

    @Override
    public void onPresenterReady(PresenterT presenter) {
        final ComponentT component = presenter.getComponent();
        component.inject(toInjectInto);
    }

    @Override
    public void onPresenterDestroyed() {

    }
}
