package eu.darken.ommvplib.base.support;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import eu.darken.ommvplib.base.Presenter;
import eu.darken.ommvplib.base.PresenterFactory;


public class PresenterSupportLoader<ViewT extends Presenter.View, PresenterT extends Presenter<ViewT>> extends RetainingSupportLoader<PresenterT> {

    private Bundle savedState;

    public PresenterSupportLoader(@NonNull Context context, @NonNull PresenterFactory<PresenterT> factory, @Nullable Bundle savedState) {
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
