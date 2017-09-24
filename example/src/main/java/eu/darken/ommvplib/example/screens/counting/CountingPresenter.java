package eu.darken.ommvplib.example.screens.counting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import eu.darken.ommvplib.injection.ComponentPresenter;
import timber.log.Timber;


@CountingScope
public class CountingPresenter extends ComponentPresenter<CountingView, CountingComponent> {

    private final Counter counter;

    @Inject
    CountingPresenter(Counter counter) {
        this.counter = counter;
    }

    void onCountClick() {
        if (getView() != null) getView().showText(String.valueOf(counter.countUp()));
    }

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) counter.setCounter(bundle.getInt("counter"));
    }

    @Override
    public void onBindChange(@Nullable CountingView view) {
        super.onBindChange(view);
        if (view != null) view.showText(String.valueOf(counter.getCurrent()));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("counter", counter.getCurrent());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy()");
    }
}
