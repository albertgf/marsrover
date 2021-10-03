Code Challenge Mars Rover for Android
=====================================

---

this repository contains an Android application with a solution to the Mars Rover Code Challenge 

This application is based on Compose.

* ''GeneratorActivity'' shows an UI to setup mars rover input, send it

![GeneratorActivityScreenshot][generatorActivityScreenshot]

* ''ResultScreen'' shows the result of the mars rover exploration'

![GeneratorActivityResultScreenshot][generatorActivityresultScreenshot]

the application is based on a multi-module project, with MVVM architecture and test the main functionalities

to build the code challenge

1. Clone the repository
2. sync gradle
3. build project
4. Execute the application

## Considerations

* This project uses KOIN as DI
* The description of the project put as constraint a Activity/Fragment implementation but I wanted to work with compose and the backdropScaffold component fit perfectly in the UI that I imagine it.
* I worked with compose to see it's capabilities in a real environment as a Android dev.
* The mars rover "problem" is solved in the 'core' module. at the end of the project I realise that is not the best naming and even I should build it in a kotlin/java library without any android dependencies
* I worked the mars rover thinking about new features could be implemented, that's the reason behind 'navsystem'
* I think that viewmodel stateflow fields can be moved to a RoverDataUI unique stateflow but I didn't have the time to iterate again the dev process

## Process of the code challenge
1. Create basic test and solve the challenge using a basic OO point of view
2. Added the json parser with moshi and test it.
3. Create the domain module with the repository to send the data to the mars rover and get the answer.
4. Create a basic layout with all the inputs necessary to send it to the rovers
5. Link domain and ui with a viewmodel using StateFlow to manage the link between data and UI
6. Create a basic layout of response that's going to be updated with differents states
7. Update repository to Flow to have a responsive state of all the rover status
8. Update layouts with final UI design
9. Refactor compose components to be more reusable in the future
10. Update Rovers with Error handling with Either from arrow lib
11. Test compose components

[generatorActivityScreenshot]: ./art/generatorActivityScreenshot.png
[generatorActivityresultScreenshot]: ./art/generatorActivityresultScreenshot.png