package edu.bsu.cs222;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TranslatorAppLogicTest {

    @Test
    public void testIsInternetConnected() {
        TranslatorAppLogic translatorAppLogic = new TranslatorAppLogic(null);
        assertTrue(translatorAppLogic.isInternetConnected());
    }

    @Test
    public void testIsInternetConnected_NonExistentDomain() {
        TranslatorAppLogic translatorAppLogic = new TranslatorAppLogic(null);
        assertFalse(translatorAppLogic.isInternetConnectedNoDomain());
    }
}
