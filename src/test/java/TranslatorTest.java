import edu.bsu.cs222.TranslationAPIConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class TranslatorTest {
    @Test
    public static void main(String[] throws IOException {
        TranslationAPIConnection connection = new TranslationAPIConnection();
        String text = "Hello world!";
        String translated = connection.translate(text);
        Assertions.assertEquals("Hola Mundo", translated);
    }
}
