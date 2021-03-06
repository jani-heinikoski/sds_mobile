# sds_mobile
This is a code repository containing everything required to pass the course Software Development Skills: Mobile 2020-21. Repository is authored only by Jani Heinikoski.
I have taken the liberty to slightly modify the course module exercises (made them a little bit more complex so I could refresh my memory for the course project).

WHERE AND WHAT

The learning diary can be found under directory ./documentation.
Course module tasks can be found as Android Studio projects under ./modules and project under ./project.
A brief demonstration video about the course project running & working is available at https://drive.google.com/file/d/1COIHV9fhcSZCUrt0K8hdOrcvQNEHNDz7/view?usp=sharing
The project's idea is my version of the coinline's speed test game (featured e.g. in a finnish game show called "Speden Spelit"). The game highlights buttons in a certain order and the player needs to click the buttons in the same sequence. The project is fully lifecycle aware; you can rotate your screen or navigate away and back from the app and it will recover its state. It uses an SQLite database to store the high scores (locally) and a recycler view to display them.

HOW TO INSTALL

1. Download and install the latest Android Studio version from https://developer.android.com/studio (Tested on 4.1.1)
2. Download the contents of this repository as a ZIP https://github.com/jani-heinikoski/sds_mobile/archive/main.zip
3. Extract its contents into your file system
4. Open Android Studio and choose Open File or Project (under File tab if you have already opened an arbitrary project)
5. Navigate to the path where you extracted the aforementioned ZIP file
6. Select any of the four Android Studio projects (see WHERE AND WHAT section), click it and click OK
7. Let Gradle finish its autoconfiguration (downloads necessary dependencies etc.)
8. Choose your Android Virtual Device (if you don't have one, you can either use your own Android mobile device or create a new one using the AVD -manager)
9. Click the Run 'app' button (green play button in the top section of Android Studio, hotkey default is Shift+F10 on MS Windows 10)


