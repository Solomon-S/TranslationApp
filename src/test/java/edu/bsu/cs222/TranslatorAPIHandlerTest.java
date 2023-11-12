package edu.bsu.cs222;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import java.util.Properties;
import java.io.InputStream;

public class TranslatorAPIHandlerTest {

    @Test
    public void testTranslationPhrase() {
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
            Properties properties = new Properties();
            properties.load(input);

            String apiKey = properties.getProperty("Translator_API_key");
            Translate translate = TranslateOptions.newBuilder().setApiKey(apiKey).build().getService();
            Translation translation = translate.translate(
                    "Hello world",
                    Translate.TranslateOption.sourceLanguage("en"),
                    Translate.TranslateOption.targetLanguage("es")
            );

            String translatedText = translation.getTranslatedText().toLowerCase();
            String expectedTranslation = "Hola mundo".toLowerCase();
            assertEquals(expectedTranslation, translatedText);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Translation Failure");
        }
    }

    @Test
    public void testTranslationWord() {
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
            Properties properties = new Properties();
            properties.load(input);

            String apiKey = properties.getProperty("Translator_API_key");

            Translate translate = TranslateOptions.newBuilder().setApiKey(apiKey).build().getService();

            Translation translation = translate.translate(
                    "Hello",
                    Translate.TranslateOption.sourceLanguage("en"),
                    Translate.TranslateOption.targetLanguage("es")
            );

            String translatedText = translation.getTranslatedText().toLowerCase();
            String expectedTranslation = "Hola".toLowerCase();
            assertEquals(expectedTranslation, translatedText);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Translation Failure");
        }
    }
}
