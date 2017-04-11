package eu.darken.ommvplib.example.screens;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import eu.darken.ommvplib.example.R;
import eu.darken.ommvplib.injection.ComponentPresenterActivity;
import eu.darken.ommvplib.injection.ComponentSource;
import eu.darken.ommvplib.injection.ManualInjector;
import eu.darken.ommvplib.injection.fragment.support.HasManualSupportFragmentInjector;


public class MainActivity extends ComponentPresenterActivity<MainView, MainPresenter, MainComponent>
        implements MainView, HasManualSupportFragmentInjector {

    @MainScope
    @Inject
    ComponentSource<Fragment> componentSource;

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
    public void onComponentAvailable(MainComponent component) {
        component.inject(this);
    }

    @Override
    public void showFragments(List<MainPagerAdapter.FragmentObj> fragmentbjects) {
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), fragmentbjects));
    }

    @Override
    public Class<? extends MainPresenter> getTypeClazz() {
        return MainPresenter.class;
    }

    @Override
    public ManualInjector<Fragment> supportFragmentInjector() {
        return componentSource;
    }
}
