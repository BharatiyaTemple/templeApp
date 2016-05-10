This is the application for managing temple visitors

To create this app the following instructions were followed in the order that it is stated below:


1. https://devcenter.heroku.com/articles/deploying-java-applications-to-heroku-from-eclipse-or-intellij-idea

2. Added the Postgres db addon from heroku Web console.

3.Followed the rest of the instructions from here: https://devcenter.heroku.com/articles/connecting-to-relational-databases-on-heroku-with-java

4. Ensure that the version for heroku-sdk is 1.0.0 in your pom.xml
<groupId>com.heroku.sdk</groupId>
<artifactId>heroku-maven-plugin</artifactId>
<version>1.0.0</version>

Temple app is deployed at https://troy-temple-library.herokuapp.com/
