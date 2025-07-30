package com.gcs.hypeformat.formatter.language.scripting;

public class HyperscriptFormatter extends AbstractScriptFormatter {
    
    @Override
    protected boolean canHandle(String statement) {
        return isHyperScriptContent(statement);
    }
    
    @Override
    protected String formatScript(String statement) {
        String formatted = super.formatScript(statement);
        
        return formatted
                .replaceAll("\\b(on)\\s+", "$1 ")
                .replaceAll("\\b(click|load|submit|change|keyup|keydown|focus|blur)\\s*", "$1 ")
                .replaceAll("\\b(add|remove|toggle)\\s+", "$1 ")
                .replaceAll("\\b(from|to|me|it|my|its)\\s*", "$1 ")
                .replaceAll("\\b(then|wait|trigger|send|fetch|put|get|post|delete)\\s+", "$1 ")
                .replaceAll("\\b(class|classes|attribute|style)\\s*", "$1 ")
                .replaceAll("\\b(closest|first|last|next|previous)\\s+", "$1 ")
                .replaceAll("\\b(with|using|as|called)\\s+", "$1 ")
                .replaceAll("\\s+", " ")
                .trim();
    }
    
    private boolean isHyperScriptContent(String statement) {
        return containsHyperScriptKeywords(statement) ||
               containsHyperScriptAttributes(statement) ||
               isHyperScriptBlock(statement);
    }
    
    private boolean containsHyperScriptKeywords(String statement) {
        String lowerStatement = statement.toLowerCase();
        return lowerStatement.matches(".*(on\\s+(click|load|submit|change|keyup|keydown|focus|blur)|add|remove|toggle|from|to|me|it|my|its|then|wait|trigger|send|fetch|put|get|post|delete|closest|first|last|next|previous|with|using|as|called).*");
    }
    
    private boolean containsHyperScriptAttributes(String statement) {
        return statement.matches(".*_\\s*=\\s*[\"'].*") ||
               statement.contains("hx-") ||
               statement.contains("_hyperscript") ||
               statement.contains("script type=\"text/hyperscript\"");
    }
    
    private boolean isHyperScriptBlock(String statement) {
        return statement.contains("<script type=\"text/hyperscript\"") ||
               statement.contains("</script>") && statement.toLowerCase().contains("hyperscript");
    }
}