package org.example.service;

import org.example.userAction.UserAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuditServiceTest {

    private AuditService auditService;

    @BeforeEach
    void setUp() {
        auditService = new AuditService();
    }

    @Test
    void logAction() {
        String user = "user1";
        String action = "action1";
        auditService.logAction(user, action);

        List<UserAction> actions = auditService.getActions();
        assertEquals(1, actions.size());
        assertEquals(user, actions.get(0).getUser());
        assertEquals(action, actions.get(0).getAction());
    }

    @Test
    void getActions() {
        auditService.logAction("user1", "action1");
        auditService.logAction("user2", "action2");

        List<UserAction> actions = auditService.getActions();
        assertEquals(2, actions.size());
    }

    @Test
    void filterActions() {
        LocalDateTime now = LocalDateTime.now();
        auditService.logAction("user1", "action1");
        auditService.logAction("user2", "action2");

        List<UserAction> filteredActions = auditService.filterActions("user1", null, now.minusDays(1), now.plusDays(1));
        assertEquals(1, filteredActions.size());
        assertEquals("user1", filteredActions.get(0).getUser());

        filteredActions = auditService.filterActions(null, "action2", now.minusDays(1), now.plusDays(1));
        assertEquals(1, filteredActions.size());
        assertEquals("action2", filteredActions.get(0).getAction());

        filteredActions = auditService.filterActions(null, null, now.minusDays(1), now.plusDays(1));
        assertEquals(2, filteredActions.size());
    }

    @Test
    void exportLog() throws IOException {
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
