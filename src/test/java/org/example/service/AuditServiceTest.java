package org.example.service;

import org.example.aspects.userAction.UserAction;
import org.example.aspects.auditService.AuditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuditServiceTest {

    private AuditService auditService;

    @BeforeEach
    void setUp() {
        auditService = new AuditService();
    }

    @Test
    @DisplayName("logAction should log the user action")
    void logAction_shouldLogUserAction() {
        String user = "user1";
        String action = "action1";
        auditService.logAction(action);

        List<UserAction> actions = auditService.getActions();
        assertEquals(1, actions.size());

    }

    @Test
    @DisplayName("getActions should return all logged actions")
    void getActions_shouldReturnAllLoggedActions() {
        auditService.logAction("action1");
        auditService.logAction( "action2");

        List<UserAction> actions = auditService.getActions();
        assertEquals(2, actions.size());
    }

    @Test
    @DisplayName("exportLog should export the log to a file")
    void exportLog_shouldExportLogToFile() throws IOException {
        String filename = "test_audit_log.txt";
        auditService.logAction("action1");
        auditService.logAction("action2");

        auditService.exportLog(filename);

        File file = new File(filename);
        assertTrue(file.exists());

        List<String> lines = Files.readAllLines(file.toPath());
        assertEquals(2, lines.size());


        Files.deleteIfExists(file.toPath());
    }
}
