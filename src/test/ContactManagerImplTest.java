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

    //private final Calendar currentDate = new GregorianCalendar(2016, 2, 6, 11, 45, 10);
    private static final String FILENAME = "contacts.txt";
    //private int meetingId;
    //private int contactId;
    private Calendar currentDate;
    private List<Meeting> allMeetings;
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
        allMeetings = new ArrayList<>();

        testContact1 = new ContactImpl(1, "Mowgli", "Man cub");
        testContact2 = new ContactImpl(2, "Baloo", "The sleepy brown bear");
        testContact3 = new ContactImpl(3, "Louie", "Jungle VIP");

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

        extraContact1 = new ContactImpl(4, "Bagheera", "Found Mowgli");
        extraContact2 = new ContactImpl(5, "Kaa", "Not very successful");
    }

    @Test
    public void testAddFutureMeetingFirstIdReturned() {
        int thisMeetingId = cm.addFutureMeeting(group1, futureDate);
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
     public void testGetPastMeetingInvalidId() {

     }

     //Testing for ((If the meeting happened in the past but is a FutureMeeting, convert it to a PastMeeting))


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

    @Test
    public void testAddNewPastMeetingDateInFuture() {

    }

     @Test
     public void testAddNewPastMeetingSetContactsEmpty() {

     }

     @Test
     public void testAddNewPastMeetingOneContactIdNonExistent() {

     }

     @Test
     public void testAddNewPastMeetingNormal() {

     }

    @Test
    public void testAddMeetingNotesMeetingIdNonExistent() {

    }

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
         String providedEmpty = "";
         cm.getContacts(providedEmpty);
    }

/**     @Test
     public void testGetContactsStringIsPresentInOneName() {

     }

     @Test
     public void testGetContactsStringIsPresentInTwoNames() {

     }

     @Test
     public void testGetContactsStringNotPresent() {

     }

     @Test
     public void testGetContactsEmptySetContacts() {

     }

//getContacts(int... ids)
    @Test
    public void testGetContactsCorrespondingToIdsSingleId() {

    }

     @Test
     public void testGetContactsCorrespondingToIdsTwoIds() {

     }

     @Test
     public void testGetContactsCorrespondingToIdsNoIdsProvided() {

     }

     @Test
     public void testGetContactsCorrespondingToIdsOneProvidedIdNonExistent() {

     }

    //Save all data to disk.
     //This method must be executed when the program is closed and when/if the user requests it.
    //testing:call flush and check stuff written on the outside is the same as written on the inside
    @Test
    public void testFlush() {

    }
    */
}
