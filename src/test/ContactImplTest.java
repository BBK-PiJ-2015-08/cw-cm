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
    private int testID;
    private String testName;
    private String testNotes;

    @Before
    public void setUp() {
        testID = 1001;
        testName = "Mr Robot";
        testNotes = "Who knows";
        testContact = new ContactImpl(testID, testName, testNotes);
    }

    @After
    public void tearDown() {
        testContact = null;
    }

    @Test
    public void getIdTest() {
        assertEquals(testID,testContact.getId());
    }

    //@Test

    //@Test

    //@Test
}
