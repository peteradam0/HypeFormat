package com.gcs.hypeformat.formatter.language;

import com.gcs.hypeformat.formatter.base.AbstractBaseLanguageFormatter;

public class AbstractBasicFormatter extends AbstractBaseLanguageFormatter {
    
    @Override
    protected boolean canHandle(String statement) {
        return !statement.contains("<!--") &&
               !statement.contains("-->") &&
               !statement.matches(".*<[^>]+>.*");
    }
    
    @Override
    protected String applyFormatting(String statement) {
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
                .replaceAll("\\b(if|for|while|switch)\\s*\\(", "$1 (")
                .replaceAll("\\s+", " ")
                .trim();
    }
}