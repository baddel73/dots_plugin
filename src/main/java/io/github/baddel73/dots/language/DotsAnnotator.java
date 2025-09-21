package io.github.baddel73.dots.language;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import io.github.baddel73.dots.language.psi.DotsCustomType;
import org.jetbrains.annotations.NotNull;

public class DotsAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof DotsCustomType) {
            PsiReference reference = element.getReference();
            if (reference != null && reference.resolve() == null) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Unresolved type: " + element.getText())
                        .create();
            } else {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                        .textAttributes(DotsSyntaxHighlighter.CUSTOM_TYPE)
                        .create();
            }
        }
    }
}
