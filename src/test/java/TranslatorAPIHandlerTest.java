import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class TranslatorAPIHandlerTest {

    @Test
    public void testTranslation() {
        try {
            String apiKey = "AIzaSyCefoMSOZ4NATjaLsTutfj_lATTwnURkp0";

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
}
