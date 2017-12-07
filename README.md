# One More MVP Lib
[ ![Download](https://api.bintray.com/packages/darken/maven/ommvplib/images/download.svg) ](https://bintray.com/darken/maven/ommvplib/_latestVersion)
[![Coverage Status](https://coveralls.io/repos/github/d4rken/ommvplib/badge.svg)](https://coveralls.io/github/d4rken/ommvplib)
[![Build Status](https://travis-ci.org/d4rken/ommvplib.svg?branch=master)](https://travis-ci.org/d4rken/ommvplib)

This library offers a structure for building Android apps follow MVP principles. Adhering to this structure yields a modular and well testable app.

The core concept consists of Dagger2's `AndroidInjector` while using Android's `Loader` to retain presenters and components across lifecycle events

## Quickstart
1. Add the library
```
implementation 'eu.darken.ommvplib:library:0.1.0'
```
2. There is no Quickstart. Look at the example app. It's not as complicated as it looks. 

## Acknowledgements
This library combines and refactored several other concepts. It was based on:

* [tomorrow-mvp](https://github.com/michal-luszczuk/tomorrow-mvp) by Michał Łuszczuk
* [toegether-mvp](https://github.com/laenger/together-mvp) by Christian Langer
* [activities-multibinding-in-dagger-2](http://frogermcs.github.io/activities-multibinding-in-dagger-2/) by Miroslaw Stanek
* [Dagger2-MultiBinding-Android-Example](https://github.com/trevjonez/Dagger2-MultiBinding-Android-Example) by Trevor Jones
* [MCE3 Dagger 2 Talk](https://www.youtube.com/watch?v=iwjXqRlEevg) by Gregory Kick

Check out [@luszczuk's](https://twitter.com/luszczuk) (author of tomorrow-mvp) [comparison of Android MVP approaches](http://blog.propaneapps.com/android/mvp-for-android/).
