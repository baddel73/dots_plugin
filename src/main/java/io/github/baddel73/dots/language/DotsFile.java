package io.github.baddel73.dots.language;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import io.github.baddel73.dots.file.DotsFileType;
import org.jetbrains.annotations.NotNull;

public class DotsFile extends PsiFileBase {

    public DotsFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, DotsLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return DotsFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "DOTS File";
    }
}
