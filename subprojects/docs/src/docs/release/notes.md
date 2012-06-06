Gradle 1.0 is a major step forward in the evolution of Gradle, and build tools in general.

## New and noteworthy

Here are the new features introduced in Gradle 1.0, including the changes in all of the milestones.

### Fast, accurate and powerful dependency management

Dependency management is at the heart of every build. Gradle 1.0 we've rebuilt the artifact cache and dependency resolution engine so that dependency resolution is much faster, while giving more accurate results to avoid subtle (and not so subtle) problems, such as non-repeatable builds that work on one developer's machine, but not some other developer's machine.

#### Cache meta-data on a per repository basis

All meta-data and artefacts are cached per-repository, meaning that your build only has access to dependencies that are available in declared repositories.

This avoids issues that can arise where a build succeeds due to a dependency being available in the cache from a different repository.

#### Concurrency-safe cache

The Gradle 1.0 dependency cache is fully thread-safe and mulitprocess-safe.

#### Faster dependency resolution

We've done a lot of work to make dependency resolution as fast as possible, while staying reproducible and accurate. We did this by:

* Greatly reducing the number of HTTP requests required to download a dependency</li>
* Caching the absence of artefacts per repository, so we don't need to check on every resolve.</li>
* Implementing a new resolve engine which is much faster and more maintainable than the previous ivy-based one.</li>
* Improving the cache locking and lock escalation, to be as efficient as possible.</li>

#### Reuse previously downloaded artefacts from m2 and older Gradle caches

If you're a maven user, in many cases you a dependency you require is already available in your local m2 repository. To save downloading the dependency again, Gradle will reuse any locally available artefacts if they match the checksums published by the remote repository.

#### Improved change detection and reduced artifact downloads

Gradle 1.0 has an improved algorithm for detecting if a cached artifact is up-to-date. By using published .sha1 files, ETags, and last-modified-date + content-length, Gradle will do everything it can to check if the version on the server is the same as the version you have already downloaded.

#### Consistent caching for dynamic dependencies and changing modules.

To improve performance, Gradle caches the resolution of dynamic versions and changing modules for 24 hours. You can easily manage this timeout using the <a href="http://gradle.org/docs/1.0/userguide/dependency_management.html#sec:controlling_caching">cache-control DSL</a>.

#### Offline support

When run with the `--offline` command-line option, Gradle will use whatever cached dependencies are available, failing early if a required dependency is not available in the cache.

#### Refresh dependencies support

When run with the `--refresh-dependencies` command-line option, Gradle will validate all dependencies against the repository, while still using it's change detection algorithm to avoid the need to re-download artefacts that have not changed.

#### Stricter artifact resolution

Gradle 1.0 will now fail if a required dependency is not available, rather than silently ignoring the fact.</p>

#### Option to fail dependency resolution on conflict

More control over the dependency resolution. By default Gradle uses newer version in case of the version conflict. Now it is possible to fail fast when a conflict occurs during dependency resolution.

#### More accurate exclusions
            
#### Dependency resolution explained

Sometimes dependency resolution is a black box. We've attempted to make it more understandable by improving the <a href="http://gradle.org/docs/1.0/userguide/dependency_management.html#sec:dependency_resolution">documentation</a>, log messages and error reporting.

#### Can force a particular version of dependency

Useful for resolving conflicts explicitly. Another use case is forcing a certain transitive dependency version. It is particularly useful if the transitive dependency is incorrectly published, i.e. wrong metadata, invalid binary, etc.

#### Improved repository definition DSL

In Gradle 1.0, we've made it easier to declare basic Ivy and Maven repositories, and using the DSL for specifying your repository credentials is now simple and consistent.

#### Publish artefacts other than archives

#### More Information

You can find out more in the <a href="http://gradle.org/docs/1.0/userguide/dependency_management.html#sec:dependency_resolution">User Guide chapter</a> and this series of forum posts: <a href="http://forums.gradle.org/gradle/topics/welcome_to_our_new_dependency_cache">part 1</a>, <a href="http://forums.gradle.org/gradle/topics/dependency_resolution_improvements">part 2</a>, and <a href="http://forums.gradle.org/gradle/topics/dependency_resolution_in_gradle_1_0_milestone_8">part 3</a>.

### Faster builds

A fast build means faster feedback. Gradle 1.0 includes a host of performance improvements. We've made dependency resolution faster, in some cases many times faster. Faster dependency resolution also means faster up-to-date checks for speedy incremental builds.

