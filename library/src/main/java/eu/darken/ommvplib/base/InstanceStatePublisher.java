package eu.darken.ommvplib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class InstanceStatePublisher implements InstanceStateCallback {
    private InstanceStateCallback internalCallback;
    private Bundle savedInstanceState;
    private Bundle outState;

    void setInternalCallback(InstanceStateCallback internalCallback) {
        this.internalCallback = internalCallback;
        if (savedInstanceState != null) {
            internalCallback.onCreate(savedInstanceState);
            savedInstanceState = null;
        }
        if (outState != null) {
            internalCallback.onSaveInstanceState(outState);
            outState = null;
        }
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (internalCallback != null) internalCallback.onCreate(savedInstanceState);
        else this.savedInstanceState = savedInstanceState;
    }

    public void onSaveInstanceState(Bundle outState) {
        if (internalCallback != null) internalCallback.onSaveInstanceState(outState);
        else this.outState = outState;
    }
}
