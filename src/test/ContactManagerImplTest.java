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

    @Before
    public void setUp() {

    }
    /**
     * @Test
     */
}
