# BowlingScoreKata
This is a sample app to demonstrate the Bowling scoreboard, that uses Clean Architecture with MVVM and Jetpack Compose for UI screens.

# Rules
- A game consists of ten frames, which start with a full rack of ten pins.
- In each frame, you have 2 throws of your ball to knock down all 10 pins.
- If you knock down all the pins on your first ball, it is called a `strike`.
- Points for `strike` frame is 10 plus next two ball as bonus (ex: if you score a `strike` in the `1st` frame, then an 7 and 1 in the `2nd` frame, you would score 18 (10+7+1) for the first frame, and 8 for the second frame).
- If you knock down some of the pins on the first ball, and knocked down the remainder of the pins in the second ball, it is known as a `spare`.
- Points for `spare` frame is 10 plus next ball as bonus (ex: if you score a `spare` in the `1st` frame, say an 6 and a 4, then got an 8 and a 1 in the `2nd` frame, you would score 18 (6+4+8) for the first frame, and 9 for the second frame).
- In the final 10th frame, you get bonus balls if you strike or spare, to a maximum of three deliveries.

# Application Screens
![initial_screen](https://github.com/vinayjainmaya/BowlingScoreKata/blob/master/screen/initial.png?raw=true "Initial Screen")
![tenth_frame](https://github.com/vinayjainmaya/BowlingScoreKata/blob/master/screen/tenth_frame.png?raw=true "Tenth Frame")
![error_screen](https://github.com/vinayjainmaya/BowlingScoreKata/blob/master/screen/error.png?raw=true "Error Screen")
![result_screen](https://github.com/vinayjainmaya/BowlingScoreKata/blob/master/screen/result.png?raw=true "Result Screen")

### Libraries
- [Jetpack](https://developer.android.com/jetpack)
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manage UI data to survive configuration changes.
    - [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#stateflow) - is a state-holder observable flow that emits the current and new state updates to its collectors.
    - [Compose](https://developer.android.com/jetpack/compose) - Jetpack Compose is the modern toolkit for building native Android UI
- [Hilt](https://dagger.dev/hilt/) - A fast dependency injector for Android.



