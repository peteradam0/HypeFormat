package com.gcs.hypeformat.formatter.language.markup;

public class HtmlFormatter extends AbstractMarkupFormatter {
    
    @Override
    protected boolean canHandle(String statement) {
        return isHtmlTag(statement) || isHtmlContent(statement);
    }
    
    @Override
    protected String formatMarkup(String statement) {
        if (statement.contains("<!--") && statement.contains("-->")) {
            return statement.replaceAll("<!--\\s*(.*?)\\s*-->", "<!-- $1 -->");
        }
        
        String formatted = super.formatMarkup(statement);
        
        return formatted
                .replaceAll("\\s*(<!DOCTYPE\\s+html)\\s*", "$1")
                .replaceAll("<(\\w+)\\s*([^>]*)\\s*>", "<$1$2>")
                .replaceAll("\\s+", " ")
                .trim();
    }
    
    private boolean isHtmlContent(String statement) {
        return statement.toLowerCase().contains("<!doctype") ||
               statement.toLowerCase().contains("<!--") ||
               statement.toLowerCase().contains("-->") ||
               containsHtmlTags(statement);
    }
    
    private boolean containsHtmlTags(String statement) {
        String lowerStatement = statement.toLowerCase();
        return lowerStatement.matches(".*(html|head|body|div|span|p|a|img|table|tr|td|th|ul|ol|li|form|input|button|h[1-6]).*");
    }
}