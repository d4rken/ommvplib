package eu.darken.ommvplib;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface BasePresenter<ViewType extends BaseView> {
    void onCreate(@Nullable Bundle bundle);

    void onBindChange(@Nullable ViewType view);

    void onSaveInstanceState(@NonNull Bundle bundle);

    void onDestroy();
}
