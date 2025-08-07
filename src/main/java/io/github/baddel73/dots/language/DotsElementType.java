package io.github.baddel73.dots.language;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class DotsElementType extends IElementType {
    public DotsElementType(@NotNull @NonNls String debugName) {
        super(debugName, DotsLanguage.INSTANCE);
    }
}
