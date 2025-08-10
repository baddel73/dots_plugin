package io.github.baddel73.dots.language;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import io.github.baddel73.dots.language.DotsParser;
import io.github.baddel73.dots.file.DotsFileType;
import io.github.baddel73.dots.language.psi.DotsTypes;
import org.jetbrains.annotations.NotNull;

public class DotsParserDefinition implements ParserDefinition {

    public static final IFileElementType FILE = new IFileElementType(DotsLanguage.INSTANCE);

    // Add a static TokenSet for comments
    public static final TokenSet COMMENTS = TokenSet.create(DotsTypes.LINE_COMMENT, DotsTypes.BLOCK_COMMENT);

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new DotsLexerAdapter();
    }

    @Override
    public @NotNull TokenSet getWhitespaceTokens() {
        return TokenSet.create(TokenType.WHITE_SPACE);
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        // Return the new TokenSet
        return COMMENTS;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    @Override
    public PsiParser createParser(final Project project) {
        return new DotsParser();
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    @NotNull
    @Override
    public PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new DotsFile(viewProvider);
    }

    // Add the missing createElement method
    @NotNull
    @Override
    public PsiElement createElement(ASTNode node) {
        return DotsTypes.Factory.createElement(node);
    }

    @NotNull
    @Override
    public SpaceRequirements spaceExistenceTypeBetweenTokens(@NotNull ASTNode left, @NotNull ASTNode right) {
        return SpaceRequirements.MAY;
    }
}
