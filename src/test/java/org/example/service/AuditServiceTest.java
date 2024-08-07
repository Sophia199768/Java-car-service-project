package org.example.service;

import org.example.core.userAction.UserAction;
import org.example.service.service.AuditService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        auditService.logAction(user, action);

        List<UserAction> actions = auditService.getActions();
        assertEquals(1, actions.size());
        assertEquals(user, actions.get(0).getUser());
        assertEquals(action, actions.get(0).getAction());
    }

    @Test
    @DisplayName("getActions should return all logged actions")
    void getActions_shouldReturnAllLoggedActions() {
        auditService.logAction("user1", "action1");
        auditService.logAction("user2", "action2");

        List<UserAction> actions = auditService.getActions();
        assertEquals(2, actions.size());
    }

    @Test
    @DisplayName("exportLog should export the log to a file")
    void exportLog_shouldExportLogToFile() throws IOException {
        String filename = "test_audit_log.txt";
        auditService.logAction("user1", "action1");
        auditService.logAction("user2", "action2");

        auditService.exportLog(filename);

        File file = new File(filename);
        assertTrue(file.exists());

        List<String> lines = Files.readAllLines(file.toPath());
        assertEquals(2, lines.size());
        assertTrue(lines.get(0).contains("user1"));
        assertTrue(lines.get(1).contains("user2"));

        Files.deleteIfExists(file.toPath());
    }
}
