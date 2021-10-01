Code Challenge Mars Rover for Android
=====================================

---

this repository contains an Android application with a solution to the Mars Rover Code Challenge 

This application is based on Compose.

* ''GeneratorActivity'' shows an UI to setup mars rover input, send it and show the result
![GeneratorActivityScreenshot][generatorActivityScreenshot]
  
![GeneratorActivityResultScreenshot][generatorActivityresultScreenshot]

the application is based on a multi-module project, with MVVM architecture and test the main functionalities

to build the code challenge

1. Clone the repository
2. sync gradle
3. build project
4. Execute the application

##Considerations

* This project uses KOIN as DI
* The description of the project put as constraint a Activity/Fragment implementation but I wanted to work with compose and the backdropScaffold component fit perfectly in the UI that I imagine it.
* I worked with compose to see it's capabilities in a real environment as a Android dev.
* The mars rover "problem" is solved in the 'core' module. at the end of the project I realise that is not the best naming and even I should build it in a kotlin/java library without any android dependencies
* I worked the mars rover thinking about new features could be implemented, that's the reason behind 'navsystem'

## Process of the code challenge
1. Create basic test and solve the challenge using a basic OO point of view
2. Refactor this approach adding some Option feature of the arrow FP library but it was only for error handling so I removed.
3. Added the json parser with moshi and test it.
4. Create the domain module with the repository to send the data to the mars rover and get the answer.
5. Create a basic layout with all the inputs necessary to send it to the rovers
6. Link domain and ui with a viewmodel using StateFlow to manage the link between data and UI
7. Create a basic layout of response that's going to be updated with differents states
8. Update repository to Flow to have a responsive state of all the rover status
9. Update layouts with final UI design
10. Refactor compose components to be more reusable in the future

[generatorActivityScreenshot]: ./art/generatorActivityScreenshot.png
[generatorActivityresultScreenshot]: ./art/generatorActivityresultScreenshot.png