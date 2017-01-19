package eu.darken.ommvplib.example.screens;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.darken.ommvplib.example.ExampleApplication;
import eu.darken.ommvplib.example.R;
import eu.darken.ommvplib.injection.ComponentPresenterActivity;
import eu.darken.ommvplib.injection.fragment.FragmentComponent;
import eu.darken.ommvplib.injection.fragment.FragmentComponentBuilder;
import eu.darken.ommvplib.injection.fragment.FragmentComponentBuilderSource;


public class MainActivity extends ComponentPresenterActivity<MainView, MainPresenter, MainComponent>
        implements MainView, FragmentComponentBuilderSource {

    @Inject Map<Class<? extends Fragment>, Provider<FragmentComponentBuilder>> componentBuilders;
    @Inject List<MainPagerAdapter.FragmentObj> fragments;

    @BindView(R.id.tabs) TabLayout tabLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.viewpager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                getPresenter().onPagerItemSelected(position);
            }
        });
    }

    @Override
    public void showPagerItem(int position) {
        viewPager.setCurrentItem(position, false);
    }

    @Override
    protected MainComponent createComponent() {
        MainComponent.Builder builder = ExampleApplication.get(this).getComponentBuilder(MainActivity.class);
        builder.activityModule(new MainModule(this));
        return builder.build();
    }

    @Override
    public void inject(@NonNull MainComponent component) {
        super.inject(component);
        component.injectMembers(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), fragments));
    }

    @Override
    public Class<? extends MainPresenter> getTypeClazz() {
        return MainPresenter.class;
    }

    @Override
    public <A extends Fragment, B extends FragmentComponentBuilder<A, ? extends FragmentComponent<A>>> B getComponentBuilder(Class<A> fragmentClass) {
        //noinspection unchecked
        return (B) componentBuilders.get(fragmentClass).get();
    }
}
