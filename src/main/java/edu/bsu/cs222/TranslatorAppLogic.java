package edu.bsu.cs222;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

import static edu.bsu.cs222.languageNameToCode.mapLanguageNameToCode;

public class TranslatorAppLogic {
    private final TranslatorAPIHandler translatorAPIHandler;
    private final TranslatorAppGui appGUI;
    private TextField inputTextField;
    private ComboBox<String> languageComboBox;
    private Label resultLabel;

    public TranslatorAppLogic(TranslatorAppGui appGUI) {
        this.appGUI = appGUI;
        this.translatorAPIHandler = makeTranslatorApiHandler();
    }

    private TranslatorAPIHandler makeTranslatorApiHandler() {
        String apiKey = ReadConfigurations.getApiKey();
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("Missing API key");
        }
        return new TranslatorAPIHandler(apiKey);
    }

//        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
//            Properties properties = new Properties();
//            properties.load(input);
//            String apiKey = properties.getProperty("Translator_API_key");
//            if (apiKey == null || apiKey.isEmpty()) {
//                throw new IllegalArgumentException("Missing API key");
//            }
//            return new TranslatorAPIHandler(apiKey);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Error loading necessary files");
//        }

    public Parent getRoot() {
        Label titleLabel = new Label("Welcome to the Translation App");
        Label inputLabel = new Label("Enter a word, phrase, or text in English to translate");
        inputTextField = new TextField();
        Button translateButton = new Button("Translate");
        resultLabel = new Label();
        languageComboBox = new ComboBox<>(supportedLanguages.supportedLanguages);
        languageComboBox.setPromptText("Select Target Language");
        Button historyButton = new Button("View History");

        Font headerAndTextFont = new Font(36); // Define the header font size
        Font buttonFont = new Font(24); // Define the button font size

        FontUtility.setFontSize(headerAndTextFont, titleLabel, inputLabel, inputTextField, resultLabel);
        FontUtility.setFontSize(buttonFont, translateButton, historyButton, languageComboBox);

        translateButton.setOnAction(e -> translate());

        inputTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    translateButton.fire();
                }
            }
        });

        historyButton.setOnAction(e -> appGUI.showHistoryPage());

        // Create and return the root node
        VBox root = new VBox(20);
        root.getChildren().addAll(titleLabel, inputLabel, inputTextField, languageComboBox, translateButton, resultLabel, historyButton);
        root.setSpacing(20);

        return root;
    }

    private void translate() {
        String input = inputTextField.getText();
        String targetLanguageName = languageComboBox.getValue();
        String targetLanguage = mapLanguageNameToCode(targetLanguageName);

        if (!Objects.equals(input, "")) {
            if (targetLanguage == null) {
                resultLabel.setText("Language not recognized. Please try again.");
            } else {
                if (isInternetConnected()) {
                    String translationResult = translatorAPIHandler.translateText(input, "en", targetLanguage);
                    resultLabel.setText(translationResult);
                    appGUI.getTranslationHistory().add("English to " + targetLanguageName + ": " + input + " -> " + translationResult);
                } else {
                    resultLabel.setText("No internet connection.");
                }
            }
        } else {
            resultLabel.setText("Please enter a word, phrase, or text in English to translate!");
        }
    }
    boolean isInternetConnected() {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("https://www.google.com").openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return (responseCode == HttpURLConnection.HTTP_OK);
        } catch (IOException e) {
            return false;
        }
    }

    //For Testing no internet connection method by a using non-existent domain
    boolean noInternetConnection(){
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("https://thisdomaindoesnotexist12345.com").openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            return (responseCode == HttpURLConnection.HTTP_OK);
        } catch (IOException e) {
            return false;
        }
    }
}