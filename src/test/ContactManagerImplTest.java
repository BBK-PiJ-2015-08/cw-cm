/**
 * Plan for order of test creation:
 * 1. Create a new contact: (AddNewContact) X
 * 2. Construct and return sets of contacts: (getContacts(int... ids) and getContacts(String name)) X
 * 3. Create new meeting in future (addFutureMeeting) X
 * 4. Create new meeting in past (addNewPastMeeting) X
 * 5. Get and return individual meetings (getPastMeeting) X, (getFutureMeeting) & (getMeeting)
 * 6. Add notes to a meeting (addMeetingNotes)
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
import java.io.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactManagerImplTest {

    //private final Calendar currentDate = new GregorianCalendar(2016, 2, 6, 11, 45, 10);
    private static final String FILENAME = "contacts.txt";
    //private int meetingId;
    //private int contactId;
    private Calendar currentDate;
    //private List<Meeting> allMeetings;
    private Set<Contact> allContacts;
    private Set<Contact> group1;
    private Set<Contact> group2;
    private Set<Contact> group3;
    private Set<Contact> group4;
    private Set<Contact> group5;

    private final Calendar futureDate = new GregorianCalendar(2017, 10, 1, 10, 30, 30);
    private final Calendar farFutureDate = new GregorianCalendar(2020, 1, 5, 10, 30, 20);
    private final Calendar pastDate = new GregorianCalendar(2010, 5, 4, 9, 20, 1);
    private final Calendar distantPastDate = new GregorianCalendar(2002, 1, 1, 13, 10, 45);

    private ContactManager cm;

    //private int testContactId;
    //private String testName;
    //private String testNotes;
    private Contact testContact1;
    private Contact testContact2;
    private Contact testContact3;
    private Contact extraContact1;
    private Contact extraContact2;
    private Meeting testMeeting;
    private int testMeetingId;

    @Before
    public void setUp() {
        cm = new ContactManagerImpl();
        currentDate = Calendar.getInstance();
        allContacts = new HashSet<>();
        //allMeetings = new ArrayList<>();

        testContact1 = new ContactImpl(1, "Mowgli", "Man cub");
        testContact2 = new ContactImpl(2, "Baloo", "The sleepy brown bear");
        testContact3 = new ContactImpl(3, "Bagheera", "Found Mowgli");

        cm.addNewContact("Mowgli", "Man cub");
        cm.addNewContact("Baloo", "The sleepy brown bear");
        cm.addNewContact("Bagheera", "Found Mowgli");

        //Group 1 - 1 contact
        group1 = new HashSet<>();
        group1.add(testContact1);

        //Group 2 - 2 contacts
        group2 = new HashSet<>();
        group2.add(testContact1);
        group2.add(testContact2);

        //Group 3 - 3 contacts
        group3 = new HashSet<>();
        group3.add(testContact1);
        group3.add(testContact2);
        group3.add(testContact3);

        extraContact1 = new ContactImpl(4, "Louie", "Jungle VIP");
        extraContact2 = new ContactImpl(5, "Kaa", "Not very successful");

        //Group 4 - 1 contact not in the main contactmanager
        group4 = new HashSet<>();
        group4.add(extraContact1);

        //Group 5 - actually an empty set
        group5 = new HashSet<>();

    }

    @Test
    public void testAddFutureMeetingFirstIdReturned() {
        int thisMeetingId = cm.addFutureMeeting(cm.getContacts(1), futureDate);
        assertEquals(1, thisMeetingId);
    }

    @Test
    public void testAddFutureMeetingSecondIdReturned() {
        cm.addFutureMeeting(cm.getContacts(1), futureDate);
        int thisMeetingId = cm.addFutureMeeting(cm.getContacts(2,3), futureDate);
        assertEquals(2, thisMeetingId);
    }

    @Test(expected = NullPointerException.class)
    public void testAddFutureMeetingNullDate() {
        cm.addFutureMeeting(cm.getContacts(1), null);
    }

    @Test(expected = NullPointerException.class)
    public void testAddFutureMeetingNullSetContacts() {
        cm.addFutureMeeting(null, futureDate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFutureMeetingDateInPast() {
        cm.addFutureMeeting(cm.getContacts(1), distantPastDate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFutureMeetingOneContactIdNonExistentInCM() {
        cm.addFutureMeeting(group4, futureDate);
    }

    @Test
    public void testGetPastMeetingNormal() {
        cm.addNewPastMeeting(cm.getContacts(1), pastDate, "Should Mowgli leave jungle?");
        cm.getPastMeeting(1);
    }

    @Test
    public void testGetPastMeetingIdDoesntExist() {
        cm.addNewPastMeeting(cm.getContacts(1), pastDate, "Should Mowgli leave jungle?");
        assertEquals(null, cm.getPastMeeting(2));
    }
    //Testing for ((If the meeting happened in the past but is a FutureMeeting, convert it to a PastMeeting))
    /**
    @Test
    public void testGetPastMeetingWasFutureMeetingNowInPast() {
        cm.addFutureMeeting(cm.getContacts(1), futureDate);
        currentDate = farFutureDate;
        cm.getPastMeeting(1);
    }
    */

     /**
    @Test
    public void testGetFutureMeetingNormal() {

    }
*/
     @Test
     public void testGetFutureMeetingDoesntExist() {
         cm.addFutureMeeting(cm.getContacts(1), futureDate);
         assertEquals(null, cm.getPastMeeting(2));
     }
