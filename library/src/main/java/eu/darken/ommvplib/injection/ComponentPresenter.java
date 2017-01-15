package eu.darken.ommvplib.injection;


import android.support.annotation.Nullable;

import eu.darken.ommvplib.Presenter;

public abstract class ComponentPresenter<
        ViewT extends Presenter.View,
        ComponentT extends PresenterComponent<ViewT, ? extends ComponentPresenter>>
        implements Presenter<ViewT> {

    ComponentT component;
    private ViewT view;

    @Override
    public void onBindChange(@Nullable ViewT view) {
        this.view = view;
    }

    @Nullable
    public ViewT getView() {
        return view;
    }
}
