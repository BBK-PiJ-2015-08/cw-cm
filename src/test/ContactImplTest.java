/**
 * Plan:
 * 1. getId
 * 2. getName
 * 3. getNotes
 * 4. addNotes
 */

/**
 * @author Jade Dickinson jdicki04
 */
package test;

import code.Contact;
import code.ContactImpl;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactImplTest {
    private int testId;
    private String testName;
    private String testNotes;
    private Contact testContact;

    @Before
    public void setUp() {
        testId = 1001;
        testName = "Mr Robot";
        testNotes = "Who knows";
        testContact = new ContactImpl(testId, testName, testNotes);
    }

    @Test
    public void testGetId() {
        assertEquals(testId,testContact.getId());
    }

    @Test
    public void testGetName() {
        assertEquals(testName,testContact.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testGetNameWhenNull() {
        testContact = new ContactImpl(testId, null, testNotes);
        assertNull(testContact.getName());
    }

    @Test
    public void testGetNotes() {
        assertEquals(testNotes,testContact.getNotes());
    }

    @Test(expected = NullPointerException.class)
    public void testNotesNull
/**
    @Test
    public void testAddNotesIfNoNotesAndAddedNotesAreEmpty() {

    }

    @Test
    public void testAddNotesIfNoNotesAndThereAreAddedNotes() {

    }

    @Test
    public void testAddNotesIfNotesExistAndAddedNotesAreEmpty() {

    }

    @Test
    public void testAddNotesIfNotesExistAndThereAreAddedNotes() {

    }
    */
}
