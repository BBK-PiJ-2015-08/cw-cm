package test;

import code.Contact;
import code.ContactImpl;
import code.ContactManager;
import code.ContactManagerImpl;
import code.FutureMeeting;
import code.Meeting;
import code.PastMeeting;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.io.File;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
/**
 * @author Jade Dickinson jdicki04
 */
public class ContactManagerImplTest {
    private static final String FILENAME = "contacts.txt";
    private File checkExistence = new File(FILENAME);
    private Calendar currentDate;
    private Set<Contact> group1;
    private Set<Contact> group2;
    private ContactManager cm;

    private Contact testContact1;
    private Contact testContact2;
    private Contact testContact3;
    private Contact extraContact1;
    private Contact extraContact2;

    private final Calendar futureDate = new GregorianCalendar(2017, 10, 1, 10, 30, 30);
    private final Calendar futureDatePM = new GregorianCalendar(2017, 10, 1, 20, 30, 30);
    private final Calendar farFutureDate = new GregorianCalendar(2020, 1, 5, 10, 30, 20);
    private final Calendar pastDate = new GregorianCalendar(2010, 5, 4, 9, 20, 1);
    private final Calendar pastDatePM = new GregorianCalendar(2010, 5, 4, 20, 30, 30);
    private final Calendar distantPastDate = new GregorianCalendar(2002, 1, 1, 13, 10, 45);
    private static final int JUSTATICK = 10;
    private static final int TWOTICKS = 20;
    private static final String SHOULDMOWGLILEAVE = "Should Mowgli leave jungle?";
    private static final String MOWGLICONSIDERED = "Mowgli considered joining the Dawn Patrol";
    private static final String WOLFNAME = "Akela";
    private static final String WOLFNOTES = "A lone wolf";
    private static final String MOWGLIRETURN = "Mowgli is returning to the man-village";
    private static final String QUESTIONRETURN = "Should Mowgli return to the man-village?";
    private static final String LAIR = "How to infiltrate monkey lair";
    private static final String BEES = "Stealing honey from bees";

    @Before
    public void setUp() {
        if (checkExistence.exists()) {
            checkExistence.delete();
        }
        cm = new ContactManagerImpl();
        currentDate = Calendar.getInstance();

        testContact1 = new ContactImpl(1, "Mowgli", "Man cub");
        testContact2 = new ContactImpl(2, "Baloo", "The sleepy brown bear");
        testContact3 = new ContactImpl(3, "Bagheera", "Found Mowgli");

        cm.addNewContact("Mowgli", "Man cub");
        cm.addNewContact("Baloo", "The sleepy brown bear");
        cm.addNewContact("Bagheera", "Found Mowgli");

        extraContact1 = new ContactImpl(4, "Louie", "Jungle VIP");
        extraContact2 = new ContactImpl(5, "Kaa", "Not very successful");

        //Group 1 - 1 contact not in the main ContactManager
        group1 = new HashSet<>();
        group1.add(extraContact1);

        //Group 2 - actually an empty set
        group2 = new HashSet<>();
    }

    /**
     * Test addFutureMeeting()
     * Testing flush(): call flush and check stuff written on the outside is the same as written on the inside.
     */
    @Test
    public void testAddFutureMeetingIdReturned() {
        int thisMeetingId = cm.addFutureMeeting(cm.getContacts(1), futureDate);
        assertEquals(1, thisMeetingId);
        cm.flush();
    }

    @Test
    public void testAddFutureMeetingSecondIdReturned() {
        cm.addFutureMeeting(cm.getContacts(1), futureDate);
        int thisMeetingId = cm.addFutureMeeting(cm.getContacts(2, 3), futureDate);
        assertEquals(2, thisMeetingId);
    }