The Gradle daemon is no longer experimental, and we recommend you use it for developer builds. The daemon is used automatically by the native IDE integrations to speed up the builds you run from the IDE.

#### Dependency resolution is faster
    
Reduced number of HTTP requests, started caching absence of artefacts, improved the defaults for caching changing modules (snapshots) or dynamic versions, started reusing artefacts that were previously downloaded to maven local or other Gradle version's caches.

#### Java and Groovy compilation is faster.
#### Up-to-date checks are faster for large source sets.
#### Profile report includes dependency resolution times.
#### Daemon: what is it? Read on the release notes or jump to the <a href="http://gradle.org/docs/current/userguide/gradle_daemon.html">user guide</a> for details.

### Monitoring code quality

Gradle 1.0 includes a number of new code quality integrations.

* Sonar support <p>Using the sonar plugin, Gradle user can now integrate there build results easily with Sonar, the web based platform for monitoring code quality. The <a href="http://gradle.org/docs/current/userguide/sonar_plugin.html">userguide</a> contains a detailed description how to setup and configure the Sonar support.</p>
* FindBugs plugin <p><a href="http://findbugs.sourceforge.net/">FindBugs</a> is a program which uses static analysis to look for bugs in Java code. The FindBugs plugin adds a task for every source set to scan the Java bytecode for a list of bug patterns. You can find more details about the FindBugs plugin in the <a href="http://gradle.org/docs/current/userguide/findbugs_plugin.html">userguide</a>. 
* JDepend plugin <p>The JDepend plugin enables the user to generate design quality metrics. Therefore it adds Gradle tasks that analyze all inter-package dependencies of a source set and create an according report. The JDepend plugin is described in detail in the Gradle <a href="http://gradle.org/docs/current/dsl/org.gradle.api.plugins.quality.JDepend.html">userguide</a>.</p>
* PMD plugin <p>The popular PMD project is designed to inspect Java source code and point out inefficient structures. The plugin introduces PMD Gradle tasks that can be configured in different ways. As a default, the plugin adds one pmd task for each defined source set. Details about the configuration of the PMD plugin can be found in the <a href="http://gradle.org/docs/current/userguide/pmd_plugin.html">userguide.</a></p>
* Separate Checkstyle and CodeNarc plugins. <p>As a gradle user you can now apply the Checkstyle and the CodeNarc plugins seperately. Furthermore you can now specify which version of the tool to use. You need more details? Have a look at the <a href="http://gradle.org/docs/current/userguide/checkstyle_plugin.html">Checkstyle</a> and <a href="http://gradle.org/docs/current/userguide/codenarc_plugin.html">CodeNarc</a> chapter of the Gradle userguide.</p>

### IDE integration

There are now native integrations for Eclipse STS, Intellij IDEA and NetBeans. In addition, we've improved our IDE plugins to provide more configuration options, to have better defaults, to run faster and to smoothly lets you import the project into the IDE.

The native integrations continuously improve as Gradle provides more features via the Tooling API - the new embeddable API which all the integrations use. Using native Eclipse STS, Intellij IDEA and NetBeans integrations you can import and run Gradle builds directly into your IDE, and keep your IDE settings in sync with your Gradle build definition.

The IDE plugins have more configuration options and better defaults. The DSL reference for the idea and eclipse plugins contains lots of code samples. They demonstrate how to fine-tune the configuration and make sure the project imports easily into the IDE. Generating IDE metadata using Gradle's IDE plugins is also much faster now. 

#### Gradle IDE plugins - generation of the importable IDE metadata.

DSL guide reference for <a href="http://gradle.org/docs/current/dsl/org.gradle.plugins.ide.idea.model.IdeaProject.html">idea</a> and <a href="http://gradle.org/docs/current/dsl/org.gradle.plugins.ide.eclipse.model.EclipseProject.html">eclipse</a> types contain lots of useful samples to fine-tune your IDE setup. The plugins are also described in the user guide: <a href="http://gradle.org/docs/current/userguide/userguide_single.html#idea_plugin">idea</a> and <a href="http://gradle.org/docs/current/userguide/userguide_single.html#eclipse_plugin">eclipse</a>.

#### Eclipse STS plugin includes <a href="http://static.springsource.org/sts/docs/latest/reference/html/gradle">Gradle support</a>

