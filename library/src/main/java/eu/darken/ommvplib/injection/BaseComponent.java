package eu.darken.ommvplib.injection;

import eu.darken.ommvplib.mvp.BaseView;

public interface BaseComponent<
        ViewT extends BaseView,
        PresenterT extends ComponentPresenter<ViewT, ?>> {

    void inject(ViewT view);

    PresenterT getPresenter();
}
