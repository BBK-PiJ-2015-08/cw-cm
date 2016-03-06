/**
 * Plan for order of test creation:
 * 1. Create a new contact: (AddNewContact)
 * 2. Construct and return sets of contacts: (getContacts(int... ids) and getContacts(String name))
 * 3. Create new meeting in future (addFutureMeeting)
 * 4. Create new meeting in past (addNewPastMeeting)
 * 5. Add notes to a meeting (addMeetingNotes)
 * 6. Get and return individual meetings (getPastMeeting), (getFutureMeeting) & (getMeeting)
 * 7. Construct and return lists of meetings (getFutureMeetingList), (getMeetingListOn) & (getPastMeetingListFor)
 * 8. Save all data (flush)
 * 13 methods to test in total
 */

/**
 * @author Jade Dickinson jdicki04
 */
package test;

import code.Contact;
import code.ContactImpl;
import code.ContactManager;
import code.ContactManagerImpl;
import code.FutureMeeting;
import code.FutureMeetingImpl;
import code.Meeting;
import code.MeetingImpl;
import code.PastMeeting;
import code.PastMeetingImpl;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactManagerImplTest {

    //fields
    private final Calendar currentDate = new GregorianCalendar(2016, 2, 6, 11, 45, 10);
    private final Calendar futureDate = new GregorianCalendar(2017, 10, 1, 10, 30, 30);
    private final Calendar farFutureDate = new GregorianCalendar(2020, 1, 5, 10, 30, 20);
    private final Calendar pastDate = new GregorianCalendar(2010, 5, 4, 9, 20, 1);
    private final Calendar distantPastDate = new GregorianCalendar(2002, 1, 1, 13, 10, 45);

    @Before
    public void setUp() {

    }

    @Test
    public void testAddFutureMeeting() {

    }

    @Test
    public void testGetPastMeeting() {

    }

    @Test
    public void testGetFutureMeeting() {

    }

    @Test
    public void testGetMeeting() {

    }

    @Test
    public void testGetFutureMeetingList() {

    }

    @Test
    public void testGetMeetingListOn() {

    }

    @Test
    public void testGetPastMeetingListFor() {

    }

    @Test
    public void testAddNewPastMeeting() {

    }

    @Test
    public void testAddMeetingNotes() {

    }

    @Test
    public void testAddNewContact() {

    }

    @Test
    public void testGetContacts() {

    }

    @Test
    public void testGetContacts1() {

    }

    @Test
    public void testFlush() {

    }
}