#### <a href="http://www.jetbrains.com/idea/webhelp/gradle-2.html">IntelliJ IDEA plugin</a>.
#### <a href="http://plugins.netbeans.org/plugin/41776/gradle">NetBeans plugin</a>.

### Embedding Gradle via the Tooling API

Gradle 1.0 saw the introduction of the Gradle tooling API, a new way to embed Gradle. This API allows you to execute and monitor builds, and to query Gradle about the details of the build. The API operates in a version independent way, meaning that with a given version of the Tooling API you will always be able to handle earlier or newer Gradle versions.

The tooling API implementation is lightweight, with only a small number of dependencies. You don't need the Gradle distribution to use the API. It is a well-behaved library, and makes no assumptions about your class loader structure or logging configuration. All above reasons make the API easy to bundle in your application.

The tooling API design aims to improve the quality of applications that embed it. The cross-version compatibility is constantly verified by our extensive test suite which validates the correctness of all Tooling API features across all Gradle versions. What's important, not only backwards compatibility is constantly verified but also forward compatibility.

For more information about the Tooling API please refer to <a href="http://gradle.org/docs/current/userguide/userguide_single.html#embedding">this section in the user guide</a>.

### Gradle Build Daemon

The Gradle build daemon aims to reduce the startup and execution time of builds, by running them in a long-running process. The <code>gradle</code> command becomes a client process that communicates with the daemon process. This way, you avoid the various startup costs and give the JVM an opportunity to optimize hotspots.

In longer term, the daemon will offer even more features that improve the performance of the build. Preemptive up-to-date checks, dependency resolution or projects evaluation are just some of many planned features. The Tooling API - an official way to embed Gradle - fully takes advantage of the build daemon. For more information please refer to <a href="http://gradle.org/docs/current/userguide/userguide_single.html#gradle_daemon">this user guide chapter</a>.

In Gradle 1.0 the daemon is:

#### No longer experimental.

The daemon was hardened since Gradle milestone-3 and finally it is recommended for local builds. There are still many improvements planned in the upcoming Gradle releases. The best way to start using the daemon is configuring the <a href="http://gradle.org/docs/current/userguide/userguide_single.html#sec:gradle_configuration_properties">org.gradle.daemon</a> property.
            
#### Forwards client input.

Some builds consume the standard input, for example, to perform the interaction with the user. The daemon fully supports those kinds of builds.

#### Respects client java version.

The java version the daemon uses is configurable. In case you run different builds with different java versions multiple daemons will get spawned to avoid compatibility issues.

#### <a href="http://gradle.org/docs/current/userguide/userguide_single.html#sec:gradle_configuration_properties">Configurable</a> via gradle.properties and system properties.
            
#### Can specify JVM args and java version to use.

#### Non-daemon build honours settings too.

### Enterprise scale

Working at the enterprise scale means more than just a fast build. Gradle 1.0 adds some capabilities to help you push build logic and configuration out to developers, and across teams, in a controlled way. You can do this without having to visit every machine to make tweaks or install specific plugins.

Gradle 1.0 allows you to assemble a custom distribution. This is a perfect way to bundle corporate plugins and reuse solutions to the common use cases found across teams in the organization. Below Gradle features are intended to improve the enterprise build environment:

#### The Gradle <a href="http://gradle.org/docs/current/userguide/userguide_single.html#gradle_wrapper">wrapper</a>.

The wrapper is invaluable in a large team because it guarantees that particular Gradle version is used to execute builds. Consistent build environment means reproducible and maintainable automation. Wrapper automatically provides a required gradle distribution which makes it easy to execute builds on new environments, set up CI servers, etc.

#### Can specify JVM args and java version to use.

It is possible to explicitly configure the JVM args and the java location. This setting can be checked-in to the VCS and becomes a part of versioned build environment which increases to the maintainability and consistency of the development environment.

#### Bundling init scripts in the distribution.

The init script can be used to configure the standard corporate plugins or apply certain common conventions. This way it is very easy to use a particular standard plugin in a project that needs it without the hassle of configuring the build script classpath.

#### Init scripts DSL: rootProject { }, allprojects { }.

Init script enables to apply specific configurations to the root project, or even all projects. This way it is possible avoid duplication that comes with standardizing certain aspects of Gradle builds across teams.

#### Automatically using init scripts from ~/.gradle/init.d directory.

### C++ support

