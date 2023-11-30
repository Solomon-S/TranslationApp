package edu.bsu.cs222;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    private ComboBox<String> targetLanguageComboBox;
    private ComboBox<String> sourceLanguageComboBox;
    private Label resultLabel;
    private final TextArea notesText;

    public TranslatorAppLogic(TranslatorAppGui appGUI) {
        this.appGUI = appGUI;
        this.translatorAPIHandler = makeTranslatorApiHandler();
        this.notesText = new TextArea();

    }

    private TranslatorAPIHandler makeTranslatorApiHandler() {
        String apiKey = ReadConfigurations.getApiKey();
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("Missing API key");
        }
        return new TranslatorAPIHandler(apiKey);
    }


    public Parent getRoot() {
        Label titleLabel = new Label("Welcome to Ball State University's Translation App");
        Label inputLabel = new Label("Enter text for translation");
        inputTextField = new TextField();
        Button translateButton = new Button("Translate");
        resultLabel = new Label();
        sourceLanguageComboBox = new ComboBox<>(supportedLanguages.supportedLanguages);
        sourceLanguageComboBox.setPromptText("Select Source Language");
        targetLanguageComboBox = new ComboBox<>(supportedLanguages.supportedLanguages);
        targetLanguageComboBox.setPromptText("Select Target Language");
        Button historyButton = new Button("View History");

        Font headerAndTextFont = new Font(36);
        Font buttonFont = new Font(24);

        FontUtility.setFontSize(headerAndTextFont, titleLabel, inputLabel, inputTextField, resultLabel);
        FontUtility.setFontSize(buttonFont, translateButton, historyButton, targetLanguageComboBox, sourceLanguageComboBox);

        titleLabel.setStyle("-fx-background-color: #7D0000; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 3px; -fx-padding: 10px;");
        translateButton.setStyle("-fx-background-color: #333333; -fx-text-fill: white;-fx-border-color: black; -fx-border-width: 2px;");
        historyButton.setStyle("-fx-background-color: #333333; -fx-text-fill: white; -fx-text-fill: white;-fx-border-color: black; -fx-border-width: 2px;");



        translateButton.setOnAction(e -> translate());

        inputTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                translateButton.fire();
            }
        });
        historyButton.setOnAction(e -> appGUI.showHistoryPage());

        HBox titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);

        //Notes
        Label notesLabel = new Label("Notes:");
        notesLabel.setStyle("-fx-font-size: 28; -fx-background-color: #7D0000; -fx-text-fill: white; -fx-padding: 5px;");
        notesText.setPrefColumnCount(20);
        notesText.setPrefRowCount(20);

        Button notesButton = new Button("Clear Notes");
        notesButton.setStyle("-fx-background-color: #333333; -fx-text-fill: white; -fx-border-color: black; -fx-border-width: 2px; -fx-font-size: 24; -fx-padding: 10px;");
        notesButton.setOnAction(e -> notesText.clear());

        // Layout for Notes
        VBox notesBox = new VBox(10, notesLabel, notesText, notesButton);
        notesBox.setAlignment(Pos.CENTER);

        //HISTORY
        VBox root = new VBox(20);
        root.getChildren().addAll(titleLabel, inputLabel, inputTextField,sourceLanguageComboBox,targetLanguageComboBox,translateButton, resultLabel, historyButton, notesBox);
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);

        return root;
    }

    private void translate() {
        String input = inputTextField.getText();
        String sourceLanguageName = sourceLanguageComboBox.getValue();
        String sourceLanguage = mapLanguageNameToCode(sourceLanguageName);
        String targetLanguageName = targetLanguageComboBox.getValue();
        String targetLanguage = mapLanguageNameToCode(targetLanguageName);

        if (!Objects.equals(input, "")) {
            if (targetLanguage == null) {
                resultLabel.setText("Language not recognized. Please try again.");
            } else {
                if (isInternetConnected()) {
                    String translationResult = translatorAPIHandler.translateText(input, sourceLanguage, targetLanguage);
                    resultLabel.setText(translationResult);
                    appGUI.getTranslationHistory().add(sourceLanguageName + " to " + targetLanguageName + ": " + input + " -> " + translationResult);
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