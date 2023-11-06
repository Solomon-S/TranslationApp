package edu.bsu.cs222;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TranslatorAppGui extends Application {
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
}