Gradle 1.0 includes preliminary [support for building C++ based projects](http://gradle.org/docs/current/userguide/cpp.html) on both Windows and UNIX like platforms. The `cpp-exe` and `cpp-lib` plugins can be used for building native executables and libraries respectively from C++ source code. These plugins are in the early stages of development, but can already be used to generate native binaries.

Currently, there is no direct support for creating multiple variants of the same binary (e.g. 32 bit vs. 64 bit) and there is no direct support for cross platform source configuration (à la autoconf) at this time. Support for different compiler chains, managing multiple variants and cross platform source configuration will be added over time, making Gradle a fully capable build tool for C++ (and other “native” language) projects.

The Gradle development team would like to encourage all users interested in C++ support to experiment with the existing functionality and provide feedback via the [Gradle Forums](http://forums.gradle.org/).

### Even more plugins and simpler build scripts

Gradle is always committed to improve the development cycles. Hence new plugins, conventions and features that make the everyday use of Gradle more convenient. More plugins means more build-by-convention support, and less configuration and custom build logic.

Gradle knows new types of projects: new plugins include support for building c++ libraries and executables, applications that run on the JVM, and EARs. Gradle 1.0 also includes numerous improvements to the build DSL and lots of new features that make the builds simpler and easier to maintain. Better error messages, more documentation and samples streamlines any troubleshooting.

* C++ plugin: UNIX, Mac, Windows, gcc/mingw and visual studio.
* Application plugin.
* Signing plugin.
* Ear plugin.
* Build announcements plugin.
* Test output improvements: new JUnit report and an option to log output to the console.
* --continue command line option gives better control over the execution of the build.

## Migrating from Gradle 1.0-milestone-9

Please let us know if you encounter any issues during the upgrade to Gradle 1.0, that are not listed below.

### Deprecations

#### Command Line Options

##### <code>--cache</code>

The command line option <code>--cache</code> has been deprecated. Use <code>--recompile-scripts</code> or <code>--rerun-tasks</code> instead.

##### <code>--no-opt</code>

The command line option <code>--no-opt</code> has been deprecated. Use <code>--rerun-tasks</code> instead.

##### <code>--refresh</code>

The command line option <code>--refresh</code> has been deprecated. Use <code>--refresh-dependencies</code> instead.

#### API classes and methods

##### <code>Jvm</code></h5>

The class <code>org.gradle.util.Jvm</code> has been deprecated.

##### <code>StartParameter</code></h5>

The method <code>getCacheUsage()</code> has been deprecated. Use <code>isRecompileScripts()</code> and/or <code>isRerunTasks()</code> instead.

The method <code>setCacheUsage()</code> has been deprecated. Use <code>setRecompileScripts(boolean)</code> and/or <code>setRerunTasks(boolean)</code> instead.

The method <code>setRefreshOptions(RefreshOptions)</code> has been deprecated. Use <code>setRefreshDependencies(boolean)</code> instead.

The method <code>getRefreshOptions()</code> has been deprecated. Use <code>isRefreshDependencies()</code> instead.

The method <code>setNoOpt(boolean)</code> has been deprecated. Use <code>setRerunTasks(boolean)</code> instead.

The method <code>isNoOpt()</code> has been deprecated. Use <code>isRerunTasks()</code> instead.

##### <code>RefreshOptions</code>

The class <code>org.gradle.RefreshOptions</code> has been deprecated.

##### <code>CacheUsage</code>

The class <code>org.gradle.CacheUsage</code> has been deprecated.

### Potential breaking changes

#### Classpath changes for test process

The way we construct classpath for the JVM used to run tests has changed, to allow us to properly support running 
tests with <code>-javaagent</code> or <code>-Djava.security.manager</code>. We don't expect this will cause any 
problems and your tests should continue to run just fine.

#### Groovy compiler integration

We have introduced a new Groovy compiler integration in Gradle 1.0. This integration is much faster than the old 
Ant-task based integration, particularly for multi-project builds. We don't expect this will cause any problems 
and your code should continue to compile and run just fine.

## JIRA issues

See JIRA for details of the issues fixed in <a href="http://issues.gradle.org/secure/ReleaseNote.jspa?projectId=10001&version=10062">Gradle 1.0-rc-1</a>, <a href="http://issues.gradle.org/secure/ReleaseNote.jspa?projectId=10001&version=10460">Gradle 1.0-rc-2</a> and <a href="http://issues.gradle.org/secure/ReleaseNote.jspa?projectId=10001&version=10560">Gradle 1.0-rc-3</a>.