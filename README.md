
# WillyWonkaChocolateFactory Sample App

**WillyWonkaChocolateFactory Sample App for Napptilus**


Sample master/detail app that consumes a Napptilus Oompa Loompa API to display a workers list & worker details It has been built with principles of clean architecture, repository pattern and MVVM pattern, and applying SOLID principles.

The MVP displays a list of workers (Oompa Loompas), managed by Remote Mediator with Pager3 and local caching, and a worker detail, that provides extra information and cache to the worker. All the user interface was done with Compose, and Koin has been used as a dependency injector. Some unit tests have also been done.


**Libraries used:**
- **Compose:** A declarative UI toolkit for building native Android apps using Kotlin that allows for easier and more flexible UI development.
- **Koin:** A lightweight dependency injection framework for Kotlin that simplifies the management of dependencies.
- **Flow & Coroutines:** Kotlin libraries for asynchronous programming that enable to write non-blocking and highly responsive code.
- **Paging3:** A library for handling pagination allowing to efficiently load and display large data sets.
- **Timber:** A logging library that provides more advanced logging features than the built-in Android logging system.
- **Coil:** A fast and lightweight image loading library that makes it easy to load and display images in app views.
- **Room:** A database library that provides an abstraction layer over SQLite and enables to easily store and retrieve data.
- **Retrofit:** A type-safe HTTP client that simplifies the process of making network requests and handling responses.
- **Okhttp3:** A powerful and efficient HTTP client that provides advanced features such as connection pooling and caching.
- **Junit & Mockk:** Testing libraries for Kotlin and Android that provide tools for writing and executing unit tests and mocking dependencies.
