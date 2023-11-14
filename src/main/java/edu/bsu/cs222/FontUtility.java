package edu.bsu.cs222;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class FontUtility {
    public static void setFontSize(Font font, javafx.scene.Node... nodes) {
        for (javafx.scene.Node node : nodes) {
            if (node instanceof Labeled) {
                ((Labeled) node).setFont(font);
            } else if (node instanceof TextField) {
                ((TextField) node).setFont(font);
            } else if (node instanceof ComboBox) {
                node.setStyle("-fx-font-size: " + font.getSize());
            } else if (node instanceof ListView) {
                node.setStyle("-fx-font-size: " + font.getSize());
            }
        }
    }
}