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
    //private final Calendar currentDate = new GregorianCalendar(2016, 2, 6, 11, 45, 10);
    private static final String FILENAME = "contacts.txt";
    //private int meetingId;
    //private int contactId;
    private Calendar currentDate;
    private List<Meeting> allMeetings;
    private Set<Contact> allContacts;
    private final Calendar futureDate = new GregorianCalendar(2017, 10, 1, 10, 30, 30);
    private final Calendar farFutureDate = new GregorianCalendar(2020, 1, 5, 10, 30, 20);
    private final Calendar pastDate = new GregorianCalendar(2010, 5, 4, 9, 20, 1);
    private final Calendar distantPastDate = new GregorianCalendar(2002, 1, 1, 13, 10, 45);

    private ContactManager cm;

    private int testContactId;
    private String testName;
    private String testNotes;
    private Contact testContact1;
    private Contact testContact2;
    private Contact testContact3;
    private Contact additionalContact1;
    private Contact additionalContact2;
    private Meeting testMeeting;
    private int testMeetingId;

    @Before
    public void setUp() {
        cm = new ContactManagerImpl();
        currentDate = Calendar.getInstance();

        testContact1 = new ContactImpl(1, "Mowgli", "Man cub");
        testContact2 = new ContactImpl(2, "Baloo", "The sleepy brown bear");
        testContact3 = new ContactImpl(3, "Louie", "Jungle VIP");
        allContacts = new HashSet<>();

        allContacts.add(testContact1);
        allContacts.add(testContact2);
        allContacts.add(testContact3);

        additionalContact1 = new ContactImpl(4, "Bagheera", "Found Mowgli");
        additionalContact2 = new ContactImpl(5, "Kaa", "Not very successful");

        allMeetings = new ArrayList<>();

    }

    @Test
    public void testAddFutureMeetingFirstIdReturned() {
        int thisMeetingId = cm.addFutureMeeting(allContacts, futureDate);
        assertEquals(1, thisMeetingId);
    }

    @Test
    public void testAddFutureMeetingSecondIdReturned() {

    }

    @Test
    public void testAddFutureMeetingNullDate() {

    }

    @Test
    public void testAddFutureMeetingNullSetContacts() {

    }

    @Test
    public void testAddFutureMeetingDateInPast() {

    }

    @Test
    public void testAddFutureMeetingOneContactIdNonExistent() {

    }
    /**

    @Test
    public void testGetPastMeetingNormal() {

    }

     @Test
     public void testGetPastMeetingDoesntExist() {

     }

     @Test
     public void testGetPastMeetingMeetingInFuture() {

     }




    @Test
    public void testGetFutureMeetingNormal() {

    }

     @Test
     public void testGetFutureMeetingDoesntExist() {

     }

     @Test
     public void testGetFutureMeetingMeetingInPast() {

     }

     @Test
     public void testGetFutureMeetingInvalidId() {

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
    */
}
