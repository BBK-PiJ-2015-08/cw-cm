/**
 * @author Jade Dickinson jdicki04
 */
package test;

import code.Meeting;
import code.MeetingImpl;
import code.Contact;
import code.ContactImpl;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class MeetingImplTest {
    private int testMeetingId;
    private Calendar testDate;
    private Set<Contact> invited;
    private Contact contact1;
    private Contact contact2;
    private Meeting testMeeting;

    @Before
    public void setUp() {
        contact1 = new ContactImpl(1, "Yogi Bear", "Smarter than your average");
        contact2 = new ContactImpl(2, "Boo Boo Bear", "Mr Ranger isn't going to like this");
        testDate = new GregorianCalendar(2015, 03, 18);
        testMeetingId = 999;
        invited = new HashSet<>();
        invited.add(contact1);
        invited.add(contact2);
        testMeeting = new MeetingImpl(testMeetingId, testDate, invited);
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
        assertEquals(invited, testMeeting.getContacts());
    }
}
