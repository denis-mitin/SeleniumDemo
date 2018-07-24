This is IntelliJ Gradle project.

To open it at intelliJ do as following:

1. Open IntelliJ, File -> Open -> Project from exciting sources.
2. On import project screen select “Import Project from external model” and Gradle
3. Select Use customizable gradle wrapper and finish
4. Expand project tree, open Tests.java classes, select one of tests mehods, right click and run

The project supports command line parameters for environment and browser.
For example: -Denv=dev browser=FIREFOX
Default is Chrome

To rul all tests with default browser (Chrome) and default env (prod):

git pull https://github.com/denis-mitin/SeleniumDemo.git
cd C:\SeleniumDemo
gradle clean test


Run on specific environment (dev) at Firefox:

gradle clean test -Denv=dev -Dbrowser=FIREFOX


Run specific class or test case with gradle:
gradle clean test --tests *Search*