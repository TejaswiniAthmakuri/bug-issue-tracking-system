
package com.bugtracker.bug_tracker;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BugControllerTest {

    @Mock
    private BugRepository bugRepository;

    @InjectMocks
    private BugController bugController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBugs() {

        bug bug1 = new bug();
        bug1.setTitle("Login button not working");

        bug bug2 = new bug();
        bug2.setTitle("Search button not loading");

        when(bugRepository.findAll())
                .thenReturn(Arrays.asList(bug1, bug2));

        var result = bugController.getAllBugs();

        assertEquals(2, result.size());
        verify(bugRepository, times(1)).findAll();
    }

    @Test
    void testCreateBug() {

        bug newBug = new bug();
        newBug.setTitle("Login issue");
        newBug.setDescription("Login page not responding");
        newBug.setStatus("OPEN");
        newBug.setPriority("HIGH");

        when(bugRepository.save(newBug)).thenReturn(newBug);

        bug result = bugController.createBug(newBug);

        assertNotNull(result);
        assertEquals("Login issue", result.getTitle());
        verify(bugRepository, times(1)).save(newBug);
    }

    @Test
    void testGetBugById() {

        bug existingBug = new bug();
        existingBug.setTitle("Login issue");

        when(bugRepository.findById(1L))
                .thenReturn(Optional.of(existingBug));

        bug result = bugController.getBugById(1L);

        assertEquals("Login issue", result.getTitle());
    }

    @Test
    void testUpdateBug() {

        bug existingBug = new bug();
        existingBug.setTitle("Old title");

        bug updatedBug = new bug();
        updatedBug.setTitle("New title");
        updatedBug.setDescription("Updated description");
        updatedBug.setStatus("IN_PROGRESS");
        updatedBug.setPriority("MEDIUM");

        when(bugRepository.findById(1L))
                .thenReturn(Optional.of(existingBug));

        when(bugRepository.save(existingBug))
                .thenReturn(existingBug);

        bug result = bugController.updateBug(1L, updatedBug);

        assertEquals("New title", result.getTitle());
        assertEquals("Updated description", result.getDescription());
        assertEquals("IN_PROGRESS", result.getStatus());
        assertEquals("MEDIUM", result.getPriority());
    }

    @Test
    void testDeleteBug() {

        bug existingBug = new bug();

        when(bugRepository.findById(1L))
                .thenReturn(Optional.of(existingBug));

        String result = bugController.deleteBug(1L);

        assertEquals("Bug deleted successfully", result);
        verify(bugRepository, times(1)).delete(existingBug);
    }
    

@Test
void testGetBugByIdNotFound() {

    when(bugRepository.findById(99L))
            .thenReturn(Optional.empty());

    RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> bugController.getBugById(99L)
    );

    assertEquals("Bug not found with id: 99",
            exception.getMessage());
}

@Test
void testUpdateBugNotFound() {

    bug updatedBug = new bug();
    updatedBug.setTitle("Updated title");

    when(bugRepository.findById(99L))
            .thenReturn(Optional.empty());

    RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> bugController.updateBug(99L, updatedBug)
    );

    assertEquals("Bug not found with id: 99",
            exception.getMessage());
}

@Test
void testDeleteBugNotFound() {

    when(bugRepository.findById(99L))
            .thenReturn(Optional.empty());

    RuntimeException exception = assertThrows(
            RuntimeException.class,
            () -> bugController.deleteBug(99L)
    );

    assertEquals("Bug not found with id: 99",
            exception.getMessage());
}

}
