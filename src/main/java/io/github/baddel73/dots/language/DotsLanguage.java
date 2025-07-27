package io.github.baddel73.dots.language;

import com.intellij.lang.Language;

public class DotsLanguage extends Language {
    public static final DotsLanguage INSTANCE = new DotsLanguage();

    private DotsLanguage() {
        super("DOTS");
    }
}
