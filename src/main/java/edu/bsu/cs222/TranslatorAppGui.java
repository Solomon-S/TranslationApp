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

public class TranslatorAppGui extends Application {
    private final ObservableList<String> translationHistory = FXCollections.observableArrayList();
    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Translation App");

        TranslatorAppLogic translatorApp = new TranslatorAppLogic(this);

        Scene scene = createScene(translatorApp.getRoot());
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    private Scene createScene(Parent root) {
        return new Scene(root, 1024, 576);
    }
    public ObservableList<String> getTranslationHistory() {
        return translationHistory;
    }
    public void showHistoryPage() {
        ListView<String> historyListView = new ListView<>(translationHistory);

        Button backButton = new Button("Back to Translation");

        backButton.setOnAction(e -> showTranslationPage());

        VBox historyPage = new VBox(20);
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
