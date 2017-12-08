package eu.darken.ommvplib.extra;

import android.content.Context;
import android.content.Loader;


public class RetainingLoader<TypeT> extends Loader<TypeT> {

    private ObjectFactory<TypeT> objectFactory;
    private TypeT objectToRetain;

    public RetainingLoader(Context context, ObjectFactory<TypeT> objectFactory) {
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

    public TypeT getObject() {
        return objectToRetain;
    }

    protected void createObjectToRetain() {
        objectToRetain = objectFactory.create();
    }

    protected void clearDataAfterCreation() {
        objectFactory = null;
    }
}
