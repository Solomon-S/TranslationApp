package edu.bsu.cs222;

import java.util.Scanner;

public class TranslatorApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Ball State Translation App");
        System.out.println("Enter word in English to translate to Spanish:");

        String input = scanner.nextLine();
        String apiKey = "AIzaSyCefoMSOZ4NATjaLsTutfj_lATTwnURkp0";

        try {
            TranslatorAPIHandler translatorAPIHandler = new TranslatorAPIHandler(apiKey);
            String translatedText = translatorAPIHandler.translateText(input, "en", "es");

            System.out.println("Translation: " + translatedText);
        } catch (TranslationException e) {
            System.err.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
