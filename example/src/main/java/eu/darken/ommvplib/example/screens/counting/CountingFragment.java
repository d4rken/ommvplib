package eu.darken.ommvplib.example.screens.counting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.darken.ommvplib.base.InstanceStatePublisher;
import eu.darken.ommvplib.base.OMMVPLib;
import eu.darken.ommvplib.example.R;
import eu.darken.ommvplib.injection.InjectedPresenter;
import eu.darken.ommvplib.injection.PresenterInjectionCallback;

public class CountingFragment extends Fragment implements CountingPresenter.View {

    @BindView(R.id.fragment_text) TextView textView;

    private InstanceStatePublisher statePublisher;
    private OMMVPLib<CountingPresenter.View, CountingPresenter> ommvpLib;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statePublisher = new InstanceStatePublisher();
        statePublisher.onCreate(savedInstanceState);
        ommvpLib = OMMVPLib.<CountingPresenter.View, CountingPresenter>builder()
                .statePublisher(statePublisher)
                .presenterCallback(new PresenterInjectionCallback<>(this))
                .presenterSource(new InjectedPresenter<>(this))
                .attach(this);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        statePublisher.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_counting, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void showText(String text) {
        textView.setText(text);
    }

    @OnClick(R.id.fragment_button)
    void onCountClick() {
        ommvpLib.getPresenter().onCountClick();
    }
}
