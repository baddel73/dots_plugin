package io.github.baddel73.dots.file;

import com.intellij.openapi.fileTypes.LanguageFileType;
import io.github.baddel73.dots.language.DotsLanguage;

import javax.swing.*;

public class DotsFileType extends LanguageFileType {
    public static final DotsFileType INSTANCE = new DotsFileType();
    
    private DotsFileType() {
        super(DotsLanguage.INSTANCE);
    }

    @Override
    public String getName() {
        return "DOTS";
    }

    @Override
    public String getDescription() {
        return "Distributed Objects in Time and Space language file";
    }

    @Override
    public String getDefaultExtension() {
        return "dots";
    }

    @Override
    public Icon getIcon() {
        return DotsIcons.FILE;
    }
}
