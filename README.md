# Virtusa Test
An Android application consuming the [Dog Ceo API]([https://developer.github.com/v3/](https://dog.ceo/api/)) to show list of dog breeds and their image.
I've created this project to showcase these practices - MVVM + Clean Architecture + DI using Dagger Hilt + Unit Testing + Instrumentation Testing + CI/CD with Github Actions.


### Screenshots
<img src="https://github.com/abhishektiwarijr/VirtusaTest/assets/25899457/bf61af75-f9a8-4ce0-9dcf-5a3b71a6a02e" width=300/>
<img src="https://github.com/abhishektiwarijr/VirtusaTest/assets/25899457/aa0bf317-0ccb-45e8-b738-e0cd6a05ea7a" width=300/> 


## Libraries
- [Jetpack](https://developer.android.com/jetpack)🚀
    - [Viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manage UI
      related data in a lifecycle conscious way
    - [Compose](https://developer.android.com/courses/pathways/compose) - A modern declarative way
      to build android ui
    - [Navigation](https://developer.android.com/jetpack/compose/navigation) - Handle everything needed for in-app navigation.
    - [ViewModel](https://developer.android.com/jetpack/compose/state#viewmodel-state) - Easily schedule asynchronous tasks for optimal execution.
    - [Room](https://developer.android.com/topic/libraries/architecture/room) - Persistence library for Android.
- [Retrofit](https://square.github.io/retrofit/) - Type-safe HTTP client and supports coroutines out
  of the box. Used for the network calls.
- [Gson](https://github.com/google/gson) - Used to convert JSON to Java/Kotlin classes for the
  Retrofit
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for
  coroutines
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Used for
  Dependency injection
- [Coil](https://coil-kt.github.io/coil/compose/) - Allows for fetching and displaying of images in
  the composables
- [CI/CD Github Actions](https://github.blog/2022-02-02-build-ci-cd-pipeline-github-actions-four-steps/) - A quick guide on the advantages of using GitHub Actions
  as your preferred CI/CD tool—and how to build a CI/CD pipeline with it.
