package eu.darken.ommvplib.example.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.darken.ommvplib.example.R;
import eu.darken.ommvplib.injection.ComponentPresenterActivity;

import static eu.darken.ommvplib.example.di.InjectionHelper.getAppComponent;

public class MainActivity extends ComponentPresenterActivity<eu.darken.ommvplib.example.main.MainView, eu.darken.ommvplib.example.main.MainPresenter, MainComponent> implements eu.darken.ommvplib.example.main.MainView {

    @BindView(R.id.tabs) TabLayout tabLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.viewpager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initViewPager();
    }

    private void initViewPager() {
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
    public void inject(eu.darken.ommvplib.example.main.MainComponent component) {
        component.inject(this); // no-op for this activity
    }

    @Override
    protected eu.darken.ommvplib.example.main.MainComponent createComponent() {
        return getAppComponent(this).mainComponent(new eu.darken.ommvplib.example.main.MainModule());
    }

    @Override
    public Class<? extends eu.darken.ommvplib.example.main.MainPresenter> getTypeClazz() {
        return eu.darken.ommvplib.example.main.MainPresenter.class;
    }

}
