package com.wem.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DureeTest {

    @Test
    @DisplayName("Test methode getHeures")
    void getHeures() {
        Duree duree = new Duree(5, 30, 45);
        assertEquals(5, duree.getHeures(), "La valeur de l'heur doit etre 5");
    }

    @Test
    @DisplayName("Test Set heurs")
    void setHeures() {
        Duree d1 = new Duree();
        d1.setHeures(5);
        assertEquals(5, d1.getHeures(), "The heures value should be 5");

        d1.setHeures(0);
        assertEquals(0, d1.getHeures(), "The heures value should be 0");

        assertThrows(IllegalArgumentException.class, () -> d1.setHeures(-1),
                "Setting heures to -1 should throw an IllegalArgumentException");
    }

    @Test
    @DisplayName("Test getter for minutes")
    void getMinutes() {
        Duree duree = new Duree(5, 30, 45);
        assertEquals(30, duree.getMinutes(), "The minutes value should be 30");
    }

    @Test
    @DisplayName("Test setter for minutes with valid and invalid inputs")
    void setMinutes() {
        Duree d1 = new Duree();
        d1.setMinutes(30);
        assertEquals(30, d1.getMinutes(), "The minutes value should be 30");

        d1.setMinutes(0);
        assertEquals(0, d1.getMinutes(), "The minutes value should be 0");

        assertThrows(IllegalArgumentException.class, () -> d1.setMinutes(60),
                "Setting minutes to 60 should throw an IllegalArgumentException");
    }

    @Test
    @DisplayName("Test getter for secondes")
    void getSecondes() {
        Duree duree = new Duree(5, 30, 45);
        assertEquals(45, duree.getSecondes(), "The secondes value should be 45");
    }

    @Test
    @DisplayName("Test setter for secondes with valid and invalid inputs")
    void setSecondes() {
        Duree d1 = new Duree();
        d1.setSecondes(45);
        assertEquals(45, d1.getSecondes(), "The secondes value should be 45");

        d1.setSecondes(0);
        assertEquals(0, d1.getSecondes(), "The secondes value should be 0");

        assertThrows(IllegalArgumentException.class, () -> d1.setSecondes(60),
                "Setting secondes to 60 should throw an IllegalArgumentException");
    }

    @Test
    @DisplayName("Test formatting of duration as hh:mm:ss")
    void formatDuree() {
        Duree duree1 = new Duree(1, 2, 3);
        assertEquals("01:02:03", duree1.formatDuree(), "The formatted duration should be 01:02:03");

        Duree duree2 = new Duree(0, 0, 0);
        assertEquals("00:00:00", duree2.formatDuree(), "The formatted duration should be 00:00:00");

        Duree duree3 = new Duree(23, 59, 59);
        assertEquals("23:59:59", duree3.formatDuree(), "The formatted duration should be 23:59:59");
    }

    @Test
    @DisplayName("Test displaying of duration (integration with System.out)")
    void afficherDuree() {
        Duree duree = new Duree(12, 34, 56);


        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            duree.afficherDuree();
            assertEquals("12:34:56\n", outContent.toString(), "The output of afficherDuree() should match the formatted duration");
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    @DisplayName("Test comparing durations")
    void comparer() {
        Duree d1 = new Duree(1, 0, 0);
        Duree d2 = new Duree(2, 0, 0);

        assertEquals(0, d1.comparer(new Duree(1, 0, 0)), "Equal durations should return 0");
        assertEquals(-1, d1.comparer(d2), "Smaller duration should return -1");
        assertEquals(1, d2.comparer(d1), "Greater duration should return 1");
    }

    @Test
    @DisplayName("Test adding one second to duration")
    void nextSeconde() {
        Duree duree = new Duree(1, 59, 59);
        duree.nextSeconde();
        assertEquals("02:00:00", duree.formatDuree(), "After adding one second, the duration should roll over to 02:00:00");
    }

    @Test
    @DisplayName("Test adding one minute to duration")
    void nextMinute() {
        Duree duree = new Duree(1, 59, 59);
        duree.nextMinute();
        assertEquals("02:00:59", duree.formatDuree(), "After adding one minute, the duration should roll over to 02:00:59");
    }

    @Test
    @DisplayName("Test equality of durations")
    void isEquals() {
        Duree d1 = new Duree(1, 0, 0);
        Duree d2 = new Duree(1, 0, 0);
        Duree d3 = new Duree(2, 0, 0);

        assertTrue(d1.isEquals(d2), "Durations with equal values should return true");
        assertFalse(d1.isEquals(d3), "Durations with different values should return false");
    }
}
