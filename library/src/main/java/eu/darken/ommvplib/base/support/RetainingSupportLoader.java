package eu.darken.ommvplib.base.support;

import android.content.Context;
import android.support.v4.content.Loader;

import eu.darken.ommvplib.base.ObjectFactory;


class RetainingSupportLoader<TypeT> extends Loader<TypeT> {

    private ObjectFactory<TypeT> objectFactory;
    private TypeT objectToRetain;

    RetainingSupportLoader(Context context, ObjectFactory<TypeT> objectFactory) {
        super(context);
        this.objectFactory = objectFactory;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (objectToRetain == null) forceLoad();
        else deliverResult(objectToRetain);
    }

    @Override
    public void forceLoad() {
        createObjectToRetain();
        clearDataAfterCreation();
        deliverResult(objectToRetain);
    }

    TypeT getObject() {
        return objectToRetain;
    }

    protected void createObjectToRetain() {
        objectToRetain = objectFactory.create();
    }

    protected void clearDataAfterCreation() {
        objectFactory = null;
    }
}
