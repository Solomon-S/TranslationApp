# Final Project: Team D

## Authors
* Angeles
* Solomon
* Rees

## Project Summary
This program translates words from a given language to the desired language using the Google Cloud Translation API

## Build and run instructions
This project was created using IntelliJ Community Edition IDE

**Requirements:**
* Java Development Kit (JDK) 17
* Gradle 7.5.1
* Google Cloud Translation API key
- Cloned through: https://github.com/bsu-cs222-fall23-dll/FinalProject_TeamD
- Run the App through Gradle.
    - Find Gradle near the top right of the IDE
    - Tasks --> Application --> Run
  
**Configurations:**
1. Obtaining Your API Key:
   
This project requires a Cloud Translation API key. To obtain your google api key:
- Create a Google Cloud account  and set up a project
- Enable the Cloud Translation api
- Create your api credentials 

2. Implementing Your API Key
   
This project uses a configuration file to store the Cloud Translation API key.
To set up your local configuration:
- Open the `config.properties` file in the `src/main/resources/` directory.
- Replace the value for api.key=YourAPIKey123 with your Cloud Translation API key

## Additional Notes
- It is recommended that you navigate the app in full screen for optimal experience. 
- You may receive a warning about Google Credentials but this can be ignored as it has no impact on the program's functionality.
- If accent marks are not showing up properly try:
-     Go to Help > Edit Custom VM Options then paste: 
-     Dconsole.encoding=UTF-8
-     Dfile.encoding=UTF-8

