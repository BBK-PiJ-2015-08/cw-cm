package code;

import java.util.*;
/**
 * The class implementing this interface must have only one constructor with four
 * parameters: an ID (int), a date, a set of contacts that must be non-empty
 * (otherwise, an IllegalArgumentException must be thrown), and a String containing
 * the notes for the meeting. If any of the references / pointers passed as
 * parameters is null, a NullPointerException must be thrown.
 *
 * A meeting that was held in the past.
 * It includes your notes about what happened and what was agreed.
 */

/**
 * @author Jade Dickinson jdicki04
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {

    private String notes;

    public PastMeetingImpl(int id, Calendar date, Set<Contact> contacts, String notes) {
        super(id, date, contacts);
        if (notes == null) {
            throw new NullPointerException("Please make sure to enter notes that are not null");
        } else {
            this.notes = notes;
        }
    }

    /**
     * Returns the notes from the meeting.
     * <p>
     * If there are no notes, the empty string is returned.
     *
     * @return the notes from the meeting.
     */
    @Override
    public String getNotes() {
        return notes;
    }

    /**
     * Methods extended from MeetingImpl:
     * public int getId() {return the id of the meeting}
     * public Calendar getDate() {return the date of the meeting}
     * public Set<Contact> getContacts(){return the details of the people that attended the meeting}
     */

}