    @Test
    public void testAddFutureMeetingDetails() {
        cm.addFutureMeeting(cm.getContacts(1), futureDate);
        assertEquals(cm.getFutureMeeting(1).getId(), 1);
        assertEquals(cm.getFutureMeeting(1).getDate(), futureDate);
        assertEquals(cm.getFutureMeeting(1).getContacts(), cm.getContacts(1));
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
    public void testAddFutureMeetingDateIsNow() {
        cm.addFutureMeeting(cm.getContacts(1), currentDate);
        Meeting check = cm.getFutureMeeting(1);
        assertEquals(check.getId(), 1);
        assertEquals(check.getDate(), currentDate);
        assertEquals(check.getContacts(), cm.getContacts(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddFutureMeetingOneContactIdNonExistentInCM() {
        cm.addFutureMeeting(group1, futureDate);
    }

    /**
     * Test getPastMeeting()
     */

    @Test
    public void testGetPastMeetingNormal() {
        cm.addNewPastMeeting(cm.getContacts(1), pastDate, SHOULDMOWGLILEAVE);
        PastMeeting check = cm.getPastMeeting(1);
        assertEquals(check.getNotes(), SHOULDMOWGLILEAVE);
        assertEquals(check.getContacts(), cm.getContacts(1));
        assertEquals(check.getDate(), pastDate);
        assertEquals(check.getId(), 1);
    }

    @Test
    public void testGetPastMeetingIdDoesntExist() {
        cm.addNewPastMeeting(cm.getContacts(1), pastDate, SHOULDMOWGLILEAVE);
        assertNull(cm.getPastMeeting(2));
    }

    @Test
    public void testGetPastMeetingNoMeetingsExist() {
        assertNull(cm.getPastMeeting(1));
    }

    @Test
    public void testGetPastMeetingIdZero() {
        cm.addNewPastMeeting(cm.getContacts(1), pastDate, SHOULDMOWGLILEAVE);
        assertNull(cm.getPastMeeting(0));
    }

    @Test
    public void testGetPastMeetingIdNegative() {
        cm.addNewPastMeeting(cm.getContacts(1), pastDate, SHOULDMOWGLILEAVE);
        assertNull(cm.getPastMeeting(-1));
    }

    @Test(expected = IllegalStateException.class)
    public void testGetPastMeetingMeetingInFuture() {
        cm.addFutureMeeting(cm.getContacts(1), farFutureDate);
        cm.getPastMeeting(1);
    }

    @Test
    public void testGetPastMeetingMeetingNow() {
        cm.addNewPastMeeting(cm.getContacts(1), currentDate, "Leaving the jungle");
        assertSame(cm.getPastMeeting(1), cm.getMeeting(1));
    }

    @Test
    public void testGetPastMeetingFutureMeetingNowInPast() {
        Calendar nearFutureDate = Calendar.getInstance();
        nearFutureDate.add(Calendar.MILLISECOND, JUSTATICK);
        cm.addFutureMeeting(cm.getContacts(1), nearFutureDate);
        try {
            Thread.sleep(TWOTICKS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        PastMeeting check = cm.getPastMeeting(1);
        assertEquals(check.getId(), 1);
        assertEquals(check.getDate(), nearFutureDate);
        assertEquals(check.getContacts(), cm.getContacts(1));
        assertEquals(check.getNotes(), "");
    }

    /**
     * Test getFutureMeeting()
     */

    @Test
    public void testGetFutureMeetingNormal() {
        cm.addFutureMeeting(cm.getContacts(1, 2), futureDate);
        FutureMeeting check = cm.getFutureMeeting(1);
        assertEquals(check.getContacts(), cm.getContacts(1, 2));
        assertEquals(check.getDate(), futureDate);
        assertEquals(check.getId(), 1);
    }

    @Test
    public void testGetFutureMeetingDoesntExist() {
        cm.addFutureMeeting(cm.getContacts(2), futureDate);
        assertNull(cm.getFutureMeeting(2));
    }

    @Test
    public void testGetFutureMeetingNoMeetingsExist() {
        assertNull(cm.getFutureMeeting(1));
    }

    @Test
    public void testGetFutureMeetingIdZero() {
        cm.addFutureMeeting(cm.getContacts(2), futureDate);
        assertNull(cm.getFutureMeeting(0));
    }

    @Test
    public void testGetFutureMeetingIdNegative() {
        cm.addFutureMeeting(cm.getContacts(2), futureDate);
        assertNull(cm.getFutureMeeting(-1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFutureMeetingMeetingInPast() {
        cm.addNewPastMeeting(cm.getContacts(2), pastDate, "Doo-bee doo-bee doo-bee dee-doo");
        cm.getFutureMeeting(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFutureMeetingMeetingNow() {
        cm.addNewPastMeeting(cm.getContacts(2), currentDate, "A scooby doo bop bop");
        cm.getFutureMeeting(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFutureMeetingFutureMeetingNowInPast() {
        Calendar nearFutureDate = Calendar.getInstance();
        nearFutureDate.add(Calendar.MILLISECOND, JUSTATICK);
        cm.addFutureMeeting(cm.getContacts(1), nearFutureDate);
        try {
            Thread.sleep(TWOTICKS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        FutureMeeting check = cm.getFutureMeeting(1);
        assertNull(check);
    }

    /**
     * Test getMeeting()
     */

    @Test
    public void testGetMeetingNormalFuture() {
        cm.addFutureMeeting(cm.getContacts(1, 2), futureDate);
        Meeting checkFuture = cm.getMeeting(1);
        assertEquals(checkFuture.getContacts(), cm.getContacts(1, 2));
        assertEquals(checkFuture.getDate(), futureDate);
        assertEquals(checkFuture.getId(), 1);
    }

    @Test
    public void testGetMeetingNormalPast() {
        cm.addNewPastMeeting(cm.getContacts(2, 3), pastDate, LAIR);
        Meeting checkPast = cm.getMeeting(1);
        assertEquals(checkPast.getContacts(), cm.getContacts(2, 3));
        assertEquals(checkPast.getDate(), pastDate);
        assertEquals(checkPast.getId(), 1);

        PastMeeting checkPastNotes = (PastMeeting) cm.getMeeting(1);
        assertEquals(checkPastNotes.getNotes(), LAIR);
    }

    @Test
    public void testGetMeetingIdDoesntExist() {
        cm.addFutureMeeting(cm.getContacts(1), futureDate);
        assertNull(cm.getMeeting(3));
        cm.addNewPastMeeting(cm.getContacts(2), pastDate, "Looking for bear necessities");
        assertNull(cm.getMeeting(4));
    }

    @Test
    public void testGetMeetingNoMeetingsExist() {
        assertNull(cm.getMeeting(1));
    }

    @Test
    public void testGetMeetingIdZero() {
        cm.addNewPastMeeting(cm.getContacts(2), pastDate, BEES);
        assertNull(cm.getMeeting(0));
    }

    @Test
    public void testGetMeetingIdNegative() {
        cm.addNewPastMeeting(cm.getContacts(2), pastDate, BEES);
        assertNull(cm.getMeeting(-1));
    }

    /**
     * Test getFutureMeetingList()
     */

    @Test(expected = IllegalArgumentException.class)
    public void testGetFutureMeetingListContactNonExistent() {
        cm.getFutureMeetingList(extraContact1);
    }

    @Test(expected = NullPointerException.class)
    public void testGetFutureMeetingListContactNull() {
        cm.getFutureMeetingList(null);
    }

    @Test
    public void testGetFutureMeetingListOneMeeting() {
        cm.addFutureMeeting(cm.getContacts(1), futureDate);
        List<Meeting> check = cm.getFutureMeetingList(testContact1);
        assertEquals(check.size(), 1);
        assertTrue(check.contains(cm.getFutureMeeting(1)));
        assertEquals(check.get(0), cm.getFutureMeeting(1));
        assertEquals(check.get(0).getContacts(), cm.getFutureMeeting(1).getContacts());
        assertEquals(check.get(0).getDate(), cm.getFutureMeeting(1).getDate());
    }

    @Test
    public void testGetFutureMeetingListTwoMeetings() {
        cm.addFutureMeeting(cm.getContacts(1), farFutureDate);
        cm.addFutureMeeting(cm.getContacts(1, 2), futureDate);
        List<Meeting> check = cm.getFutureMeetingList(testContact1);
        assertEquals(check.size(), 2);
        assertTrue(check.contains(cm.getFutureMeeting(1)));
        assertTrue(check.contains(cm.getFutureMeeting(2)));
        assertEquals(check.get(0), cm.getFutureMeeting(2));
        assertEquals(check.get(1), cm.getFutureMeeting(1));
        assertEquals(check.get(0).getContacts(), cm.getFutureMeeting(2).getContacts());
        assertEquals(check.get(0).getDate(), cm.getFutureMeeting(2).getDate());
        assertEquals(check.get(1).getContacts(), cm.getFutureMeeting(1).getContacts());
        assertEquals(check.get(1).getDate(), cm.getFutureMeeting(1).getDate());
    }

    @Test
    public void testGetFutureMeetingListNoMeetings() {
        List<Meeting> check = cm.getFutureMeetingList(testContact2);
        assertTrue(check.isEmpty());
    }

    @Test
    public void testGetFutureMeetingListThreeMeetingsOneSamePropertiesExceptId() {
        cm.addFutureMeeting(cm.getContacts(1), farFutureDate);
        cm.addFutureMeeting(cm.getContacts(1), farFutureDate);
        cm.addFutureMeeting(cm.getContacts(1, 2), futureDate);
        List<Meeting> check = cm.getFutureMeetingList(testContact1);
        assertEquals(check.size(), 3);
    }

    @Test
    public void testGetFutureMeetingListWereTwoFutureNowOneHasPast() {
        Calendar nearFutureDate = Calendar.getInstance();
        nearFutureDate.add(Calendar.MILLISECOND, JUSTATICK);
        cm.addFutureMeeting(cm.getContacts(1), nearFutureDate);
        try {
            Thread.sleep(TWOTICKS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        cm.addFutureMeeting(cm.getContacts(1), farFutureDate);
        List<Meeting> check = cm.getFutureMeetingList(testContact1);
        assertEquals(check.size(), 1);
        assertEquals(check.get(0).getId(), 2);
        assertEquals(check.get(0).getContacts(), cm.getContacts(1));
        assertEquals(check.get(0).getDate(), farFutureDate);
    }


    @Test
    public void testGetFutureMeetingListWereThreeFutureTwoForContactOneHasNowPast() {
        Calendar nearFutureDate = Calendar.getInstance();
        nearFutureDate.add(Calendar.MILLISECOND, JUSTATICK);
        cm.addFutureMeeting(cm.getContacts(1), nearFutureDate);
        try {
            Thread.sleep(TWOTICKS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        cm.addFutureMeeting(cm.getContacts(2), futureDate);
        cm.addFutureMeeting(cm.getContacts(1), farFutureDate);
        List<Meeting> check = cm.getFutureMeetingList(testContact1);
        assertEquals(check.size(), 1);
        assertEquals(check.get(0).getId(), 3);
        assertEquals(check.get(0).getContacts(), cm.getContacts(1));
        assertEquals(check.get(0).getDate(), farFutureDate);
    }

    /**
     * Test getMeetingListOn()
     */

    @Test(expected = NullPointerException.class)
    public void testGetMeetingListOnNullDate() {
        cm.getMeetingListOn(null);
    }

    @Test
    public void testGetMeetingListOnOneFutureMeeting() {
        cm.addFutureMeeting(cm.getContacts(1), futureDate);
        List<Meeting> check = cm.getMeetingListOn(futureDate);
        assertEquals(check.size(), 1);
        assertEquals(check.get(0), cm.getFutureMeeting(1));
        assertEquals(check.get(0).getId(), cm.getFutureMeeting(1).getId());
        assertEquals(check.get(0).getContacts(), cm.getFutureMeeting(1).getContacts());
        assertEquals(check.get(0).getDate(), cm.getFutureMeeting(1).getDate());
    }

    @Test
    public void testGetMeetingListOnOnePastMeeting() {
        cm.addNewPastMeeting(cm.getContacts(1), pastDate, "Safe from Shere Khan");
        List<Meeting> check = cm.getMeetingListOn(pastDate);
        assertEquals(check.size(), 1);
        assertEquals(check.get(0), cm.getPastMeeting(1));
        assertEquals(check.get(0).getId(), cm.getPastMeeting(1).getId());
        assertEquals(check.get(0).getContacts(), cm.getPastMeeting(1).getContacts());
        assertEquals(check.get(0).getDate(), cm.getPastMeeting(1).getDate());
    }

    @Test
    public void testGetMeetingListOnNoMeetings() {
        List<Meeting> check = cm.getMeetingListOn(pastDate);
        assertEquals(check.size(), 0);
    }

    @Test
    public void testGetMeetingListOnTwoFutureOneDay() {
        cm.addFutureMeeting(cm.getContacts(3), futureDatePM);
        cm.addFutureMeeting(cm.getContacts(2), futureDate);
        List<Meeting> check = cm.getMeetingListOn(futureDate);
        assertEquals(check.size(), 1);
        assertTrue(check.contains(cm.getFutureMeeting(2)));
        assertEquals(check.get(0).getContacts(), cm.getFutureMeeting(2).getContacts());
        assertEquals(check.get(0).getDate(), cm.getFutureMeeting(2).getDate());
    }

    @Test
    public void testGetMeetingListOnTwoPastOneDay() {
        cm.addNewPastMeeting(cm.getContacts(3), pastDatePM, "Everybody wants to be a cat");
        cm.addNewPastMeeting(cm.getContacts(2), pastDate, "Groovin'");
        List<Meeting> check = cm.getMeetingListOn(pastDate);
        assertEquals(check.size(), 1);
        assertTrue(check.contains(cm.getPastMeeting(2)));
        assertEquals(check.get(0).getContacts(), cm.getPastMeeting(2).getContacts());
        assertEquals(check.get(0).getDate(), cm.getPastMeeting(2).getDate());
        assertEquals(check.get(0).getDate(), cm.getPastMeeting(2).getDate());
    }

    /**
     * Test getPastMeetingListFor()
     */

    @Test(expected = NullPointerException.class)
    public void testGetPastMeetingListForNullContact() {
        cm.getPastMeetingListFor(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetPastMeetingListForNonExistentContact() {
        cm.getPastMeetingListFor(extraContact2);
    }

    @Test
    public void testGetPastMeetingListForOne() {
        cm.addNewPastMeeting(cm.getContacts(1), pastDate, "Mowgli ruminating");
        List<PastMeeting> check = cm.getPastMeetingListFor(testContact1);
        assertEquals(check.size(), 1);
        assertTrue(check.contains(cm.getPastMeeting(1)));
        assertEquals(check.get(0), cm.getPastMeeting(1));
        assertEquals(check.get(0).getContacts(), cm.getPastMeeting(1).getContacts());
        assertEquals(check.get(0).getDate(), cm.getPastMeeting(1).getDate());
        assertEquals(check.get(0).getNotes(), cm.getPastMeeting(1).getNotes());
    }

    @Test
    public void testGetPastMeetingListForTwo() {
        cm.addNewPastMeeting(cm.getContacts(1), pastDate, "Eating ants");
        cm.addNewPastMeeting(cm.getContacts(1, 2), distantPastDate, "Learning about paw paws and prickly pears");
        List<PastMeeting> check = cm.getPastMeetingListFor(testContact1);
        assertEquals(check.size(), 2);
        assertTrue(check.contains(cm.getPastMeeting(1)));
        assertTrue(check.contains(cm.getPastMeeting(2)));
        assertEquals(check.get(0), cm.getPastMeeting(2));
        assertEquals(check.get(1), cm.getPastMeeting(1));
        assertEquals(check.get(0).getContacts(), cm.getPastMeeting(2).getContacts());
        assertEquals(check.get(0).getDate(), cm.getPastMeeting(2).getDate());
        assertEquals(check.get(0).getNotes(), cm.getPastMeeting(2).getNotes());
        assertEquals(check.get(1).getContacts(), cm.getPastMeeting(1).getContacts());
        assertEquals(check.get(1).getDate(), cm.getPastMeeting(1).getDate());
        assertEquals(check.get(1).getNotes(), cm.getPastMeeting(1).getNotes());
    }

    @Test
    public void testGetPastMeetingListForNoMeetings() {
        List<PastMeeting> check = cm.getPastMeetingListFor(testContact3);
        assertTrue(check.isEmpty());
    }

    @Test
    public void testGetPastMeetingListForThreeMeetingsOneDuplicatesPropertiesButNotId() {
        cm.addNewPastMeeting(cm.getContacts(1), pastDate, "Skimming stones");
        cm.addNewPastMeeting(cm.getContacts(1), pastDate, "Skimming stones");
        cm.addNewPastMeeting(cm.getContacts(1, 2), distantPastDate, "Trying fancy ants");
        List<PastMeeting> check = cm.getPastMeetingListFor(testContact1);
        assertEquals(check.size(), 3);
    }

    @Test
    public void testGetPastMeetingListForWereTwoFutureNowOneHasPast() {
        Calendar nearFutureDate = Calendar.getInstance();
        nearFutureDate.add(Calendar.MILLISECOND, JUSTATICK);
        cm.addFutureMeeting(cm.getContacts(1), nearFutureDate);
        try {
            Thread.sleep(TWOTICKS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        cm.addFutureMeeting(cm.getContacts(2), farFutureDate);
        List<PastMeeting> check = cm.getPastMeetingListFor(testContact1);
        assertEquals(check.size(), 1);
        assertEquals(check.get(0).getId(), 1);
        assertEquals(check.get(0).getContacts(), cm.getContacts(1));
        assertEquals(check.get(0).getDate(), nearFutureDate);
        assertEquals(check.get(0).getNotes(), "");
    }

    /**
     * Test addNewPastMeeting()
     */

    @Test(expected = NullPointerException.class)
    public void testAddNewPastMeetingNullContacts() {
        cm.addNewPastMeeting(null, pastDate, MOWGLICONSIDERED);
    }

    @Test(expected = NullPointerException.class)
    public void testAddNewPastMeetingNullDate() {
        cm.addNewPastMeeting(cm.getContacts(1), null, MOWGLICONSIDERED);
    }

    @Test(expected = NullPointerException.class)
    public void testAddNewPastMeetingNullText() {
        String nullString = null;
        cm.addNewPastMeeting(cm.getContacts(1), pastDate, nullString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNewPastMeetingDateInFuture() {
        cm.addNewPastMeeting(cm.getContacts(1), farFutureDate, MOWGLICONSIDERED);
    }

    @Test
    public void testAddNewPastMeetingDateIsNow() {
        cm.addNewPastMeeting(cm.getContacts(1), currentDate, "Setting Shere Khan's tail on fire");
        PastMeeting check = cm.getPastMeeting(1);
        assertEquals(check.getId(), 1);
        assertEquals(check.getDate(), currentDate);
        assertEquals(check.getContacts(), cm.getContacts(1));
        assertEquals(check.getNotes(), "Setting Shere Khan's tail on fire");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNewPastMeetingSetContactsEmpty() {
        cm.addNewPastMeeting(group2, pastDate, MOWGLICONSIDERED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNewPastMeetingOneContactIdNonExistent() {
        cm.addNewPastMeeting(group1, pastDate, MOWGLICONSIDERED);
    }

    @Test
    public void testAddNewPastMeetingNormal() {
        cm.addNewPastMeeting(cm.getContacts(1), pastDate, MOWGLICONSIDERED);
        PastMeeting check = cm.getPastMeeting(1);
        assertEquals(check.getId(), 1);
        assertEquals(check.getDate(), pastDate);
        assertEquals(check.getContacts(), cm.getContacts(1));
        assertEquals(check.getNotes(), MOWGLICONSIDERED);
    }

    /**
     * Test addMeetingNotes()
     */

    @Test(expected = IllegalArgumentException.class)
    public void testAddMeetingNotesMeetingIdNonExistent() {
        cm.addNewPastMeeting(cm.getContacts(1), pastDate, QUESTIONRETURN);
        cm.addMeetingNotes(2, "Perhaps");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMeetingNotesMeetingIdZero() {
        cm.addNewPastMeeting(cm.getContacts(1), pastDate, QUESTIONRETURN);
        assertNull(cm.addMeetingNotes(0, "Perhaps"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMeetingNotesMeetingIdNegative() {
        cm.addNewPastMeeting(cm.getContacts(1), pastDate, QUESTIONRETURN);
        cm.addMeetingNotes(-1, "Perhaps");
    }

    @Test(expected = IllegalStateException.class)
    public void testAddMeetingNotesMeetingInFuture() {
        cm.addFutureMeeting(cm.getContacts(1), futureDate);
        cm.addMeetingNotes(1, "Mowgli should rejoin the man tribe");
    }

    @Test
    public void testAddMeetingNotesFutureMeetingNowInPast() {
        Calendar nearFutureDate = Calendar.getInstance();
        nearFutureDate.add(Calendar.MILLISECOND, JUSTATICK);
        cm.addFutureMeeting(cm.getContacts(1), nearFutureDate);
        try {
            Thread.sleep(TWOTICKS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        cm.addMeetingNotes(1, MOWGLIRETURN);
        assertEquals(cm.getPastMeeting(1).getNotes(), MOWGLIRETURN);
    }

    @Test
    public void testAddMeetingNotesNormal() {
        cm.addNewPastMeeting(cm.getContacts(1, 3), distantPastDate, "That ");
        cm.addMeetingNotes(1, "Baloo sure sings a lot");
        assertEquals(cm.getPastMeeting(1).getNotes(), "That Baloo sure sings a lot");
    }

    @Test(expected = NullPointerException.class)
    public void testAddMeetingNotesAddingNullNotes() {
        cm.addNewPastMeeting(cm.getContacts(1, 3), distantPastDate, "");
        String nullNotes = null;
        cm.addMeetingNotes(1, nullNotes);
    }

    /**
     * Test addNewContact()
     */

    @Test
    public void testAddNewContactNormal() {
        int result = cm.addNewContact(WOLFNAME, WOLFNOTES);
        assertEquals(result, 4);
    }

    @Test(expected = NullPointerException.class)
    public void testAddNewContactNameNull() {
        cm.addNewContact(null, WOLFNOTES);
    }

    @Test(expected = NullPointerException.class)
    public void testAddNewContactNotesNull() {
        cm.addNewContact(WOLFNAME, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNewContactNameEmptyString() {
        cm.addNewContact("", WOLFNOTES);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNewContactNotesEmptyString() {
        cm.addNewContact(WOLFNAME, "");
    }

    @Test
    public void testAddNewContactSecondContactAddedId() {
        cm.addNewContact(WOLFNAME, WOLFNOTES);
        int result = cm.addNewContact("Shere Khan", "Super evil");
        assertEquals(result, 5);
    }

    /**
     * Test getContacts (String name)
     */

    @Test(expected = NullPointerException.class)
    public void testGetContactsProvidedNull() {
        String providedNull = null;
        cm.getContacts(providedNull);
    }

    //Double check what this is meant to do since the spec/forum seem to be in contradiction
    @Test
    public void testGetContactsStringIsEmptyString() {
         assertEquals(cm.getContacts(""), cm.getContacts(1, 2, 3));
    }


    @Test
    public void testGetContactsStringIsPresentInOneName() {
        assertEquals(cm.getContacts("Mowgli"), cm.getContacts(1));
    }

    @Test
    public void testGetContactsStringIsPresentInTwoNames() {
        assertEquals(cm.getContacts("Ba"), cm.getContacts(2, 3));
    }

    @Test
    public void testGetContactsStringNotPresent() {
        Set<Contact> resultSet =  cm.getContacts("Haathi");
        assertSame(resultSet.size(), 0);
    }

    /**
     * Test getContacts(int... ids)
     */

    @Test
    public void testGetContactsIdsSingleId() {
        Set<Contact> resultSet = cm.getContacts(1);
        assertEquals(1, resultSet.size());
    }

    @Test
    public void testGetContactsIdsTwoIds() {
        Set<Contact> resultSet = cm.getContacts(1, 2);
        assertEquals(2, resultSet.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetContactsIdsNoIdsProvided() {
        cm.getContacts();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetContactsIdsIdNonExistent() {
        cm.getContacts(50);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetContactsIdsIdZero() {
        cm.getContacts(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetContactsIdsIdNegative() {
        cm.getContacts(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetContactsIdsNoneProvided() {
        cm.getContacts();
    }
}
