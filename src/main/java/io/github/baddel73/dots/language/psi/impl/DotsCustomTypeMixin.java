package io.github.baddel73.dots.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.util.IncorrectOperationException;
import io.github.baddel73.dots.language.DotsUtil;
import io.github.baddel73.dots.language.psi.DotsCustomTypeReference;
import io.github.baddel73.dots.language.psi.DotsEnumDefinition;
import io.github.baddel73.dots.language.psi.DotsNamedElement;
import io.github.baddel73.dots.language.psi.DotsStructDefinition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class DotsCustomTypeMixin extends ASTWrapperPsiElement implements DotsCustomTypeReference {

    public DotsCustomTypeMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public PsiReference getReference() {
        return this;
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        List<PsiElement> definitions = new ArrayList<>();
        String name = getElement().getText();

        List<DotsStructDefinition> structs = DotsUtil.findStructs(getElement().getProject());
        for (DotsStructDefinition struct : structs) {
            if (name.equals(struct.getName())) {
                definitions.add(struct);
            }
        }

        List<DotsEnumDefinition> enums = DotsUtil.findEnums(getElement().getProject());
        for (DotsEnumDefinition anEnum : enums) {
            if (name.equals(anEnum.getName())) {
                definitions.add(anEnum);
            }
        }

        List<ResolveResult> results = new ArrayList<>();
        for (PsiElement definition : definitions) {
            results.add(new PsiElementResolveResult(definition));
        }
        return results.toArray(new ResolveResult[0]);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public PsiElement getElement() {
        return this;
    }

    @NotNull
    @Override
    public TextRange getRangeInElement() {
        return new TextRange(0, getElement().getTextLength());
    }

    @NotNull
    @Override
    public String getCanonicalText() {
        return Objects.requireNonNull(getElement().getText());
    }

    @Override
    public PsiElement handleElementRename(@NotNull String newElementName) throws IncorrectOperationException {
        throw new IncorrectOperationException("Rename is not supported yet.");
    }

    @Override
    public PsiElement bindToElement(@NotNull PsiElement element) throws IncorrectOperationException {
        throw new IncorrectOperationException("Bind to element is not supported yet.");
    }

    @Override
    public boolean isReferenceTo(@NotNull PsiElement element) {
        return element instanceof DotsNamedElement && Objects.equals(resolve(), element);
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        return PsiReference.EMPTY_ARRAY;
    }

    @Override
    public boolean isSoft() {
        return false;
    }
}
