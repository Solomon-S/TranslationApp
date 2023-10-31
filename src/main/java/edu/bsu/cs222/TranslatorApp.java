package edu.bsu.cs222;

import java.util.Scanner;

import static edu.bsu.cs222.languageNameToCode.mapLanguageNameToCode;

public class TranslatorApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (scanner) {
            System.out.println("Welcome to the Translation App");
            System.out.println("Enter a word, phrase, or text in English to translate:");
            String input = scanner.nextLine();
            String apiKey = "AIzaSyCefoMSOZ4NATjaLsTutfj_lATTwnURkp0";
            TranslatorAPIHandler translatorAPIHandler = new TranslatorAPIHandler(apiKey);
            System.out.println("Enter the target language (e.g., 'Spanish', 'Japanese', 'French'):");
            String targetLanguageName = scanner.nextLine();
            String targetLanguage = mapLanguageNameToCode(targetLanguageName);
            if (targetLanguage == null) {
                System.err.println("Language not recognized. Exiting.");
                return;
            }
            String translatedText = translatorAPIHandler.translateText(input, "en", targetLanguage);
            System.out.println("Translation: " + translatedText);
        } catch (TranslationException e) {
            System.err.println(e.getMessage());
        }
    }
}