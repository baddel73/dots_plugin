package io.github.baddel73.dots.language;

import com.intellij.lexer.FlexAdapter;

public class DotsLexerAdapter extends FlexAdapter {
    public DotsLexerAdapter() {
        super(new DotsLexer(null));
    }
}
