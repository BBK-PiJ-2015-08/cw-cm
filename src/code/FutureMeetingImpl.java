package code;

import java.util.*;
/**
 * The class implementing this interface must have only one constructor with three
 * parameters: an ID (int), a date, and a set of contacts that must be non-empty
 * (otherwise, an IllegalArgumentException must be thrown). If any of the
 * references/pointers passed as parameters is null, a NullPointerException must be
 * thrown.
 */

/**
 * @author Jade Dickinson jdicki04
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {
    public FutureMeetingImpl(int id, Calendar date, Set<Contact> contacts) {
        super(id, date, contacts);
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
