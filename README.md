This is IntelliJ Gradle project.

To open it at intelliJ do as following:

1. Open IntelliJ, File -> Open -> Project from exciting sources.
2. On import project screen select “Import Project from external model” and Gradle
3. Select Use customizable gradle wrapper and finish
4. Expand project tree, open LibraryTests.java class, select one of 5 tests, right click and run

The project supports command line parameters for environment and browser.
For example: -Denv=prod browser=CHROME

or
gradlew clean test --tests *CYBR_9237*