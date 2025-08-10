package io.github.baddel73.dots.language;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class DotsColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Line comment", DotsSyntaxHighlighter.LINE_COMMENT),
            new AttributesDescriptor("Block comment", DotsSyntaxHighlighter.BLOCK_COMMENT),
            new AttributesDescriptor("Keyword", DotsSyntaxHighlighter.KEYWORD),
            new AttributesDescriptor("Type", DotsSyntaxHighlighter.TYPE),
            new AttributesDescriptor("Number", DotsSyntaxHighlighter.NUMBER),
            new AttributesDescriptor("Identifier", DotsSyntaxHighlighter.IDENTIFIER),
            new AttributesDescriptor("Operator", DotsSyntaxHighlighter.OPERATOR),
            new AttributesDescriptor("Brackets", DotsSyntaxHighlighter.BRACKETS),
            new AttributesDescriptor("Braces", DotsSyntaxHighlighter.BRACES),
            new AttributesDescriptor("Semicolon", DotsSyntaxHighlighter.SEMICOLON),
            new AttributesDescriptor("Comma", DotsSyntaxHighlighter.COMMA),
            new AttributesDescriptor("Attribute", DotsSyntaxHighlighter.ATTRIBUTE),
            new AttributesDescriptor("Boolean", DotsSyntaxHighlighter.BOOLEAN)
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return null; // You can set an icon for your language here
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new DotsSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return """
                // This is a line comment.
                /*
                  This is a block comment.
                */
                internal persistent struct MyStruct {
                    [key] int32 my_number = -123;
                    bool my_boolean = true;
                    string some_text = "hello";
                };
                
                enum MyEnum {
                    ONE = 1,
                    TWO = 2,
                }""";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "DOTS";
    }
}
