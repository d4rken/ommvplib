package eu.darken.ommvplib.injection;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import eu.darken.ommvplib.base.Presenter;

public abstract class ComponentPresenter<
        ViewT extends Presenter.View,
        ComponentT extends PresenterComponent<ViewT, ? extends ComponentPresenter>>
        implements Presenter<ViewT> {

    protected ComponentT component;
    private ViewT view;

    @Nullable
    public ViewT getView() {
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onBindChange(@Nullable ViewT view) {
        this.view = view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

    }

    @Override
    public void onDestroy() {

    }
}
