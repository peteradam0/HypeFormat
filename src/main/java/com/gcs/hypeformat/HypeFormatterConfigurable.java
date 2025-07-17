package com.gcs.hypeformat;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class HypeFormatterConfigurable implements Configurable {

    private JPanel mainPanel;
    private JCheckBox enableFormattingCheckBox;
    private JSpinner indentSizeSpinner;
    private JCheckBox formatOnSaveCheckBox;

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "HypeFormat";
    }


    @Nullable
    @Override
    public JComponent createComponent() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));


        enableFormattingCheckBox = new JCheckBox("Enable HypeFormat");
        enableFormattingCheckBox.setSelected(true);

        JPanel indentPanel = new JPanel();
        indentPanel.add(new JLabel("Indent size:"));
        indentSizeSpinner = new JSpinner(new SpinnerNumberModel(4, 1, 8, 1));
        indentPanel.add(indentSizeSpinner);

        formatOnSaveCheckBox = new JCheckBox("Format on save");
        formatOnSaveCheckBox.setSelected(false);

        mainPanel.add(enableFormattingCheckBox);
        mainPanel.add(indentPanel);
        mainPanel.add(formatOnSaveCheckBox);

        return mainPanel;
    }

    @Override
    public boolean isModified() {
        HypeFormatterSettings settings = HypeFormatterSettings.getInstance();
        return enableFormattingCheckBox.isSelected() != settings.isEnabled() || !indentSizeSpinner.getValue().equals(settings.getIndentSize()) || formatOnSaveCheckBox.isSelected() != settings.isFormatOnSave();
    }

    @Override
    public void apply() throws ConfigurationException {
        HypeFormatterSettings settings = HypeFormatterSettings.getInstance();
        settings.setEnabled(enableFormattingCheckBox.isSelected());
        settings.setIndentSize((Integer) indentSizeSpinner.getValue());
        settings.setFormatOnSave(formatOnSaveCheckBox.isSelected());
    }

    @Override
    public void reset() {
        HypeFormatterSettings settings = HypeFormatterSettings.getInstance();
        enableFormattingCheckBox.setSelected(settings.isEnabled());
        indentSizeSpinner.setValue(settings.getIndentSize());
        formatOnSaveCheckBox.setSelected(settings.isFormatOnSave());
    }
}