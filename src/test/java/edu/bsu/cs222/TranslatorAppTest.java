package edu.bsu.cs222;

import org.junit.jupiter.api.Test;

public class TranslatorAppTest {

    @Test
    public void testTranslationAppWithoutErrors() {
        String[] args = {""};

        try {
            TranslatorApp.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
