package com.gcs.hypeformat.formatter.language.scripting;

import com.gcs.hypeformat.formatter.base.AbstractBaseLanguageFormatter;

public abstract class AbstractScriptFormatter extends AbstractBaseLanguageFormatter {
    
    @Override
    protected String applyFormatting(String statement) {
        if (!canHandle(statement)) {
            return statement;
        }
        
        return formatScript(statement);
    }
    
    protected String formatScript(String statement) {
        return statement
                .replaceAll("\\s*=\\s*", " = ")
                .replaceAll("\\s*\\+\\s*", " + ")
                .replaceAll("\\s*-\\s*", " - ")
                .replaceAll("\\s*\\*\\s*", " * ")
                .replaceAll("\\s*/\\s*", " / ")
                .replaceAll("\\s*%\\s*", " % ")
                .replaceAll("\\s*===\\s*", " === ")
                .replaceAll("\\s*!==\\s*", " !== ")
                .replaceAll("\\s*==\\s*", " == ")
                .replaceAll("\\s*!=\\s*", " != ")
                .replaceAll("\\s*<=\\s*", " <= ")
                .replaceAll("\\s*>=\\s*", " >= ")
                .replaceAll("\\s*<\\s*", " < ")
                .replaceAll("\\s*>\\s*", " > ")
                .replaceAll("\\s*&&\\s*", " && ")
                .replaceAll("\\s*\\|\\|\\s*", " || ")
                .replaceAll("\\s*,\\s*", ", ")
                .replaceAll("\\s*;\\s*", "; ")
                .replaceAll("\\s*\\(\\s*", "(")
                .replaceAll("\\s*\\)\\s*", ")")
                .replaceAll("\\s*\\{\\s*", " {")
                .replaceAll("\\s*\\}\\s*", "}")
                .replaceAll("\\s*\\[\\s*", "[")
                .replaceAll("\\s*\\]\\s*", "]")
                .replaceAll("\\b(if|for|while|switch|function|var|let|const)\\s*\\(", "$1 (")
                .replaceAll("\\s+", " ")
                .trim();
    }
    
    protected boolean isScriptBlock(String statement) {
        return statement.contains("<script") || statement.contains("</script>");
    }
    
    protected boolean isInlineScript(String statement) {
        return statement.matches(".*on\\w+\\s*=.*");
    }
}