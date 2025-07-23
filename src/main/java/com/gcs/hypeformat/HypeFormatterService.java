package com.gcs.hypeformat;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;

@Service(Service.Level.PROJECT)
public final class HypeFormatterService {

    private final Project project;

    public HypeFormatterService(Project project) {
        this.project = project;
    }

    public String formatText(String text) {
        String[] lines = text.split("\n");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < lines.length; i++) {
            String formattedLine = formatLine(lines[i]);
            result.append(formattedLine);
            if (i < lines.length - 1) {
                result.append("\n");
            }
        }

        return result.toString();
    }

    public String formatFile(PsiFile file) {
        return formatText(file.getText());
    }

    private String formatLine(String line) {
        String trimmed = line.trim();
        if (trimmed.isEmpty()) {
            return "";
        }

        int indentLevel = calculateIndentLevel(line);
        String indent = "    ".repeat(indentLevel);

        return indent + formatStatement(trimmed);
    }

    private int calculateIndentLevel(String line) {
        int originalIndent = 0;
        for (char c : line.toCharArray()) {
            if (Character.isWhitespace(c)) {
                originalIndent++;
            } else {
                break;
            }
        }
        return originalIndent / 4;
    }

    private String formatStatement(String statement) {
        return statement
                .replaceAll("\\s*=\\s*", " = ")
                .replaceAll("\\s*\\+\\s*", " + ")
                .replaceAll("\\s*-\\s*", " - ")
                .replaceAll("\\s*\\*\\s*", " * ")
                .replaceAll("\\s*/\\s*", " / ")
                .replaceAll("\\s*,\\s*", ", ")
                .replaceAll("\\s*;\\s*", "; ")
                .replaceAll("\\s*\\(\\s*", "(")
                .replaceAll("\\s*\\)\\s*", ")")
                .replaceAll("\\s*\\{\\s*", " {")
                .replaceAll("\\s*\\}\\s*", "}")
                .replaceAll("\\s+", " ")
                .trim();
    }
}