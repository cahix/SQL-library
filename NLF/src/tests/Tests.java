package tests;

import data.DatabaseHandler;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Tests {

    @Test
    public void runTests() {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        assertTrue(databaseHandler.isValidUserLogin("admin", "admin"));
        assertFalse(databaseHandler.isValidUserLogin("noadmin", "noadmin"));
        assertFalse(databaseHandler.isValidUserLogin(null, null));
        assertFalse(databaseHandler.isValidUserLogin("admin", "admin"));
        assertTrue(databaseHandler.isValidBook("Testname", "Testautor", "1995", "192-555-1"));
        assertFalse(databaseHandler.isValidBook("Testname", "Testautor", "noyear", "192-555-1"));
        assertFalse(databaseHandler.isValidBook(null, "Testautor", "1885", "192-555-1"));
        assertFalse(databaseHandler.isValidBook("Testname", "Testautor", "1995", "noisbn"));
    }
}