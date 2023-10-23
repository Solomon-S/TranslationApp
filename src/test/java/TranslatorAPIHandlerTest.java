import edu.bsu.cs222.TranslatorAPIHandler;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class TranslatorAPIHandlerTest {

    @Test
    public void testTranslation() {
        try {
            GoogleCredentials credentials = GoogleCredentials.fromStream(
                    TranslatorAPIHandler.class.getResourceAsStream("credentials/APIkey.json"));

            Translate translate = TranslateOptions.newBuilder().setCredentials(credentials).build().getService();

            Translation translation = translate.translate(
                    "Hello world",
                    Translate.TranslateOption.sourceLanguage("en"),
                    Translate.TranslateOption.targetLanguage("es")
            );

            String translatedText = translation.getTranslatedText();
            String expectedTranslation = "Hola mundo";
            assertEquals(expectedTranslation, translatedText);
        } catch (Exception e) {
            // Handle the exception, for example, by failing the test
            e.printStackTrace();
            fail("Failed to load credentials or perform translation");
        }
    }
}
