package eu.darken.ommvplib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

interface InstanceStateCallback {
    /**
     * Call directly after {@link android.app.Activity#onCreate(Bundle)} or {@link android.app.Fragment#onCreate(Bundle)}
     */
    void onCreate(@Nullable Bundle savedInstanceState);

    /**
     * Call before {@link android.app.Activity#onSaveInstanceState(Bundle)} or {@link android.app.Fragment#onSaveInstanceState(Bundle)}
     */
    void onSaveInstanceState(Bundle outState);
}
