#ISSUE TRACKER

##Installation Guide

### Installing JDK 11
Go to this website:
https://www.oracle.com/java/technologies/javase-jdk11-downloads.html

Install the JDK to your Local machine and set JAVA_HOME system Environment to your Java path.

### Installing maven
To install Maven on windows, head over to Apache Maven site to download the latest version, select the Maven zip file, for example apache-maven-3.3.9-bin.zip.
https://maven.apache.org/download.cgi

Add both M2_HOME and MAVEN_HOME variables to the Windows environment using system properties, and point it to your Maven folder.

Update the PATH variable by appending the Maven bin folder – %M2_HOME%\bin, so that you can run the Maven’s command everywhere.

To verify it run:

_mvn -version_

in the command prompt.

### Run project
Go to project folder and run mvn command
_cd issue-tracker_
_mvn spring-boot:run_
