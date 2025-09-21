package io.github.baddel73.dots.language;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import io.github.baddel73.dots.file.DotsFileType;
import io.github.baddel73.dots.language.psi.DotsEnumDefinition;
import io.github.baddel73.dots.language.psi.DotsStructDefinition;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DotsUtil {

    public static List<DotsStructDefinition> findStructs(Project project) {
        List<DotsStructDefinition> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(DotsFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            DotsFile dotsFile = (DotsFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (dotsFile != null) {
                DotsStructDefinition[] structs = PsiTreeUtil.getChildrenOfType(dotsFile, DotsStructDefinition.class);
                if (structs != null) {
                    Collections.addAll(result, structs);
                }
            }
        }
        return result;
    }

    public static List<DotsEnumDefinition> findEnums(Project project) {
        List<DotsEnumDefinition> result = new ArrayList<>();
        Collection<VirtualFile> virtualFiles =
                FileTypeIndex.getFiles(DotsFileType.INSTANCE, GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            DotsFile dotsFile = (DotsFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (dotsFile != null) {
                DotsEnumDefinition[] enums = PsiTreeUtil.getChildrenOfType(dotsFile, DotsEnumDefinition.class);
                if (enums != null) {
                    Collections.addAll(result, enums);
                }
            }
        }
        return result;
    }
}
