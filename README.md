This is IntelliJ Gradle project.

To open it at intelliJ do as following:

1. Open IntelliJ, File -> Open -> Project from exciting sources.
2. On import project screen select “Import Project from external model” and Gradle
3. Select Use customizable gradle wrapper and finish
4. Expand project tree, open Tests.java classes, select one of tests mehods, right click and run

The project supports command line parameters for environment and browser.
For example: -Denv=prod browser=FIREFOX
Default is Chrome

cd C:\tmp\SeleniumDemo
git pull https://github.com/denis-mitin/SeleniumDemo.git

to rul all tests, type at command line: gradle clean test

To run specific class or test case with gradle:
gradle clean test --tests *Search*