/**
 * @author Jade Dickinson jdicki04
 */
package test;

import code.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class PastMeetingImplTest {
    private int testMeetingId;
    private Calendar testDate;
    private Set<Contact> attended;
    private Contact contact1;
    private Contact contact2;
    private String testNotes;
    private PastMeeting testMeeting;

    @Before
    public void setUp() {
        contact1 = new ContactImpl(3, "Danger Mouse", "Secret agent");
        contact2 = new ContactImpl(4, "Ernest Penfold", "Kung moggy expert");
        // January 1st 2016
        testDate = new GregorianCalendar(2016, 0, 1);
        testMeetingId = 111;
        attended = new HashSet<>();
        attended.add(contact1);
        attended.add(contact2);
        testNotes = "Discussing top secret matters";
        testMeeting = new PastMeetingImpl(testMeetingId, testDate, attended, "");
    }

    @Test
    public void testGetNotes() {
        testMeeting = new PastMeetingImpl(testMeetingId, testDate, attended, testNotes);
        assertEquals(testNotes, testMeeting.getNotes());
    }

    @Test
    public void testGetNotesWhenNoNotes() {
        assertEquals("", testMeeting.getNotes());
    }

    @Test
    public void testGetId() {
        assertEquals(testMeetingId, testMeeting.getId());
    }

    @Test
    public void testGetDate() {
        assertEquals(testDate, testMeeting.getDate());
    }

    @Test
    public void testGetContacts() {
        assertEquals(attended, testMeeting.getContacts());
    }
}
