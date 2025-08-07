package io.github.baddel73.dots.language;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class DotsTokenType extends IElementType {
    public DotsTokenType(@NotNull @NonNls String debugName) {
        super(debugName, DotsLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "DotsTokenType." + super.toString();
    }
}
