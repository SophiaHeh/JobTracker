package com.tnite.jobwinner.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GeneralJobTest {
    private Company c1;
    private Person p1;
    private GeneralJob job1, job2;

    @BeforeEach
    void setUp() {
        // instantiate full constructors
        c1 = new Company("NEU", "Education", "NEU.com");
        p1 = new Person("Jane", "Dow", "JD@NEU.edu.com", c1, 111222333);
        LocalDate date1 = LocalDate.of(2025, 1, 31);
        job1 = new GeneralJob("Professor", "Teach OOD", "San Jose", date1, c1, p1, 100000.0, Status.Pending, Type.FULL_TIME);

        // instantiate required constructors
        LocalDate date2 = LocalDate.of(2025, 2, 28);
        job2 = new GeneralJob("Technician", c1, date2);
    }


    @Test
    void testAddNote() {
        // Define the expected list of notes
        List<String> expectedNotes = Arrays.asList(
            "Applied from Linkedin",
            "Required to use Java to instruct",
            "Required to instruct recitation"
        );

        // Add notes to job1
        List<String> notes = new ArrayList<>();
        job1.addNote("Applied from Linkedin");
        job1.addNote("Required to use Java to instruct");
        job1.addNote("Required to instruct recitation");

        // Assert that the notes in job1 match the expected list
        assertEquals(expectedNotes, job1.getNotes());
    }

    @Test
    void testGetNotes() {
        List<String> expectedNotes = Arrays.asList("Happy", "April", "Yeah!");
        assertEquals(expectedNotes, job1.getNotes());
    }

    @Test
    void testResumeVersion() {

    }

    @Test
    void testSetInterviewDate() {
    }

    @Test
    void testGetInterviewDate() {
    }

    @Test
    void testCheckAndMarkGhosted() {
    }

    @Test
    void testDisplayDetailedInfo() {
    }
}