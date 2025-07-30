package com.gcs.hypeformat.formatter.language.scripting;

public class JavaScriptFormatter extends AbstractScriptFormatter {
    
    @Override
    protected boolean canHandle(String statement) {
        return isJavaScriptContent(statement);
    }
    
    @Override
    protected String formatScript(String statement) {
        String formatted = super.formatScript(statement);
        
        return formatted
                .replaceAll("\\b(function)\\s*\\(", "$1(")
                .replaceAll("\\b(async|await)\\s+", "$1 ")
                .replaceAll("\\b(return|throw|typeof|instanceof)\\s+", "$1 ")
                .replaceAll("\\b(try|catch|finally)\\s*\\{", "$1 {")
                .replaceAll("\\b(class|extends)\\s+", "$1 ")
                .replaceAll("=>\\s*", " => ")
                .replaceAll("\\.\\.\\.", "...")
                .replaceAll("\\s+", " ")
                .trim();
    }
    
    private boolean isJavaScriptContent(String statement) {
        return containsJavaScriptKeywords(statement) ||
               containsJavaScriptOperators(statement) ||
               isScriptBlock(statement) ||
               isInlineScript(statement);
    }
    
    private boolean containsJavaScriptKeywords(String statement) {
        String lowerStatement = statement.toLowerCase();
        return lowerStatement.matches(".*(function|var|let|const|if|else|for|while|do|switch|case|default|break|continue|return|try|catch|finally|throw|async|await|class|extends|import|export|typeof|instanceof).*");
    }
    
    private boolean containsJavaScriptOperators(String statement) {
        return statement.contains("=>") ||
               statement.contains("===") ||
               statement.contains("!==") ||
               statement.contains("...") ||
               statement.matches(".*\\b(true|false|null|undefined)\\b.*");
    }
}