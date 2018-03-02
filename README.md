# One More MVP Lib
[ ![Download](https://api.bintray.com/packages/darken/maven/ommvplib/images/download.svg) ](https://bintray.com/darken/maven/ommvplib/_latestVersion)
[![Coverage Status](https://coveralls.io/repos/github/d4rken/ommvplib/badge.svg)](https://coveralls.io/github/d4rken/ommvplib)
[![Build Status](https://travis-ci.org/d4rken/ommvplib.svg?branch=master)](https://travis-ci.org/d4rken/ommvplib)

This library offers a structure that helps build Android apps that follow MVP principles.

The core concept utilizes a `Loader` to store the `Presenter` during configuration (e.g. rotation) changes. This can be seamlessly combined with Dagger2's `AndroidInjector`.

The demo application shows how to use the library and includes examples on how to use unit and instrumentation tests with it.

## Quickstart
Add the library:
```groovy
implementation 'eu.darken.ommvplib:library:0.2.1'
```
### Without Dagger
The `Presenter` and the `View` that our `Activity` will implement.
```java
@MainComponent.Scope
public class MainPresenter extends Presenter<MainPresenter.View, MainComponent> {

    @Inject
    MainPresenter() {
    }

    @Override
    public void onBindChange(@Nullable View view) {
        super.onBindChange(view);
        onView(v -> doSomething());
    }

    public interface View extends Presenter.View {
        void doSomething();
    }
}

```

The process of `attach`ing initialises the `Loader`, checks for any existing `Presenter` and creates one if necessary.
```java
public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OMMVPLib.<MainPresenter.View, MainPresenter>builder()
                .statePublisher(statePublisher)
                .presenterCallback(new LoaderFactory.Callback<MainPresenter.View, MainPresenter>() {
                    @Override
                    public void onPresenterReady(MainPresenter presenter) {
                        MainActivity.this.presenter = presenter;
                    }

                    @Override
                    public void onPresenterDestroyed() {

                    }
                })
                .presenterSource(new PresenterSource<MainPresenter>() {
                    @Override
                    public MainPresenter create() {
                        return new MainPresenter();
                    }
                })
                .attach(this);
        setContentView(R.layout.activity_main);
    }
}
```

### With Dagger
The component for our injection.
```java
@MainComponent.Scope
@Subcomponent(modules = {AndroidSupportInjectionModule.class})
public interface MainComponent extends ActivityComponent<MainActivity>, PresenterComponent<MainPresenter.View, MainPresenter> {
    
    @Subcomponent.Builder
    abstract class Builder extends ActivityComponent.Builder<MainActivity, MainComponent> {}
    
    @javax.inject.Scope
    @Retention(RetentionPolicy.RUNTIME)
    @interface Scope {}
}
```

The `Presenter` that will be injected, including the `View` that our `Activity` will implement.

```java
@MainComponent.Scope
public class MainPresenter extends ComponentPresenter<MainPresenter.View, MainComponent> {

    @Inject
    MainPresenter() {
    }

    @Override
    public void onBindChange(@Nullable View view) {
        super.onBindChange(view);
        onView(v -> doSomething());
    }

    public interface View extends Presenter.View {
        void doSomething();
    }
}

```

Now we just need to attach the presenter.

```java
public class MainActivity extends AppCompatActivity implements MainPresenter.View {
    
    @Inject MainPresenter presenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OMMVPLib.<MainPresenter.View, MainPresenter>builder()
                .statePublisher(statePublisher)
                .presenterCallback(new PresenterInjectionCallback<>(this))
                .presenterSource(new InjectedPresenter<>(this))
                .attach(this);
        setContentView(R.layout.activity_main);
    }
}
```


## Acknowledgements
This library combines multiple concepts: 

* [tomorrow-mvp](https://github.com/michal-luszczuk/tomorrow-mvp) by Michał Łuszczuk
* [toegether-mvp](https://github.com/laenger/together-mvp) by Christian Langer
* [activities-multibinding-in-dagger-2](http://frogermcs.github.io/activities-multibinding-in-dagger-2/) by Miroslaw Stanek
* [Dagger2-MultiBinding-Android-Example](https://github.com/trevjonez/Dagger2-MultiBinding-Android-Example) by Trevor Jones
* [MCE3 Dagger 2 Talk](https://www.youtube.com/watch?v=iwjXqRlEevg) by Gregory Kick

Check out [@luszczuk's](https://twitter.com/luszczuk) (author of tomorrow-mvp) [comparison of Android MVP approaches](http://blog.propaneapps.com/android/mvp-for-android/).
