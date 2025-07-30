package com.gcs.hypeformat.formatter.language.markup;

public class JspFormatter extends AbstractMarkupFormatter {
    
    @Override
    protected boolean canHandle(String statement) {
        return isJspContent(statement) || isHtmlTag(statement);
    }
    
    @Override
    protected String formatMarkup(String statement) {
        String formatted = super.formatMarkup(statement);
        
        return formatted
                .replaceAll("<%\\s*", "<%")
                .replaceAll("\\s*%>", "%>")
                .replaceAll("<%=\\s*", "<%=")
                .replaceAll("<%@\\s*", "<%@")
                .replaceAll("<%!\\s*", "<%!")
                .replaceAll("\\s*(page|taglib|include)\\s*", " $1 ")
                .replaceAll("import\\s*=\\s*", "import=")
                .replaceAll("\\s+", " ")
                .trim();
    }
    
    private boolean isJspContent(String statement) {
        return statement.contains("<%") || 
               statement.contains("%>") ||
               statement.matches(".*<%[@!=]?.*") ||
               containsJspDirectives(statement);
    }
    
    private boolean containsJspDirectives(String statement) {
        String lowerStatement = statement.toLowerCase();
        return lowerStatement.contains("page") ||
               lowerStatement.contains("taglib") ||
               lowerStatement.contains("include") ||
               lowerStatement.contains("import");
    }
}