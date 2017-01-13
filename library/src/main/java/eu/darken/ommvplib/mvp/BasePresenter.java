package eu.darken.ommvplib.mvp;


import android.os.Bundle;
import android.support.annotation.Nullable;

public interface BasePresenter<ViewType> {
    void onCreate(@Nullable Bundle bundle);

    void onBind(@Nullable ViewType view);

    void onSaveInstanceState(Bundle bundle);

    void onDestroy();
}
