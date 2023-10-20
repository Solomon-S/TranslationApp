package edu.bsu.cs222;

import java.io.IOException;
import java.util.Scanner;

public class Translator {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a word to translate into Spanish: ");
        String translatingWord = scanner.nextLine();
        TranslationAPIConnection connection = new TranslationAPIConnection();
        System.out.println("Translated Word: " + connection.translate(translatingWord));

    }
}
