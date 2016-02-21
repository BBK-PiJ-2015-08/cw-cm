package src;

import java.util.*;

public class ContactManagerImpl implements ContactManager {
    private Set<Contact> contacts;
    private List<Meeting> meetings;

    //Create fields
    //Create constructors
//Unless instructed otherwise, all classes must have only one constructor with no parameters.


    /**
     * Add a new meeting to be held in the future.
     * <p>
     * An ID is returned when the meeting is put into the system. This
     * ID must be positive and non-zero.
     *
     * @param contacts a list of contacts that will participate in the meeting
     * @param date     the date on which the meeting will take place
     * @return the ID for the meeting
     * @throws IllegalArgumentException if the meeting is set for a time
     *                     in the past, or if any contact is unknown / non-existent.
     * @throws NullPointerException     if the set of contacts or the date are null
     */
    @Override
    public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
        // @return the ID for the meeting
    }




    /**
     * Returns the PAST meeting with the requested ID, or null if it there is none.
     * <p>
     * The meeting must have happened at a past date.
     *
     * @param id the ID for the meeting
     * @return the meeting with the requested ID, or null if it there is none.
     * @throws IllegalStateException if there is a meeting with that ID happening
     *                               in the future
     */
    @Override
    public PastMeeting getPastMeeting(int id) {
        //@return the meeting with the requested ID, or null if it there is none.
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
        //@return the meeting with the requested ID, or null if it there is none.
    }




    /**
     * Returns the meeting with the requested ID, or null if it there is none.
     *
     * @param id the ID for the meeting
     * @return the meeting with the requested ID, or null if it there is none.
     */
    @Override
    public Meeting getMeeting(int id) {
        //@return the meeting with the requested ID, or null if it there is none.
    }




    /**
     * Returns the list of future meetings scheduled with this contact.
     * <p>
     * If there are none, the returned list will be empty. Otherwise,
     * the list will be chronologically sorted and will not contain any
     * duplicates.
     *
     * @param contact one of the users contacts
     * @return the list of future meeting(s) scheduled with this contact (maybe empty).
     * @throws IllegalArgumentException if the contact does not exist
     * @throws NullPointerException     if the contact is null
     */
    @Override
    public List<Meeting> getFutureMeetingList(Contact contact) {
        //@return the list of future meeting(s) scheduled with this contact (maybe empty).
    }




    /**
     * Returns the list of meetings that are scheduled for, or that took
     * place on, the specified date
     * <p>
     * If there are none, the returned list will be empty. Otherwise,
     * the list will be chronologically sorted and will not contain any
     * duplicates.
     *
     * @param date the date
     * @return the list of meetings
     * @throws NullPointerException if the date are null
     */
    @Override
    public List<Meeting> getMeetingListOn(Calendar date) {
        //@return the list of meetings
    }




    /**
     * Returns the list of past meetings in which this contact has participated.
     * <p>
     * If there are none, the returned list will be empty. Otherwise,
     * the list will be chronologically sorted and will not contain any
     * duplicates.
     *
     * @param contact one of the users contacts
     * @return the list of future meeting(s) scheduled with this contact (maybe empty).
     * @throws IllegalArgumentException if the contact does not exist
     * @throws NullPointerException     if the contact is null
     */
    @Override
    public List<PastMeeting> getPastMeetingListFor(Contact contact) {
        //@return the list of future meeting(s) scheduled with this contact (maybe empty).
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
        //no return value
    }




    /**
     * Add notes to a meeting.
     * <p>
     * This method is used when a future meeting takes place, and is
     * then converted to a past meeting (with notes) and returned.
     * <p>
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
        //no return value
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
        //return the ID for the new contact
    }



    /**
     * Returns a Set with the contacts whose name contains that string:
     * <p>
     * If the string is the empty string, this methods returns the set
     * that contains all current contacts.
     *
     * @param name the string to search for
     * @return a list with the contacts whose name contains that string. - a set of
     * contacts: Set<Contact>
     * @throws NullPointerException if the parameter is null
     */
    @Override
    public Set<Contact> getContacts(String name) {
        //@return a list with the contacts whose name contains that string.
    }




    /**
     * Returns a Set containing the contacts that correspond to the IDs.
     * Note that this method can be used to retrieve just one contact by passing only one ID.
     *
     * @param ids an arbitrary number of contact IDs
     * @return a list containing the contacts that correspond to the IDs. - a set of
     * contacts: Set<Contact>
     * @throws IllegalArgumentException if no IDs are provided or if
     *                                  any of the provided IDs does not correspond to a real contact
     */
    @Override
    public Set<Contact> getContacts(int... ids) {
        //@return a list containing the contacts that correspond to the IDs
    }



    
    /**
     * Save all data to disk.
     * <p>
     * This method must be executed when the program is
     * closed and when/if the user requests it.
     */
    @Override
    public void flush() {

    }
}
