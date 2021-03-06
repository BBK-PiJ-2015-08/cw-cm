package test;

import code.Contact;
import code.ContactImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
/**
 * @author Jade Dickinson jdicki04
 */
public class ContactImplTest {
    private int testId;
    private String testName;
    private String testNotes;
    private Contact testContact;

    private String noteAddition;

    @Before
    public void setUp() {
        testId = 1001;
        testName = "Mr Robot";
        testNotes = "Who knows";
        testContact = new ContactImpl(testId, testName, testNotes);
        noteAddition = "Mr Robot is (spoilers)";
    }

    @Test
    public void testGetId() {
        assertEquals(testId,testContact.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetIdWhenZero() {
        testContact = new ContactImpl(0, testName, testNotes);
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

    @Test(expected = IllegalArgumentException.class)
    public void testGetNameWhenEmpty() {
        testContact = new ContactImpl(testId, "", testNotes);
        assertNull(testContact.getName());
    }

    @Test
    public void testGetNotes() {
        assertEquals(testNotes,testContact.getNotes());
    }

    @Test(expected = NullPointerException.class)
    public void testGetNotesWhenNull() {
        testContact = new ContactImpl(testId, testName, null);
        assertNull(testContact.getNotes());
    }

    @Test
    public void testAddNotesNullToNoNotes() {
        testContact = new ContactImpl(testId, testName);
        testContact.addNotes(null);
        assertEquals(testContact.getNotes(), "");
    }

    @Test
    public void testAddNotesEmptyToNoNotes() {
        testContact = new ContactImpl(testId, testName);
        testContact.addNotes("");
        assertEquals(testContact.getNotes(), "");
    }

    @Test
    public void testAddNotesNullToExistingNotes() {
        testContact.addNotes(null);
        assertEquals(testNotes,testContact.getNotes());
    }

    @Test
    public void testAddNotesEmptyToExistingNotes() {
        testContact.addNotes("");
        assertEquals(testNotes,testContact.getNotes());
    }


    @Test
    public void testAddNotesValidToNoNotes() {
        testContact = new ContactImpl(testId, testName);
        testContact.addNotes(noteAddition);
        assertEquals(noteAddition,testContact.getNotes());
    }

    @Test
    public void testAddNotesValidToExistingNotes() {
        testContact.addNotes(noteAddition);
        assertEquals(testNotes+noteAddition,testContact.getNotes());
    }
}
