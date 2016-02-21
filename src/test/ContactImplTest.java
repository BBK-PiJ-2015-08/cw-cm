package test;

/**
 * Plan:
 * 1. getId
 * 2. getName
 * 3. getNotes
 * 4. addNotes
 */

import org.junit.*;
import static org.junit.Assert.*;

public class ContactImplTest {
    private Contact testContact;
    private int testId;
    private String testName;
    private String testNotes;

    @Before
    public void setUp() {
        testId = 1001;
        testName = "Mr Robot";
        testNotes = "Who knows";
        testContact = new ContactImpl(testId, testName, testNotes);
    }

    @After
    public void tearDown() {
        testContact = null;
    }

    @Test
    public void testGetId() {
        assertEquals(testId,testContact.getId());
    }

    @Test
    public void testGetName() {
        assertEquals(testName,testContact.getName());
    }

    //@Test

    //@Test
}
