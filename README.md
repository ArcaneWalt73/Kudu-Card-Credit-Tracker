<a href="https://circleci.com/github/The-Combrades/Kudu-Card-Credit-Tracker"><img src="https://circleci.com/github/The-Combrades/Kudu-Card-Credit-Tracker.svg?style=svg" alt='CircleCi status'></a>
<a href='https://coveralls.io/github/The-Combrades/Kudu-Card-Credit-Tracker?branch=master'><img src='https://coveralls.io/repos/github/The-Combrades/Kudu-Card-Credit-Tracker/badge.svg?branch=master' alt='Coverage Status' /></a>
Kudu Card Tracker
=====================

An Android App to manage and check the amount of kudu bucks in your student card, the app will also have a functionality to buy things like food and stationary from the shops at the matrix or anywhere on campus by allowing the user to swipe their card in the same way they do when printing.

Kudu Card Tracker has the following features:
* Register and Login
* Profile viewing and Balance checks
* Markplace browsing
* Buying items sold by stores in wits
* item Popularity Votes
* Buying and Recharging History
* Item Reviews

Building
========
To build you will need:

 * A Java compiler compatible with Java 1.8
 * The Android SDK with platform 26 installed

Building from command-line
--------------------------
> Note: at the time of this writing, the current version of Gradle ([4.5.1](https://gradle.org/releases/)) is not compatible with the current version of JDK ([9.0.4](http://www.oracle.com/technetwork/java/javase/downloads/jdk9-downloads-3848520.html)). To have the build succeed, use JDK version [1.8.0_162](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
 * `gradle build` to build the APK
 * Optional: `gradle installDebug` to install the APK to a connected device

Building with Android Studio
---------------------
You can also build with Android Studio by importing this project into it.

Building from Eclipse
---------------------
You can also build from Eclipse. Create a new Android Project, choosing "Create
project from exisiting source", then set the compiler compliance level to 1.6
in project settings.

Nightly Builds
---------------------
Automatically created builds are available from [To be inserted]

Documentation
=============
Javadocs can be generated using `gradle javadoc` or `ant doc`

  [1]: https://github.com/The-Combrades/Kudu-Card-Credit-Tracker/issues
  [2]: https://github.com/The-Combrades/Kudu-Card-Credit-Tracker/wiki
