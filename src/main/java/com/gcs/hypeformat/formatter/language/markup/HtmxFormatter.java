package com.gcs.hypeformat.formatter.language.markup;

public class HtmxFormatter extends AbstractMarkupFormatter {
    
    @Override
    protected boolean canHandle(String statement) {
        return isHtmxContent(statement) || isHtmlTag(statement);
    }
    
    @Override
    protected String formatMarkup(String statement) {
        String formatted = super.formatMarkup(statement);
        
        return formatted
                .replaceAll("hx-([a-z-]+)\\s*=\\s*", "hx-$1=")
                .replaceAll("hx-(get|post|put|patch|delete)\\s*=\\s*\"\\s*", "hx-$1=\"")
                .replaceAll("\\s*\"\\s*(hx-[a-z-]+)", "\" $1")
                .replaceAll("hx-target\\s*=\\s*\"\\s*", "hx-target=\"")
                .replaceAll("hx-swap\\s*=\\s*\"\\s*", "hx-swap=\"")
                .replaceAll("hx-trigger\\s*=\\s*\"\\s*", "hx-trigger=\"")
                .replaceAll("hx-indicator\\s*=\\s*\"\\s*", "hx-indicator=\"")
                .replaceAll("hx-confirm\\s*=\\s*\"\\s*", "hx-confirm=\"")
                .replaceAll("hx-include\\s*=\\s*\"\\s*", "hx-include=\"")
                .replaceAll("hx-params\\s*=\\s*\"\\s*", "hx-params=\"")
                .replaceAll("hx-headers\\s*=\\s*\"\\s*", "hx-headers=\"")
                .replaceAll("\\s+", " ")
                .trim();
    }
    
    private boolean isHtmxContent(String statement) {
        return containsHtmxAttributes(statement) ||
               containsHtmxDirectives(statement);
    }
    
    private boolean containsHtmxAttributes(String statement) {
        return statement.matches(".*hx-[a-z-]+\\s*=.*") ||
               statement.contains("hx-boost") ||
               statement.contains("hx-push-url") ||
               statement.contains("hx-replace-url") ||
               statement.contains("hx-history");
    }
    
    private boolean containsHtmxDirectives(String statement) {
        return statement.contains("hx-get") ||
               statement.contains("hx-post") ||
               statement.contains("hx-put") ||
               statement.contains("hx-patch") ||
               statement.contains("hx-delete") ||
               statement.contains("hx-target") ||
               statement.contains("hx-swap") ||
               statement.contains("hx-trigger") ||
               statement.contains("hx-indicator") ||
               statement.contains("hx-confirm") ||
               statement.contains("hx-include") ||
               statement.contains("hx-params") ||
               statement.contains("hx-headers");
    }
}