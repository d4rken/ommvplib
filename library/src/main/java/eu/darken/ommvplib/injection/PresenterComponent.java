package eu.darken.ommvplib.injection;

import eu.darken.ommvplib.Presenter;

public interface PresenterComponent<
        ViewT extends Presenter.View,
        PresenterT extends ComponentPresenter<ViewT, ? extends PresenterComponent>> {

    void inject(ViewT view);

    PresenterT getPresenter();
}
