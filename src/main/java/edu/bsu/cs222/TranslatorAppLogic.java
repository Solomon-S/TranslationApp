package edu.bsu.cs222;

import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import static edu.bsu.cs222.languageNameToCode.mapLanguageNameToCode;

public class TranslatorAppLogic {
    private final TranslatorAPIHandler translatorAPIHandler;
    private final TranslatorAppGui appGUI;
    private TextField inputTextField;
    private ComboBox<String> languageComboBox;
    private Label resultLabel;

    public TranslatorAppLogic(TranslatorAppGui appGUI) {
        this.appGUI = appGUI;
        this.translatorAPIHandler = new TranslatorAPIHandler("AIzaSyCefoMSOZ4NATjaLsTutfj_lATTwnURkp0"); // Replace with your actual API key
    }

    public Parent getRoot() {
        Label titleLabel = new Label("Welcome to the Translation App");
        Label inputLabel = new Label("Enter a word, phrase, or text in English to translate");
        inputTextField = new TextField();
        Button translateButton = new Button("Translate");
        resultLabel = new Label();
        languageComboBox = new ComboBox<>(supportedLanguages.supportedLanguages);
        languageComboBox.setPromptText("Select Target Language");
        translateButton.setOnAction(e -> translate());

        VBox root = new VBox(20);
        root.getChildren().addAll(titleLabel, inputLabel, inputTextField, languageComboBox, translateButton, resultLabel);
        root.setSpacing(20);

        return root;
    }
    private void translate() {
        String input = inputTextField.getText();
        String targetLanguageName = languageComboBox.getValue();
        String targetLanguage = mapLanguageNameToCode(targetLanguageName);

    }
}