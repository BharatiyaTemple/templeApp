This is the application for managing temple visitors. Major software development contributors for this project are from Craig Rodewald and Kawita Kandpal.

To create this app on heroku the following instructions were followed in the order that is stated below:


1. https://devcenter.heroku.com/articles/deploying-java-applications-to-heroku-from-eclipse-or-intellij-idea

2. Added the Postgres db addon from heroku Web console.

3.Followed the rest of the instructions from here: https://devcenter.heroku.com/articles/connecting-to-relational-databases-on-heroku-with-java

4. Ensure that the version for heroku-sdk is 1.0.0 in your pom.xml
<groupId>com.heroku.sdk</groupId>
<artifactId>heroku-maven-plugin</artifactId>
<version>1.0.0</version>

5. If you are getting exceptions whilte build you might have to tweak your settings.xml file. Follow the cue (website link shown) on the build failture.

Temple app is deployed at https://troy-temple-library.herokuapp.com/
