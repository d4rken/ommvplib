package eu.darken.ommvplib.core;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import eu.darken.ommvplib.BasePresenter;
import eu.darken.ommvplib.BaseView;


public class PresenterLoader<ViewT extends BaseView, PresenterT extends BasePresenter<ViewT>> extends ObjectRetainingLoader<PresenterT> {

    private Bundle savedState;

    public PresenterLoader(
            @NonNull Context context,
            @NonNull PresenterFactory<PresenterT> factory,
            @Nullable Bundle savedState) {
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

    public interface PresenterFactory<PresenterT extends BasePresenter> extends TypedObjectFactory<PresenterT> {

    }
}
