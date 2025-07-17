package com.gcs.hypeformat;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Service(Service.Level.APP)
@State(name = "HypeFormatterSettings", storages = @Storage("HypeFormatterSettings.xml"))
public final class HypeFormatterSettings implements PersistentStateComponent<HypeFormatterSettings> {
    
    private boolean enabled = true;
    private int indentSize = 4;
    private boolean formatOnSave = false;
    
    public static HypeFormatterSettings getInstance() {
        return ApplicationManager.getApplication().getService(HypeFormatterSettings.class);
    }
    
    @Nullable
    @Override
    public HypeFormatterSettings getState() {
        return this;
    }
    
    @Override
    public void loadState(@NotNull HypeFormatterSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }
    
    public boolean isEnabled() {
        return enabled;
    }
    
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public int getIndentSize() {
        return indentSize;
    }
    
    public void setIndentSize(int indentSize) {
        this.indentSize = indentSize;
    }
    
    public boolean isFormatOnSave() {
        return formatOnSave;
    }
    
    public void setFormatOnSave(boolean formatOnSave) {
        this.formatOnSave = formatOnSave;
    }
}