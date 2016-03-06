package code;

import java.util.*;
import java.io.*;

import java.beans.XMLEncoder;
import java.beans.XMLDecoder;

/**
 * @author Jade Dickinson jdicki04
 */
public class ContactManagerImpl implements ContactManager, Serializable {
    private Set<Contact> contacts;
    private List<Meeting> meetings;

    private static final String FILENAME = "contacts.txt";

    /**
     * Create fields
     */


    //Create constructor. One constructor with no parameters. Create body of it.
    public ContactManagerImpl() {
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
     * @throws IllegalArgumentException if the meeting is set for a time
     *                     in the past, or if any contact is unknown / non-existent.
     * @throws NullPointerException     if the set of contacts or the date are null
     */
    @Override
    public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
        //Field <- int <- id of meeting created. Unique. Start field at 0, increment by 1 at start of method. Use that.
        return 0;
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
         return a PastMeeting
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
         return a FutureMeeting
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
         * Check if the contact was an attendee for any past meetings; if they were add the PastMeeting to the list
         * CHRONOLOGICALLY, checking for duplicates before adding.
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
         * Check the date IS in the past. If it isn't; throw an IllegalArgumentException
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
         * Check
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
        return 0;
    }



    /**
     * Returns a Set with the contacts whose name contains that string:
     *
     * If the string is the empty string, this methods returns the set
     * that contains all current contacts.
     *
     * @param name the string to search for
     * @return a list with the contacts whose name contains that string. - a set of contacts: Set<Contact>
     *
     * @throws NullPointerException if the parameter is null
     */
    @Override
    public Set<Contact> getContacts(String name) {
        return null;
    }


    /**
     * Returns a Set containing the contacts that correspond to the IDs.
     * Note that this method can be used to retrieve just one contact by passing only one ID.
     *
     * @param ids an arbitrary number of contact IDs
     * @return a list containing the contacts that correspond to the IDs. - a set of contacts: Set<Contact>
     * @throws IllegalArgumentException if no IDs are provided or if any of the provided IDs does not correspond to a real contact
     */
    @Override
    public Set<Contact> getContacts(int... ids) {
        return null;
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
