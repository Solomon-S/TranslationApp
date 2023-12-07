package edu.bsu.cs222;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;

public class TranslatorAppGui extends Application {
    private final ObservableList<String> translationHistory = FXCollections.observableArrayList();
    private Stage primaryStage;

    private static final String Light_Mode_style = "/lightMode.css";
    private static final String Dark_Mode_style = "/darkMode.css";
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Translation App");

        TranslatorAppLogic translatorApp = new TranslatorAppLogic(this);

        Scene scene = createScene(translatorApp.getRoot());
        primaryStage.setScene(scene);
        primaryStage.show();
        setLightMode();

    }
    private Scene createScene(Parent root) {
        return new Scene(root, 1024, 950);
    }

    public void setLightMode() {
        if (primaryStage != null && primaryStage.getScene() != null) {
            primaryStage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource(Light_Mode_style)).toExternalForm());
            primaryStage.getScene().getStylesheets().remove(Objects.requireNonNull(getClass().getResource(Dark_Mode_style)).toExternalForm());
        }
    }

    public void setDarkMode() {
        primaryStage.getScene().getStylesheets().clear();
        primaryStage.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource(Dark_Mode_style)).toExternalForm());

    }

    public ObservableList<String> getTranslationHistory() {
        return translationHistory;
    }
    public void showHistoryPage() {
        ListView<String> historyListView = new ListView<>(translationHistory);
        Font largeFont = new Font(24); // Define the common font size
        FontUtility.setFontSize(largeFont, historyListView);
        historyListView.setStyle("-fx-background-color: #F0F0F0;");

        Button backButton = new Button("Back to Translation");
        FontUtility.setFontSize(largeFont, backButton);

        backButton.setStyle("-fx-background-color: #7D0000; -fx-text-fill: white;");
        backButton.setOnAction(e -> showTranslationPage());

        VBox historyPage = new VBox(20);
        historyPage.setStyle("-fx-padding: 20;");
        historyPage.getChildren().addAll(historyListView, backButton);

        Scene historyScene = createScene(historyPage);
        primaryStage.setScene(historyScene);
    }
    public void showTranslationPage() {
        primaryStage.setTitle("Translation App");
        TranslatorAppLogic translatorApp = new TranslatorAppLogic(this);

        Scene translationScene = createScene(translatorApp.getRoot());
        primaryStage.setScene(translationScene);
    }

}
