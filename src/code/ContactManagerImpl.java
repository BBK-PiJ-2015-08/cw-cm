package code;

import java.util.*;
import java.io.*;
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
            //iterate through contacts and print out
            System.out.println("From contacts: ");
            for (Contact contact : contacts) {
                System.out.println("ID: " + contact.getId() + " Name: " + contact.getName() + " Notes: "+ contact.getNotes());
            }
            System.out.println("From allcontacts: ");
            for (Contact contact : allContacts) {
                System.out.println("ID: " + contact.getId() + " Name: " + contact.getName() + " Notes: "+ contact.getNotes());
            }
            System.out.println("allContacts does not contain all of the elements of contacts");
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
     *
     for (Contact contact : contacts) {
     if (contact.getName() == null || contact.getNotes() == null) {
     throw new NullPointerException("Make sure none of the contacts have names or notes that are null");
     }
     }
     */



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
        return null;
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
        return null;
    }




    /**
     * Returns the meeting with the requested ID, or null if it there is none.
     *
     * @param id the ID for the meeting
     * @return the meeting with the requested ID, or null if it there is none.
     */
    @Override
    public Meeting getMeeting(int id) {
        /**
         * Check the ID exists; if it doesn't return null
         * Otherwise, return the meeting which corresponds to this ID (could be in the past or future)
         */
        return null;
    }




    /**
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
         * Check the contact ID exists; if it doesn't throw an IllegalArgumentException
         * Check the contact is not null; if it is throw a NullPointerException
         * Create an empty list which will hold any future meetings
         * Check if there are any future meetings scheduled with this contact; if there are put them into the list
         * CHRONOLOGICALLY, checking for duplicate meetings before adding.
         * Two meetings are equal only if both their dates are equal and their sets of contacts are equal
         * Return the list
         */
        return null;
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
        return null;
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
         * Check the contact ID exists; if it doesn't throw an IllegalArgumentException
         * Check the contact is not null; if it is throw a NullPointerException
         * Create an empty list which will hold any past meetings
         * Check if the contact was an attendee for any meetings that happened in the past. ((If a meeting happened in
         * the past but is a FutureMeeting, convert it to a PastMeeting)). If contact was an attendee for any past
         * meetings, add the PastMeeting to the list
         * CHRONOLOGICALLY, checking for duplicates before adding.
         * Two meetings are equal only if both their dates are equal and their sets of contacts are equal, but prefer
         * to use one with notes over one without notes.
         * Return the list.
         */
        return null;
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
        return null;
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
         * ((Seems odd that there isn't an addNewContact method with just String name, given that the more limited
         * constructor without notes does exist))
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
            System.out.println("From allContacts: ");
            for (Contact c : allContacts) {
                if (c.getName().contains(name)) {
                    resultSet.add(c);
                }
                //System.out.println("ID: " + contact.getId() + " Name: " + contact.getName() + " Notes: "+ contact.getNotes());
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
        if (totalValid != (ids.length)) {
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


    /**
     * Save all data to disk.
     * This method must be executed when the program is closed and when/if the user requests it.
     */
    //testing:call flush and check stuff written on the outside is the same as written on the inside
    @Override
    public void flush() {

    }
}
