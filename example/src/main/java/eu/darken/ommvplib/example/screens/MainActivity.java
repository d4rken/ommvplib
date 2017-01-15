package eu.darken.ommvplib.example.screens;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.darken.ommvplib.example.R;
import eu.darken.ommvplib.injection.ComponentPresenterActivity;

import static eu.darken.ommvplib.example.InjectionHelper.getAppComponent;


public class MainActivity extends ComponentPresenterActivity<MainView, MainPresenter, MainComponent> implements MainView {

    @BindView(R.id.tabs) TabLayout tabLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.viewpager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        final MainPagerAdapter sectionsPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(sectionsPagerAdapter);
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
    public void inject(MainComponent component) {
        component.inject(this);
    }

    @Override
    protected MainComponent createComponent() {
        return getAppComponent(this).mainComponent(new MainModule());
    }

    @Override
    public Class<? extends MainPresenter> getTypeClazz() {
        return MainPresenter.class;
    }

}
