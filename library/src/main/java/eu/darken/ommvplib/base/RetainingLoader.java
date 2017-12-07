package eu.darken.ommvplib.base;

import android.content.Context;
import android.content.Loader;


class RetainingLoader<TypeT> extends Loader<TypeT> {

    private ObjectFactory<TypeT> objectFactory;
    private TypeT objectToRetain;

    RetainingLoader(Context context, ObjectFactory<TypeT> objectFactory) {
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
