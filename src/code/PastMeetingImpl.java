package code;

import java.util.*;
/**
 * The class implementing this interface must have only one constructor with four
 * parameters: an ID (int), a date, a set of contacts that must be non-empty
 * (otherwise, an IllegalArgumentException must be thrown), and a String containing
 * the notes for the meeting. If any of the references / pointers passed as
 * parameters is null, a NullPointerException must be thrown.
 */

/**
 * @author Jade Dickinson jdicki04
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {

    private String text;

    public PastMeetingImpl(int id, Calendar date, Set<Contact> contacts, String text) {
        super(id, date, contacts);
        if (text == null) {
            throw new NullPointerException("Please make sure to enter notes that are not null");
        } else {
            this.text = text;
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
        return null;
    }

    /**
     * Returns the id of the meeting.
     *
     * @return the id of the meeting.
     */
    @Override
    public int getId() {
        return 0;
    }

    /**
     * Return the date of the meeting.
     *
     * @return the date of the meeting.
     */
    @Override
    public Calendar getDate() {
        return null;
    }

    /**
     * Return the details of people that attended the meeting.
     * <p>
     * The list contains a minimum of one contact (if there were
     * just two people: the user and the contact) and may contain an
     * arbitrary number of them.
     *
     * @return the details of people that attended the meeting.
     */
    @Override
    public Set<Contact> getContacts() {
        return null;
    }
}
