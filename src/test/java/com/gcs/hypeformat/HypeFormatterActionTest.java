package com.gcs.hypeformat;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.testFramework.fixtures.BasePlatformTestCase;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class HypeFormatterActionTest extends BasePlatformTestCase {

    private HypeFormatterAction action;
    
    @Mock
    private AnActionEvent mockEvent;
    
    @Mock
    private Editor mockEditor;
    
    @Mock
    private PsiFile mockPsiFile;
    
    @Mock
    private Document mockDocument;
    
    @Mock
    private Presentation mockPresentation;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.openMocks(this);
        action = new HypeFormatterAction();
    }

    @Test
    public void testGetActionUpdateThread() {
        ActionUpdateThread result = action.getActionUpdateThread();
        assertEquals(ActionUpdateThread.BGT, result);
    }

    @Test
    public void testUpdateWithValidContext() {
        when(mockEvent.getProject()).thenReturn(getProject());
        when(mockEvent.getData(CommonDataKeys.EDITOR)).thenReturn(mockEditor);
        when(mockEvent.getData(CommonDataKeys.PSI_FILE)).thenReturn(mockPsiFile);
        when(mockEvent.getPresentation()).thenReturn(mockPresentation);

        action.update(mockEvent);

        verify(mockPresentation).setEnabled(true);
    }

    @Test
    public void testUpdateWithNullProject() {
        when(mockEvent.getProject()).thenReturn(null);
        when(mockEvent.getData(CommonDataKeys.EDITOR)).thenReturn(mockEditor);
        when(mockEvent.getData(CommonDataKeys.PSI_FILE)).thenReturn(mockPsiFile);
        when(mockEvent.getPresentation()).thenReturn(mockPresentation);

        action.update(mockEvent);

        verify(mockPresentation).setEnabled(false);
    }

    @Test
    public void testUpdateWithNullEditor() {
        when(mockEvent.getProject()).thenReturn(getProject());
        when(mockEvent.getData(CommonDataKeys.EDITOR)).thenReturn(null);
        when(mockEvent.getData(CommonDataKeys.PSI_FILE)).thenReturn(mockPsiFile);
        when(mockEvent.getPresentation()).thenReturn(mockPresentation);

        action.update(mockEvent);

        verify(mockPresentation).setEnabled(false);
    }

    @Test
    public void testUpdateWithNullPsiFile() {
        when(mockEvent.getProject()).thenReturn(getProject());
        when(mockEvent.getData(CommonDataKeys.EDITOR)).thenReturn(mockEditor);
        when(mockEvent.getData(CommonDataKeys.PSI_FILE)).thenReturn(null);
        when(mockEvent.getPresentation()).thenReturn(mockPresentation);

        action.update(mockEvent);

        verify(mockPresentation).setEnabled(false);
    }

    @Test
    public void testActionPerformedWithNullContext() {
        when(mockEvent.getProject()).thenReturn(null);
        when(mockEvent.getData(CommonDataKeys.EDITOR)).thenReturn(null);
        when(mockEvent.getData(CommonDataKeys.PSI_FILE)).thenReturn(null);

        // Should not throw exception
        action.actionPerformed(mockEvent);
    }
}