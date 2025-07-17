package com.gcs.hypeformat;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleManager;
import org.jetbrains.annotations.NotNull;

public class HypePreFormatProcessor {
    
    public static void formatDocument(@NotNull Project project, @NotNull Document document) {
        PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(document);
        if (psiFile == null) return;
        
        HypeFormatterService formatterService = project.getService(HypeFormatterService.class);
        HypeFormatterSettings settings = HypeFormatterSettings.getInstance();
        
        // Only format if HypeFormat is enabled in settings
        if (!settings.isEnabled()) {
            return;
        }
        
        // Get the text to format
        String originalText = document.getText();
        String formattedText = formatterService.formatText(originalText);
        
        // Replace the document text
        document.replaceString(0, document.getTextLength(), formattedText);
        
        // Commit the document changes
        PsiDocumentManager.getInstance(project).commitDocument(document);
    }
    
    public static void formatRange(@NotNull PsiElement element, @NotNull TextRange range) {
        Project project = element.getProject();
        PsiFile psiFile = element.getContainingFile();
        
        if (project == null || psiFile == null) {
            return;
        }
        
        HypeFormatterService formatterService = project.getService(HypeFormatterService.class);
        HypeFormatterSettings settings = HypeFormatterSettings.getInstance();
        
        // Only format if HypeFormat is enabled in settings
        if (!settings.isEnabled()) {
            return;
        }
        
        Document document = psiFile.getViewProvider().getDocument();
        if (document == null) return;
        
        // Get the text in the specified range
        String originalText = document.getText(range);
        String formattedText = formatterService.formatText(originalText);
        
        // Replace the text in the document
        document.replaceString(range.getStartOffset(), range.getEndOffset(), formattedText);
        
        // Commit the document changes
        PsiDocumentManager.getInstance(project).commitDocument(document);
    }
}