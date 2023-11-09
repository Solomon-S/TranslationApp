package edu.bsu.cs222;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

@SuppressWarnings("ALL")
class TranslatorAPIHandler {
    private final Translate translate;
    public TranslatorAPIHandler(String apiKey) {
        try {
            translate = TranslateOptions.newBuilder().setApiKey(apiKey).build().getService();
        } catch (Exception exception) {
            throw new TranslationException("Failure to initialize");
        }
    }
    public String translateText(String text, String sourceLanguage, String targetLanguage) {
        validateInput(text, sourceLanguage, targetLanguage);
        try {
            Translation translation = translate.translate(
                    text,
                    Translate.TranslateOption.sourceLanguage(sourceLanguage),
                    Translate.TranslateOption.targetLanguage(targetLanguage)
            );
            return translation.getTranslatedText();
        } catch (Exception e) {
            throw new TranslationException("An error occurred during translation");
        }
    }
    private void validateInput(String text, String sourceLanguage, String targetLanguage) {
        if (sourceLanguage == null || targetLanguage == null || text == null) {
            throw new IllegalArgumentException("Invalid input");
        }
    }
}