package com.gcs.hypeformat.formatter.base;

public abstract class AbstractBaseLanguageFormatter {
    
    protected AbstractBaseLanguageFormatter nextFormatter;
    
    public void setNext(AbstractBaseLanguageFormatter nextFormatter) {
        this.nextFormatter = nextFormatter;
    }
    
    public String format(String statement) {
        if (canHandle(statement)) {
            String formattedStatement = applyFormatting(statement);
            
            if (nextFormatter != null) {
                return nextFormatter.format(formattedStatement);
            }
            
            return formattedStatement;
        } else if (nextFormatter != null) {
            return nextFormatter.format(statement);
        }
        
        return statement;
    }
    
    protected abstract String applyFormatting(String statement);
    
    protected abstract boolean canHandle(String statement);
}