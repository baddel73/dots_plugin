package io.github.baddel73.dots.language;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import io.github.baddel73.dots.language.psi.DotsCustomType;
import org.jetbrains.annotations.NotNull;

public class DotsAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof DotsCustomType) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .textAttributes(DotsSyntaxHighlighter.CUSTOM_TYPE)
                    .create();
        }
    }
}
