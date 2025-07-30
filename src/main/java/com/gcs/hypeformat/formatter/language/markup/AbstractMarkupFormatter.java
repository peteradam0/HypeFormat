package com.gcs.hypeformat.formatter.language.markup;

import com.gcs.hypeformat.formatter.base.AbstractBaseLanguageFormatter;

public abstract class AbstractMarkupFormatter extends AbstractBaseLanguageFormatter {
    
    @Override
    protected String applyFormatting(String statement) {
        if (!canHandle(statement)) {
            return statement;
        }
        
        return formatMarkup(statement);
    }
    
    protected String formatMarkup(String statement) {
        String result = statement;
        
        if (result.contains("<!--") && result.contains("-->")) {
            result = result.replaceAll("<!--\\s+", "<!-- ");
            result = result.replaceAll("\\s+-->", " -->");
            result = result.replaceAll("<!--\\s*(.*?)\\s*-->", "<!-- $1 -->");
        } else {
            result = result.replaceAll("\\s*<\\s*", "<");
            result = result.replaceAll("\\s*>\\s*", ">");
            result = result.replaceAll("<\\s*/\\s*", "</");
            
            result = result.replaceAll("\\s*/>\\s*", " />");
            
            result = result.replaceAll("\\s*=\\s*", "=");
            result = result.replaceAll("=\"\\s*", "=\"");
            result = result.replaceAll("\\s*\"", "\"");
            
            result = result.replaceAll("<(\\w+)([a-zA-Z-])", "<$1 $2");
            result = result.replaceAll("\"\\s*([a-zA-Z-]+)\\s*=", "\" $1=");
            
            result = result.replaceAll("\\s+", " ");
        }
        
        return result.trim();
    }
    
    protected boolean isHtmlTag(String statement) {
        return statement.matches(".*<[^>]+>.*");
    }
    
    protected boolean isClosingTag(String statement) {
        return statement.matches(".*</[^>]+>.*");
    }
    
    protected boolean isSelfClosingTag(String statement) {
        return statement.matches(".*<[^>]+/>.*");
    }
}