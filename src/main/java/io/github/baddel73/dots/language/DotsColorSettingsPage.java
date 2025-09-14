package io.github.baddel73.dots.language;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import io.github.baddel73.dots.file.DotsIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;
import java.util.HashMap;

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
            new AttributesDescriptor("Boolean", DotsSyntaxHighlighter.BOOLEAN),
            new AttributesDescriptor("Custom type", DotsSyntaxHighlighter.CUSTOM_TYPE)
    };

    @Override
    public @Nullable Icon getIcon() {
        return DotsIcons.FILE;
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
                
                enum MyEnum {
                    1: first,
                    2: second,
                }
                
                struct MyStruct [substruct_only] {
                    1: [key] int32 my_number;
                    2: bool my_boolean;
                    3: string some_text;
                    4: <custom>MyEnum</custom> my_enum;
                };""";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        Map<String, TextAttributesKey> map = new HashMap<>();
        // ... other mappings
        map.put("custom", DotsSyntaxHighlighter.CUSTOM_TYPE);
        return map;
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
