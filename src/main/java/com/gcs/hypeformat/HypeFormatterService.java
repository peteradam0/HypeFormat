package com.gcs.hypeformat;

import com.gcs.hypeformat.formatter.base.AbstractBaseLanguageFormatter;
import com.gcs.hypeformat.formatter.language.AbstractBasicFormatter;
import com.gcs.hypeformat.formatter.language.markup.HtmlFormatter;
import com.gcs.hypeformat.formatter.language.markup.HtmxFormatter;
import com.gcs.hypeformat.formatter.language.markup.JspFormatter;
import com.gcs.hypeformat.formatter.language.scripting.HyperscriptFormatter;
import com.gcs.hypeformat.formatter.language.scripting.JavaScriptFormatter;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;

@Service(Service.Level.PROJECT)
public final class HypeFormatterService {

    private final Project project;
    private final AbstractBaseLanguageFormatter formatterChain;

    public HypeFormatterService(Project project) {
        this.project = project;
        this.formatterChain = buildFormatterChain();
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
        return formatterChain.format(statement);
    }
    
    private AbstractBaseLanguageFormatter buildFormatterChain() {
        AbstractBasicFormatter basicFormatter = new AbstractBasicFormatter();
        HtmlFormatter htmlFormatter = new HtmlFormatter();
        JspFormatter jspFormatter = new JspFormatter();
        JavaScriptFormatter jsFormatter = new JavaScriptFormatter();
        HyperscriptFormatter hyperScriptFormatter = new HyperscriptFormatter();
        HtmxFormatter htmxFormatter = new HtmxFormatter();

        basicFormatter.setNext(htmlFormatter);
        htmlFormatter.setNext(jspFormatter);
        jspFormatter.setNext(jsFormatter);
        jsFormatter.setNext(hyperScriptFormatter);
        hyperScriptFormatter.setNext(htmxFormatter);

        return htmlFormatter;
    }
}