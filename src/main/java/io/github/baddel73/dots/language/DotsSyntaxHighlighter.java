package io.github.baddel73.dots.language;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import io.github.baddel73.dots.language.psi.DotsTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class DotsSyntaxHighlighter extends SyntaxHighlighterBase {

    // Define keys for comments
    public static final TextAttributesKey LINE_COMMENT =
            createTextAttributesKey("DOTS_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BLOCK_COMMENT =
            createTextAttributesKey("DOTS_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);

    // Define text attribute keys for different token types
    public static final TextAttributesKey KEYWORD =
            createTextAttributesKey("DOTS_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);

    public static final TextAttributesKey TYPE =
            createTextAttributesKey("DOTS_TYPE", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL);

    public static final TextAttributesKey CUSTOM_TYPE =
            createTextAttributesKey("DOTS_CUSTOM_TYPE", DefaultLanguageHighlighterColors.CLASS_NAME);

    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    public static final TextAttributesKey NUMBER =
            createTextAttributesKey("DOTS_NUMBER", DefaultLanguageHighlighterColors.NUMBER);

    public static final TextAttributesKey IDENTIFIER =
            createTextAttributesKey("DOTS_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);

    public static final TextAttributesKey OPERATOR =
            createTextAttributesKey("DOTS_OPERATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN);

    public static final TextAttributesKey BRACKETS =
            createTextAttributesKey("DOTS_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS);

    public static final TextAttributesKey BRACES =
            createTextAttributesKey("DOTS_BRACES", DefaultLanguageHighlighterColors.BRACES);

    public static final TextAttributesKey SEMICOLON =
            createTextAttributesKey("DOTS_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON);

    public static final TextAttributesKey COMMA =
            createTextAttributesKey("DOTS_COMMA", DefaultLanguageHighlighterColors.COMMA);

    public static final TextAttributesKey ATTRIBUTE =
            createTextAttributesKey("DOTS_ATTRIBUTE", DefaultLanguageHighlighterColors.METADATA);

    public static final TextAttributesKey BOOLEAN =
            createTextAttributesKey("DOTS_BOOLEAN", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL);

    private static final TextAttributesKey[] LINE_COMMENT_KEYS = new TextAttributesKey[]{LINE_COMMENT};
    private static final TextAttributesKey[] BLOCK_COMMENT_KEYS = new TextAttributesKey[]{BLOCK_COMMENT};
    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEYWORD};
    private static final TextAttributesKey[] TYPE_KEYS = new TextAttributesKey[]{TYPE};
    private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER};
    private static final TextAttributesKey[] IDENTIFIER_KEYS = new TextAttributesKey[]{IDENTIFIER};
    private static final TextAttributesKey[] OPERATOR_KEYS = new TextAttributesKey[]{OPERATOR};
    private static final TextAttributesKey[] BRACKETS_KEYS = new TextAttributesKey[]{BRACKETS};
    private static final TextAttributesKey[] BRACES_KEYS = new TextAttributesKey[]{BRACES};
    private static final TextAttributesKey[] SEMICOLON_KEYS = new TextAttributesKey[]{SEMICOLON};
    private static final TextAttributesKey[] COMMA_KEYS = new TextAttributesKey[]{COMMA};
    private static final TextAttributesKey[] ATTRIBUTE_KEYS = new TextAttributesKey[]{ATTRIBUTE};
    private static final TextAttributesKey[] BOOLEAN_KEYS = new TextAttributesKey[]{BOOLEAN};

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new DotsLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType) {
        // Add cases for comments at the top for clarity
        if (tokenType.equals(DotsTypes.LINE_COMMENT)) {
            return LINE_COMMENT_KEYS;
        }
        if (tokenType.equals(DotsTypes.BLOCK_COMMENT)) {
            return BLOCK_COMMENT_KEYS;
        }
    
        // ... rest of the getTokenHighlights method
        
        // Keywords: struct, enum
        if (tokenType.equals(DotsTypes.STRUCT) || tokenType.equals(DotsTypes.ENUM)) {
            return KEYWORD_KEYS;
        }
        
        // Primitive types
        if (tokenType.equals(DotsTypes.BOOL) ||
            tokenType.equals(DotsTypes.INT_8) || tokenType.equals(DotsTypes.INT_16) || 
            tokenType.equals(DotsTypes.INT_32) || tokenType.equals(DotsTypes.INT_64) ||
            tokenType.equals(DotsTypes.UINT_8) || tokenType.equals(DotsTypes.UINT_16) || 
            tokenType.equals(DotsTypes.UINT_32) || tokenType.equals(DotsTypes.UINT_64) ||
            tokenType.equals(DotsTypes.FLOAT_32) || tokenType.equals(DotsTypes.FLOAT_64) || 
            tokenType.equals(DotsTypes.FLOAT_128) ||
            tokenType.equals(DotsTypes.DURATION) || tokenType.equals(DotsTypes.TIME_POINT) || 
            tokenType.equals(DotsTypes.STEADY_TIMEPOINT) ||
            tokenType.equals(DotsTypes.STRING) || tokenType.equals(DotsTypes.PROPERTY_SET) ||
            tokenType.equals(DotsTypes.VECTOR)) {
            return TYPE_KEYS;
        }
        
        // Struct/enum attributes
        if (tokenType.equals(DotsTypes.CACHED) || tokenType.equals(DotsTypes.CLEANUP) ||
            tokenType.equals(DotsTypes.SUBSTRUCT_ONLY) || tokenType.equals(DotsTypes.PERSISTENT) ||
            tokenType.equals(DotsTypes.INTERNAL) || tokenType.equals(DotsTypes.KEY)) {
            return ATTRIBUTE_KEYS;
        }
        
        // Boolean literals
        if (tokenType.equals(DotsTypes.FALSE) || tokenType.equals(DotsTypes.TRUE)) {
            return BOOLEAN_KEYS;
        }
        
        // Numbers
        if (tokenType.equals(DotsTypes.NUMBER)) {
            return NUMBER_KEYS;
        }
        
        // Identifiers
        if (tokenType.equals(DotsTypes.IDENTIFIER_TOKEN)) {
            return IDENTIFIER_KEYS;
        }
        
        // Operators
        if (tokenType.equals(DotsTypes.EQ) || tokenType.equals(DotsTypes.LT) || 
            tokenType.equals(DotsTypes.GT) || tokenType.equals(DotsTypes.COLON)) {
            return OPERATOR_KEYS;
        }
        
        // Brackets
        if (tokenType.equals(DotsTypes.LBRACK) || tokenType.equals(DotsTypes.RBRACK)) {
            return BRACKETS_KEYS;
        }
        
        // Braces
        if (tokenType.equals(DotsTypes.LBRACE) || tokenType.equals(DotsTypes.RBRACE)) {
            return BRACES_KEYS;
        }
        
        // Semicolon
        if (tokenType.equals(DotsTypes.SEMICOLON)) {
            return SEMICOLON_KEYS;
        }
        
        // Comma
        if (tokenType.equals(DotsTypes.COMMA)) {
            return COMMA_KEYS;
        }
        
        return EMPTY_KEYS;
    }
}