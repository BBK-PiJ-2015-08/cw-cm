package code;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.*;
/**
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
*/

/**
 * @author Jade Dickinson jdicki04
 */
public class ContactManagerImpl implements ContactManager, Serializable {
    private static final String FILENAME = "contacts.txt";
    private int meetingId;
    private int contactId;
    private Calendar currentDate;
    private List<Meeting> allMeetings;
    private Set<Contact> allContacts;

    public ContactManagerImpl() {
        File file = new File(FILENAME);
        currentDate = Calendar.getInstance();
        //Below checks both that the file exists and that it contains at least one character
        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream
                         decoding = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(FILENAME)));) {
                allMeetings = (List<Meeting>) decoding.readObject();
                allContacts = (Set<Contact>) decoding.readObject();
                meetingId = (int) decoding.readObject();
                contactId = (int) decoding.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                System.err.println("Error: " + ex);
            }
        }
        else {
            //The below should happen both when file didn't originally exist and if file.length() = 0
            meetingId = 0;
            contactId = 0;
            allMeetings = new ArrayList<>();
            allContacts = new HashSet<>();
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Add a new meeting to be held in the future.
     * An ID is returned when the meeting is put into the system. This
     * ID must be positive and non-zero.
     *
     * @param contacts a list of contacts that will participate in the meeting
     * @param date     the date on which the meeting will take place
     *
     * @return the ID for the meeting
     * @throws IllegalArgumentException if the meeting is set for a time in the past, or if any contact is
     * unknown / non-existent.
     * @throws NullPointerException     if the set of contacts or the date are null
     */
    @Override
    public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
        /**
         * Check if the provided date or Set of contacts are null; if either are throw a NullPointerException
         * Check that the date given is not in the past; if it is throw an IllegalArgumentException
         * Within the Set of contacts, check every contact's ID exists; if any don't throw an IllegalArgumentException
         * There should be an int field for meetingID. This should start at 0 (the value 0 is never used), and be
         * incremented up by 1 at the beginning of the method, before being used.
         * Create a new FutureMeeting with the current value of meetingID (which we just increased by 1), with the provided
         * values for Set<Contact> contacts and date.
         * Return a copy of the current value of meetingID.
         */
        if (contacts == null || date == null) {
            throw new NullPointerException("Make sure neither date not set of contacts provided are null");
        }
        else if (date.before(currentDate)) {
            throw new IllegalArgumentException("Date can't be in the past");
        }
        else if (!allContacts.containsAll(contacts)) {
            throw new IllegalArgumentException("All contacts must exist already");
        }
        else {
            meetingId++;
            allMeetings.add(new FutureMeetingImpl(meetingId, date, contacts));
            int currentMeetingId = meetingId;
            return currentMeetingId;
        }
    }

    /**
     * Returns the PAST meeting with the requested ID, or null if it there is none.
     *
     * The meeting must have happened at a past date.
     *
     * @param id the ID for the meeting
     * @return the meeting with the requested ID, or null if it there is none.
     * @throws IllegalStateException if there is a meeting with that ID happening in the future
     */
    @Override
    public PastMeeting getPastMeeting(int id) {
        /**
        Check the ID exists; if it doesn't return null
         Check the meeting happened in the past; if it is yet to happen throw an IllegalStateException
         ((If the meeting happened in the past but is a FutureMeeting, convert it to a PastMeeting))
         Return a PastMeeting
         Aim for only one return
         */
        PastMeeting thisMeetingOrNull = null;
        if (validID(id)) {
            for (Meeting m : allMeetings) {
                if (m.getId() == id) {
                    //Meeting happened in future RATHER THAN PAST
                    if (!m.getDate().before(currentDate)) {
                        throw new IllegalStateException("Meeting date must be in the past");
                    }
                    //Sergio stated converting FutureMeeting to PastMeeting where necessary is not necessary
                    /**
                    else if (m instanceof FutureMeeting) {
                        //could convert it to a past meeting
                        thisMeetingOrNull = new PastMeetingImpl(m.getId(), m.getDate(), m.getContacts(), "");
                    }
                     */
                    else {
                        thisMeetingOrNull = (PastMeeting) m;
                    }
                }
            }
        }
        return thisMeetingOrNull;
    }

    /**
     * Returns the FUTURE meeting with the requested ID, or null if there is none.
     *
     * @param id the ID for the meeting
     * @return the meeting with the requested ID, or null if it there is none.
     * @throws IllegalArgumentException if there is a meeting with that ID happening
     *                                  in the past
     */
    @Override
    public FutureMeeting getFutureMeeting(int id) {
        /**
         Check the ID exists; if it doesn't return null
         Check the meeting is scheduled in the future; if it has already happened throw an IllegalArgumentException
         If the meeting is scheduled in the future but is a PastMeeting throw an IllegalArgumentException
         Return a FutureMeeting
         Aim for only one return
         */
        FutureMeeting thisMeetingOrNull = null;
        if (validID(id)) {
            for (Meeting m : allMeetings) {
                if (m.getId() == id) {
                    if (!m.getDate().after(currentDate)) {
                        throw new IllegalArgumentException("Meeting has already happened");
                    }
                    else {
                        thisMeetingOrNull = (FutureMeeting) m;
                    }
                }
            }
        }
        return thisMeetingOrNull;
    }

    /**
     * Returns the meeting with the requested ID, or null if it there is none.
     *
     * @param id the ID for the meeting
     * @return the meeting with the requested ID, or null if it there is none.
     */
    /**
     * Check the ID exists; if it doesn't return null
     * Otherwise, return the meeting which corresponds to this ID (could be in the past or future)
     */
    @Override
    public Meeting getMeeting(int id) {
        Meeting thisMeetingOrNull = null;
        if (validID(id)) {
            for (Meeting m : allMeetings) {
                if (m.getId() == id) {
                    thisMeetingOrNull = m;
                }
            }
        }
        return thisMeetingOrNull;
    }
    
    /**
     * Returns the list of future meetings scheduled with this contact.
     *
     * If there are none, the returned list will be empty. Otherwise, the list will be chronologically sorted and will
     * not contain any duplicates.
     *
     * @param contact one of the users contacts
     * @return the list of future meeting(s) scheduled with this contact (maybe empty).
     * @throws IllegalArgumentException if the contact does not exist
     * @throws NullPointerException     if the contact is null
     */
    @Override
    public List<Meeting> getFutureMeetingList(Contact contact) {
        /**
         * Check the contact exists; if it doesn't throw an IllegalArgumentException
         * Check the contact is not null; if it is throw a NullPointerException
         * Create an empty list which will hold any future meetings
         * Check if there are any future meetings scheduled with this contact; if there are put them into the list
         * CHRONOLOGICALLY, checking for duplicate meetings before adding.
         * Two meetings are equal only if both their dates are equal and their sets of contacts are equal
         * Return the list
         */
        if (contact == null) {
            throw new NullPointerException("The contact you provided was null");
        }
        if (!validContact(contact)) {
            throw new IllegalArgumentException("The contact you provided does not exist in this Contact Manager");
        }
        Set<Meeting> nonChronologicalMeetings = new HashSet<>();
        for (Meeting m : allMeetings) {
            if (m instanceof FutureMeeting) {
                for (Contact c : m.getContacts()) {
                    if (c.getId() == contact.getId()) {
                        nonChronologicalMeetings.add(m);
                    }
                }
            }
        }
        List<Meeting> sortedMeetings = new ArrayList<>();
        for (Meeting m : nonChronologicalMeetings) {
            boolean containsDuplicate = false;
            for (Meeting n : sortedMeetings) {
                if (m.getId() == n.getId()) {
                    containsDuplicate = true;
                }
            }
            if(!containsDuplicate) {
                sortedMeetings.add(m);
            }
        }
        Collections.sort(sortedMeetings, (o1, o2) -> o1.getDate().compareTo(o2.getDate()));
        return sortedMeetings;
    }

    /**
     * Returns the list of meetings that are scheduled for, or that took place on, the specified date
     *
     * If there are none, the returned list will be empty. Otherwise, the list will be chronologically sorted and will
     * not contain any duplicates.
     *
     * @param date the date
     * @return the list of meetings
     * @throws NullPointerException if the date are null
     */
    @Override
    public List<Meeting> getMeetingListOn(Calendar date) {
        if (date == null) {
            throw new NullPointerException("Please check that date is not null");
        }
        List<Meeting> unsortedMeetings = new ArrayList<>();
        for (Meeting m : allMeetings) {
            if(m.getDate().equals(date)) {
                unsortedMeetings.add(m);
            }
        }
        List<Meeting> sortedMeetings = new ArrayList<>();
        for (Meeting u : unsortedMeetings) {
            boolean containsAlready = false;
            if (sortedMeetings.contains(u)) {
                containsAlready = true;
            }
            if (!containsAlready) {
                sortedMeetings.add(u);
            }
        }
        Collections.sort(sortedMeetings, (o1, o2) -> o1.getDate().compareTo(o2.getDate()));
        return sortedMeetings;
        /**
         * ((Check the date is valid))
         * Check the date is not null; if it is throw a NullPointerException
         * Create an empty list which will hold any meetings
         * Check for meetings scheduled on that date; if there are put them into the list CHRONOLOGICALLY, checking
         * for duplicate meetings before adding.
         * Two meetings are equal only if both their dates are equal and their sets of contacts are equal
         * ((What about PastMeetings, what if one has notes and other doesn't and you return the one without notes))
         * Return the list
         */
    }

    /**
     * Returns the list of past meetings in which this contact has participated.
     *
     * If there are none, the returned list will be empty. Otherwise, the list will be chronologically sorted and will
     * not contain any duplicates.
     *
     * @param contact one of the users contacts
     * @return the list of future meeting(s) scheduled with this contact (maybe empty).
     *
     * @throws IllegalArgumentException if the contact does not exist
     * @throws NullPointerException     if the contact is null
     */
    @Override
    public List<PastMeeting> getPastMeetingListFor(Contact contact) {
        /**
         * Check the contact is not null; if it is throw a NullPointerException
         * Check the contact exists; if it doesn't throw an IllegalArgumentException
         * Create an empty list which will hold any past meetings
         * Check if the contact was an attendee for any meetings that happened in the past. If contact was an attendee for any past
         * meetings, add the PastMeeting to the list CHRONOLOGICALLY, checking for duplicates before adding.
         * ((If a meeting happened in the past but is a FutureMeeting, convert it to a PastMeeting - not essential)).
         * Two meetings are equal only if both their dates are equal and their sets of contacts are equal, <can this prefer
         * to use one with notes over one without notes?>
         * Return the list.
         */
        if (contact == null) {
            throw new NullPointerException("Please make sure the contact is not null");
        }
        if(!validContact(contact)) {
            throw new IllegalArgumentException("That contact doesn't exist in this Contact Manager");
        }
        Set<PastMeeting> unsortedMeetings = new HashSet<>();
        for (Meeting m : allMeetings) {
            if (m instanceof PastMeeting) {
                for (Contact c : m.getContacts()) {
                    if (c.getId() == contact.getId()) {
                        unsortedMeetings.add((PastMeeting) m);
                    }
                }
            }
        }
        List<PastMeeting> sortedMeetings = new ArrayList<>();
        for (PastMeeting m : unsortedMeetings) {
            boolean containsDuplicate = false;
            for (PastMeeting s : sortedMeetings) {
                if (m.getId() == (s.getId())) {
                    containsDuplicate = true;
                }
            }
            if (!containsDuplicate) {
                sortedMeetings.add(m);
            }
        }
        Collections.sort(sortedMeetings, (o1, o2) -> o1.getDate().compareTo(o2.getDate()));
        return sortedMeetings;
    }

    /**
     * Create a new record for a meeting that took place in the past.
     *
     * @param contacts a list of participants
     * @param date     the date on which the meeting took place
     * @param text     messages to be added about the meeting.
     * @throws IllegalArgumentException if the list of contacts is empty, or any of the contacts does not exist
     * @throws NullPointerException     if any of the arguments is null
     */
    @Override
    public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
        /**
         * Check the date IS in the past. If it isn't; throw an IllegalArgumentException (forum)
         * Check the Set of contacts is not empty; if it is throw an IllegalArgumentException
         * Check that each the ID for each contact exists; if any don't straight away throw an IllegalArgumentException
         * Check that none of the arguments are null; if any are straight away throw a NullPointerException
         * Create a new PastMeeting with the given values.
         */
        if (contacts == null || date == null || text == null) {
            throw new NullPointerException("Please make sure nothing entered is null");
        }
        if (date.after(currentDate)) {
            throw new IllegalArgumentException("Date must be in the past");
        }
        if (contacts.isEmpty()) {
            throw new IllegalArgumentException("Set of contacts must not be empty");
        }
        if (!allContacts.containsAll(contacts)) {
            throw new IllegalArgumentException("All contacts have to exist already");
        }
        else {
            meetingId++;
            allMeetings.add(new PastMeetingImpl(meetingId, date, contacts, text));
        }
    }

    /**
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
     */
    @Override
    public PastMeeting addMeetingNotes(int id, String text) {
        /**
         * Check if the meeting ID exists; if it doesn't throw an IllegalArgumentException
         * Check the meeting has already taken place; if it hasn't throw an IllegalStateException
         * Check if the notes to be added are null; if they are throw a NullPointerException
         * Add the notes to the already existing notes (which may be empty)
         * When testing, need to make sure that if notes existed already, new notes were added to existing notes rather
         * than replacing them.
         * Return the PastMeeting with notes
         */
        if (text == null) {
            throw new NullPointerException("Notes to be added can't be null");
        }
        if (!validID(id)) {
            throw new IllegalArgumentException("There is no meeting corresponding to this ID");
        }
        if (getMeeting(id).getDate().after(currentDate)) {
            throw new IllegalStateException("The meeting you specified hasn't yet taken place");
        }
        PastMeeting temp = getPastMeeting(id);
        PastMeeting pastMeetingPlusNotes;
        //Using addMeetingNotes on a PastMeeting which already has notes will not overwrite the old notes, but add the new notes to the old ones.
        pastMeetingPlusNotes = new PastMeetingImpl(id, temp.getDate(), temp.getContacts(), temp.getNotes() + text);
        allMeetings.add(pastMeetingPlusNotes);
        allMeetings.remove(temp);
        return pastMeetingPlusNotes;
    }

    /**
     * Create a new contact with the specified name and notes.
     *
     * @param name  the name of the contact.
     * @param notes notes to be added about the contact.
     * @return the ID for the new contact
     * @throws IllegalArgumentException if the name or the notes are empty strings
     * @throws NullPointerException     if the name or the notes are null
     */
    @Override
    public int addNewContact(String name, String notes) {
        /**
         *
         * Check if the name or notes are empty strings; if either are throw an IllegalArgumentException
         * Check if the name or notes are null; if either are throw a NullPointerException
         * There should be an int field for contactID. This should start at 0, and be incremented by 1 at method start,
         * before being used.
         * Create a new contact with the current value of contactID (which we increased by 1 at the start of the method),
         * with the provided values for name and notes.
         * Return a copy of the current value of contactID.
         */
        if (name == null || notes == null) {
            throw new NullPointerException("Please make sure neither name or notes are null");
        }
        else if (name.equals("") || notes.equals("")) {
            throw new NullPointerException("Neither name nor notes can be empty");
        }
        contactId++;
        allContacts.add(new ContactImpl(contactId, name, notes));
        //this should be a copy rather than a reference to.
        int result = contactId;
        return result;
    }

    /**
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
     */
    @Override
    public Set<Contact> getContacts(String name) {
        /**
         * Check the provided name is not null; if it is throw a NullPointerException
         * Create an empty set.
         * If the string is "", assign the set containing all current contacts to the empty set.
         * If not, look through each contact in turn and check if their name contains the provided string. If it does,
         * add it to the Set.
         * Don't plan to make this lenient with upper/lowercase
         * Return the set
         */
        if (name == null) {
            throw new NullPointerException("Please make sure name is not null");
        }
        else if (name.equals("")) {
            return allContacts;
        }
        Set<Contact> resultSet = new HashSet<>();
            for (Contact c : allContacts) {
                if (c.getName().contains(name)) {
                    resultSet.add(c);
                }
            }
        return resultSet;
    }

    /**
     * Returns a Set containing the contacts that correspond to the IDs.
     * Note that this method can be used to retrieve just one contact by passing only one ID.
     *
     * @param ids an arbitrary number of contact IDs
     * @return a list containing the contacts that correspond to the IDs. - a set of contacts: Set<Contact>
     * @throws IllegalArgumentException if no IDs are provided or if any of the provided IDs does not correspond to a
     * real contact
     */
    @Override
    public Set<Contact> getContacts(int... ids) {
        if (ids.length < 1) {
            throw new IllegalArgumentException("You must provide at least one ID");
        }
        int totalValid = 0;
        for (Contact c : allContacts) {
            for (int i : ids) {
                if (c.getId() == i) {
                    totalValid++;
                }
            }
        }
        if (totalValid != ids.length) {
            throw new IllegalArgumentException("All IDs you provide must correspond to a real contact");
        }
        Set<Contact> resultSet = new HashSet<>();
        for (int i : ids) {
            for (Contact c: allContacts) {
                if (c.getId() == i) {
                    resultSet.add(c);
                }
            }
        }
        return resultSet;
    }

    public boolean validID(int id) {
        boolean validID = false;
        for (Meeting m : allMeetings) {
            if (m.getId() == id) {
                validID = true;
            }
        }
        return validID;
    }

    public boolean validContact(Contact contact) {
        boolean validContact = false;
        for (Contact c : allContacts) {
            if (c.getId() == contact.getId()) {
                validContact = true;
            }
        }
        return validContact;
    }


    /**
    Collections.sort(nonChronologicalMeetings, new Comparator<Meeting>() {
        @Override
        public int compare(Meeting m1, Meeting m2) {
            return m1.getDate().compareTo(m2.getDate());
        }
    });
    */

    /**
     * Save all data to disk.
     * This method must be executed when the program is closed and when/if the user requests it.
     */
    //testing:call flush and check stuff written on the outside is the same as written on the inside
    @Override
    public void flush() {

    }
}
