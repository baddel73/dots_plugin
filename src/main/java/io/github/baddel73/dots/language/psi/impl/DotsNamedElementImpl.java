package io.github.baddel73.dots.language.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import io.github.baddel73.dots.language.psi.DotsNamedElement;
import io.github.baddel73.dots.language.psi.DotsTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class DotsNamedElementImpl extends ASTWrapperPsiElement implements DotsNamedElement {

    public DotsNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public PsiElement getNameIdentifier() {
        ASTNode keyNode = getNode().findChildByType(DotsTypes.IDENTIFIER_TOKEN);
        if (keyNode != null) {
            return keyNode.getPsi();
        } else {
            return null;
        }
    }

    @Override
    public String getName() {
        ASTNode keyNode = getNode().findChildByType(DotsTypes.IDENTIFIER_TOKEN);
        if (keyNode != null) {
            return keyNode.getText();
        } else {
            return null;
        }
    }

    @Override
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        // Full implementation would require an element factory.
        throw new IncorrectOperationException("Rename is not supported yet.");
    }
}
