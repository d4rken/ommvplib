package eu.darken.ommvplib.injection;


import android.support.annotation.Nullable;

import eu.darken.ommvplib.mvp.BasePresenter;
import eu.darken.ommvplib.mvp.BaseView;

public abstract class ComponentPresenter<
        ViewT extends BaseView,
        ComponentT extends BaseComponent<ViewT, ?>>
        implements BasePresenter<ViewT> {

    protected ComponentT component;
    @Nullable private ViewT view;

    @Override
    public void onBind(@Nullable ViewT view) {
        this.view = view;
    }

    @Nullable
    public ViewT getView() {
        return view;
    }
}
