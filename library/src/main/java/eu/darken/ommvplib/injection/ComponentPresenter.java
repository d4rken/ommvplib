package eu.darken.ommvplib.injection;


import android.support.annotation.Nullable;

import eu.darken.ommvplib.BasePresenter;
import eu.darken.ommvplib.BaseView;

public abstract class ComponentPresenter<
        ViewT extends BaseView,
        ComponentT extends BaseComponent<ViewT, ? extends ComponentPresenter>>
        implements BasePresenter<ViewT> {

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