/**
     @Test
     public void testGetFutureMeetingMeetingInPast() {

     }

     @Test
     public void testGetFutureMeetingInvalidId() {

     }

    @Test
    public void testGetMeetingNormal() {

    }

     @Test
     public void testGetMeetingDoesntExist() {

     }

     @Test
     public void testGetMeetingInvalidId() {

     }

     //List will be chronologically sorted

    @Test
    public void testGetFutureMeetingListContactIdNonExistent() {

    }

     @Test
     public void testGetFutureMeetingListContactNull() {

     }

     @Test
     public void testGetFutureMeetingListNormal() {

     }

     @Test
     public void testGetFutureMeetingListNoMeetingsPlanned() {

     }

     @Test
     public void testGetFutureMeetingListHadToDisregardOneDuplicate() {

     }

     //List will be chronologically sorted

    @Test
    public void testGetMeetingListOnNormal() {

    }

     @Test
     public void testGetMeetingListOnWereNoMeetings() {

     }

     @Test
     public void testGetMeetingListOnMustDisregardOneDuplicate() {

     }

     @Test
     public void testGetMeetingListOnNullDate() {

     }

     @Test
     public void testGetMeetingListOnTryToPreferDuplicateWithNotes() {

     }

//List will be chronologically sorted

     @Test
     public void testGetPastMeetingListForNoMeetings() {

     }

     @Test
     public void testGetPastMeetingListForAttendedOne() {

     }

     @Test
     public void testGetPastMeetingListForAttendedTwo() {

     }

     @Test
     public void testGetPastMeetingListForAttendedThree() {

     }

     @Test
     public void testGetPastMeetingListForAttendedTwoDisregardOneDuplicate() {

     }

     @Test
     public void testGetPastMeetingListForAttendedTwoPreferDuplicateWithNotes() {

     }

     @Test
     public void testGetPastMeetingListForAttendedFourOneDuplicate() {

     }

     @Test
     public void testGetPastMeetingListForSingleMeetingHappenedInPastButIsFutureMeeting() {

     }

     @Test
     public void testGetPastMeetingListForContactIdNonExistent() {

     }

     @Test
     public void testGetPastMeetingListForNullContact() {

     }
*/
    @Test(expected = NullPointerException.class)
    public void testAddNewPastMeetingNullContacts() {
        cm.addNewPastMeeting(null, pastDate, "Should Mowgli leave jungle?");
    }

    @Test(expected = NullPointerException.class)
    public void testAddNewPastMeetingNullDate() {
        cm.addNewPastMeeting(cm.getContacts(1), null, "Should Mowgli leave jungle?");
    }

    @Test(expected = NullPointerException.class)
    public void testAddNewPastMeetingNullText() {
        String nullString = null;
        cm.addNewPastMeeting(cm.getContacts(1), pastDate, nullString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNewPastMeetingDateInFuture() {
        cm.addNewPastMeeting(cm.getContacts(1), farFutureDate, "Should Mowgli leave jungle?");
    }

     @Test(expected = IllegalArgumentException.class)
     public void testAddNewPastMeetingSetContactsEmpty() {
         cm.addNewPastMeeting(group5, pastDate, "Should Mowgli leave jungle?");
     }

     @Test(expected = IllegalArgumentException.class)
     public void testAddNewPastMeetingOneContactIdNonExistent() {
         cm.addNewPastMeeting(group4, pastDate, "Should Mowgli leave jungle?");
     }

     @Test
     public void testAddNewPastMeetingNormal() {
         cm.addNewPastMeeting(cm.getContacts(1), pastDate, "Should Mowgli leave jungle?");
//Need getPastMeeting to test here
     }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMeetingNotesMeetingIdNonExistent() {
        cm.addNewPastMeeting(cm.getContacts(1), pastDate, "Should Mowgli leave jungle?");
        cm.addMeetingNotes(2, "Perhaps");
    }
    /**
     @Test
     public void testAddMeetingNotesMeetingInFuture() {

     }

     @Test
     public void testAddMeetingNotesMeetingHappenedInPastButIsFutureMeeting() {

     }

     @Test
     public void testAddMeetingNotesNormal() {

     }

    @Test
    public void testAddNewContactNameEmptyString() {

    }

     @Test
     public void testAddNewContactNotesEmptyString() {

     }

    @Test
    public void testAddNewContact() {

    }
     **/

    @Test
    public void testAddNewContactNormal() {
        int result = cm.addNewContact("Akela", "A lone wolf");
        assertEquals(result, 1);
    }

    @Test(expected = NullPointerException.class)
    public void testAddNewContactNameNull() {
        cm.addNewContact(null, "A lone wolf");
    }

    @Test(expected = NullPointerException.class)
    public void testAddNewContactNotesNull() {
        cm.addNewContact("Akela", null);
    }

    @Test(expected = NullPointerException.class)
    public void testAddNewContactNameEmptyString() {
        cm.addNewContact("", "A lone wolf");
    }

    @Test(expected = NullPointerException.class)
    public void testAddNewContactNotesEmptyString() {
        cm.addNewContact("Akela", "");
    }

    @Test
    public void testAddNewContactSecondContactAddedId() {
        cm.addNewContact("Akela", "A lone wolf");
        int result = cm.addNewContact("Shere Khan", "Super evil");
        assertEquals(result, 2);
    }

 //getContacts (String name)
     @Test(expected = NullPointerException.class)
     public void testGetContactsProvidedNull() {
         String providedNull = null;
         cm.getContacts(providedNull);
     }

    //Double check what this is meant to do since the spec/forum seem to be in contradiction
     @Test
    public void testGetContactsStringIsEmptyString() {
         assertEquals(cm.getContacts(""), cm.getContacts(1,2,3));
    }


    @Test
     public void testGetContactsStringIsPresentInOneName() {
        assertEquals(cm.getContacts("Mowgli"),cm.getContacts(1));
    }

    @Test
     public void testGetContactsStringIsPresentInTwoNames() {
        assertEquals(cm.getContacts("Ba"), cm.getContacts(2,3));
     }

     @Test
     public void testGetContactsStringNotPresent() {
         Set<Contact> resultSet =  cm.getContacts("Haathi");
         assertTrue(resultSet.size() == 0);
     }
    /**
     @Test
     public void testGetContactsEmptySetContacts() {

     }

//getContacts(int... ids)


     @Test
     public void testGetContactsIdsTwoIds() {

     }
*/
    @Test
    public void testGetContactsIdsSingleId() {
        Set<Contact> resultSet = cm.getContacts(1);
        assertEquals(1, resultSet.size());
        assertEquals(cm.getContacts(1),resultSet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetContactsIdsNoIdsProvided() {
        cm.getContacts();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetContactsIdsOneProvidedIdNonExistent() {
        cm.getContacts(50);
    }
/**
    //Save all data to disk.
     //This method must be executed when the program is closed and when/if the user requests it.
    //testing:call flush and check stuff written on the outside is the same as written on the inside
    @Test
    public void testFlush() {

    }
    */
}
