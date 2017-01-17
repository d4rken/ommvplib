package eu.darken.ommvplib.injection;

import eu.darken.ommvplib.base.Presenter;

public interface PresenterComponent<
        ViewT extends Presenter.View,
        PresenterT extends ComponentPresenter<ViewT, ? extends PresenterComponent>> {

    PresenterT getPresenter();
}
