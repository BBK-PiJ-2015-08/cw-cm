/**
 * @author Jade Dickinson jdicki04
 */
package test;

import code.Contact;
import code.ContactImpl;
import code.Meeting;
import code.MeetingImpl;
import code.FutureMeeting;
import code.FutureMeetingImpl;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class FutureMeetingImplTest {
    private int testMeetingId;
    private Calendar testDate;
    private Set<Contact> invited;
    private Contact contact1;
    private Contact contact2;
    private FutureMeeting testMeeting;

    @Before
    public void setUp() {
        contact1 = new ContactImpl(5, "Rick", "Scientist");
        contact2 = new ContactImpl(6, "Morty", "His grandpa Rick sent him");
        testDate = new GregorianCalendar(2016, 11, 25);
        testMeetingId = 42;
        invited = new HashSet<>();
        invited.add(contact1);
        invited.add(contact2);
        testMeeting = new FutureMeetingImpl(testMeetingId, testDate, invited);

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
