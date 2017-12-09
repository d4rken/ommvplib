package eu.darken.ommvplib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;


public class PresenterLoader<ViewT extends Presenter.View, PresenterT extends Presenter<ViewT>> extends RetainingLoader<PresenterT> {

    private Bundle savedState;

    public PresenterLoader(Context context, PresenterSource<PresenterT> factory, @Nullable Bundle savedState) {
        super(context, factory);
        this.savedState = savedState;
    }

    public PresenterT getPresenter() {
        return getObject();
    }

    @Override
    protected void createObjectToRetain() {
        super.createObjectToRetain();
        getPresenter().onCreate(savedState);
    }

    @Override
    protected void clearDataAfterCreation() {
        super.clearDataAfterCreation();
        savedState = null;
    }

    @Override
    protected void onReset() {
        getPresenter().onDestroy();
        super.onReset();
    }

}
