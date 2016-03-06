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

      * Create a new record for a meeting that took place in the past.
      *
      * @param contacts a list of participants
     * @param date     the date on which the meeting took place
     * @param text     messages to be added about the meeting.
     * @throws IllegalArgumentException if the list of contacts is empty, or any of the contacts does not exist
     * @throws NullPointerException     if any of the arguments is null
     *
     *
     * Check the date IS in the past. If it isn't; throw an IllegalArgumentException (forum)
     * Check the Set of contacts is not empty; if it is throw an IllegalArgumentException
     * Check that each the ID for each contact exists; if any don't straight away throw an IllegalArgumentException
     * Check that none of the arguments are null; if any are straight away throw a NullPointerException
     * Create a new PastMeeting with the given values.

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

      * Add notes to a meeting.
     *
     * This method is used when a future meeting takes place, and is then converted to a past meeting (with notes)
     * and returned.
     *
     * It can be also used to add notes to a past meeting at a later date.
     *
     * @param id   the ID of the meeting
     * @param text messages to be added about the meeting.
     * @throws IllegalArgumentException if the meeting does not exist
     * @throws IllegalStateException    if the meeting is set for a date in the future
     * @throws NullPointerException     if the notes are null
     *
     * Check if the meeting ID exists; if it doesn't throw an IllegalArgumentException
     * Check the meeting has already taken place; if it hasn't throw an IllegalStateException
     * Check if the notes to be added are null; if they are throw a NullPointerException
     * Add the notes to the already existing notes (which may be empty)
     * When testing, need to make sure that if notes existed already, new notes were added to existing notes rather
     * than replacing them.
     * Return the PastMeeting with notes

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

      * Create a new contact with the specified name and notes.
     *
     * @param name  the name of the contact.
     * @param notes notes to be added about the contact.
     * @return the ID for the new contact
     * @throws IllegalArgumentException if the name or the notes are empty strings
     * @throws NullPointerException     if the name or the notes are null
     *
     * Check if the name or notes are empty strings; if either are throw an IllegalArgumentException
     * Check if the name or notes are null; if either are throw a NullPointerException
     * There should be an int field for contactID. This should start at 0, and be incremented by 1 at method start,
     * before being used.
     * Create a new contact with the current value of contactID (which we increased by 1 at the start of the method),
     * with the provided values for name and notes.
     * Return a copy of the current value of contactID.

    @Test
    public void testAddNewContactNameEmptyString() {

    }

     @Test
     public void testAddNewContactNotesEmptyString() {

     }

     @Test
     public void testAddNewContactNormal() {

     }

      * Returns a Set with the contacts whose name contains that string:
     *
     * If the string is the empty string, this methods returns the set that contains all current contacts.
     *
     * "In the case that a name (or any substring) is provided "that is not present in the set of contacts held" by the
     * ContactManager, then it returns "the contacts whose name contains that string", i.e. the empty set."
     *
     * @param name the string to search for
     * @return a list with the contacts whose name contains that string. - a set of contacts: Set<Contact>
     *
     * @throws NullPointerException if the parameter is null
     *
     * Check the provided name is not null; if it is throw a NullPointerException
     * Create an empty set.
     * If the string is "", assign the set containing all current contacts to the empty set.
     * If not, look through each contact in turn and check if their name contains the provided string. If it does,
     * add it to the Set.
     * Don't plan to make this lenient with upper/lowercase
     * Return the set

     @Test
     public void testGetContactsProvidedNull() {

     }

     @Test
    public void testGetContactsStringIsEmptyString() {

    }

     @Test
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

      * Returns a Set containing the contacts that correspond to the IDs.
     * Note that this method can be used to retrieve just one contact by passing only one ID.
     *
     * @param ids an arbitrary number of contact IDs
     * @return a list containing the contacts that correspond to the IDs. - a set of contacts: Set<Contact>
     * @throws IllegalArgumentException if no IDs are provided or if any of the provided IDs does not correspond to a
     * real contact
     *
     * Check that at least one ID is provided; if not, throw an IllegalArgumentException
     * Check that every contactID exists; if any don't, throw an IllegalArgumentException
     * Else, create an empty set
     * Going through the contactIDs provided in order, add the contact corresponding to each contactID to the Set.
     * Return the set.

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

    Save all data to disk.
     * This method must be executed when the program is closed and when/if the user requests it.
    //testing:call flush and check stuff written on the outside is the same as written on the inside
    @Test
    public void testFlush() {

    }
    */
}
