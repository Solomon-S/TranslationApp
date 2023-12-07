# Final Project: Team D

## Authors
* Angeles
* Solomon
* Rees

## Project Summary
This program translates words from a given language to the desired language using the Google Cloud Translation API

## Build and run instructions
This project was created using IntelliJ Community Edition IDE

*Requirements:*
* Java Development Kit (JDK) 17
* Gradle 7.5.1
* Google Cloud Translation API key
- Cloned through: https://github.com/bsu-cs222-fall23-dll/FinalProject_TeamD
- Run the App through Gradle.
    - Find Gradle near the top right of the IDE
    - Tasks --> Application --> Run
  
*Configurations:*
This project uses a configuration file to store the Google API key. To set up your local configuration:
1. Create a `config.properties` file in the `src/main/resources/` directory as a project resource directory
2. Include the following:
api.key=YourAPIKey123
(replace YourAPIKey123 with your actual API key.
Note not to include any additional spaces)
3. If accent marks are not showing up properly try:
- Go to Help > Edit Custom VM Options then paste: 
- Dconsole.encoding=UTF-8
- Dfile.encoding=UTF-8

## Additional Notes
- It is recommended that you navigate the app in full screen for optimal experience. 
- You may receive a warning about Google Credentials but this can be ignored as it has no impact on the program's functionality. 

