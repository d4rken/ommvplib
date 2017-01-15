package eu.darken.ommvplib.injection;

import eu.darken.ommvplib.BaseView;

public interface BaseComponent<
        ViewT extends BaseView,
        PresenterT extends ComponentPresenter<ViewT, ?>> {

    void inject(ViewT view);

    PresenterT getPresenter();
}
