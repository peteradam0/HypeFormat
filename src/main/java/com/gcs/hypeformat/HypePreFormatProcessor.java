package com.gcs.hypeformat;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class HypePreFormatProcessor {

    public static void formatDocument(@NotNull Project project, @NotNull Document document) {
        PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(document);
        if(psiFile == null)return;

        HypeFormatterService formatterService = project.getService(HypeFormatterService.class);
        HypeFormatterSettings settings = HypeFormatterSettings.getInstance();

        if(!settings.isEnabled()) {
            return;
        }

        String originalText = document.getText();
        String formattedText = formatterService.formatText(originalText);

        document.replaceString(0, document.getTextLength(), formattedText);

        PsiDocumentManager.getInstance(project).commitDocument(document);
    }

    public static void formatRange(@NotNull PsiElement element, @NotNull TextRange range) {
        Project project = element.getProject();
        PsiFile psiFile = element.getContainingFile();

        if(psiFile == null) {
            return;
        }

        HypeFormatterService formatterService = project.getService(HypeFormatterService.class);
        HypeFormatterSettings settings = HypeFormatterSettings.getInstance();

        if(!settings.isEnabled()) {
            return;
        }

        Document document = psiFile.getViewProvider().getDocument();
        if(document == null)return;

        String originalText = document.getText(range);
        String formattedText = formatterService.formatText(originalText);

        document.replaceString(range.getStartOffset(), range.getEndOffset(), formattedText);

        PsiDocumentManager.getInstance(project).commitDocument(document);
    }
}