# Simple-Caching
Simple caching app based on the google recommended architecture, following the Unidirectional Data Flow Principle as showing in the picture below.
![architectura-dig-01-01](https://user-images.githubusercontent.com/84252625/212483253-8ba68d9c-58bd-4bec-9c50-deea28e3a66b.jpg)

# Data Flow
- The Ui layer represents the data that should be to the user, whenever the data changes, either due to user interaction (such as pressing a button) or external input (such as a network response) it should always update it, when the view need to get the data it calls the viewModel which calls the domain layer so it doesn't contain any business logic.
- The Domain layer is responsible for encapsulating complex business logic, or simple business logic that is reused by multiple viewModels. here it contains (repository interface, domain level models, the mapper and use cases) when a useCase need a data it calls the repository in the data layer.
- The Data layer contains the business logic. It's made of rules that determine how your app creates, stores, and changes data. in this example it contains (repository implementation, data sources which can be local or remote and theri models however we are returning the local data always applying Single Source Of Truth Principle).

# Screen Shots
![Screenshot_details](https://user-images.githubusercontent.com/84252625/212485599-271bb4de-e3bb-4937-945a-f1baa7d9571d.png)
![Screenshot_home](https://user-images.githubusercontent.com/84252625/212485606-029600f9-dfec-4d77-a42a-6a145c696d11.png)

# Used Here 
- [Kotlin](https://kotlinlang.org/) the modern and official programming language for Android development.
- [Retrofit](https://square.github.io/retrofit/) type-safe HTTP client for dealing with the remote server.
- [Dagger-Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependecy injection.
- [Room](https://developer.android.com/training/data-storage/room) SQLite object mapping library.
- [Coroutines](https://developer.android.com/kotlin/coroutines?gclsrc=ds&gclsrc=ds) to excute tasks in a background thread.
- [State-Flow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) to enable flows to optimally emit state updates and emit values to multiple consumers.
- [MVVM](https://developer.android.com/topic/libraries/architecture/viewmodel?gclsrc=ds&gclsrc=ds) as an architecture pattern.

# If this project helps you in anyway, it would be nice if you to star it ⭐
