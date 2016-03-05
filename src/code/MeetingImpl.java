package code;

import java.util.*;
/**
 * The class implementing this interface must be abstract. It must have only one
 * constructor with three parameters: an ID (int), a date, and a set of contacts that
 * must be non-empty (otherwise, an IllegalArgumentException must be thrown).
 * A IllegalArgumentException must also be thrown in the case the ID provided
 * was non-positive or zero. If any of the references/pointers passed as parameters
 * is null, a NullPointerException must be thrown
 *
 * Meetings have unique IDs, scheduled date and a list of participating contacts
 */

/**
 * @author Jade Dickinson jdicki04
 */

public /**abstract*/ class MeetingImpl implements Meeting {
    private int id;
    private Calendar date;
    private Set<Contact> contacts;

    public MeetingImpl(int id, Calendar date, Set<Contact> contacts) {
        /**
         * What null references/pointers could be passed in as parameters?
         * Not int id (default value 0)/Not Calendar date (a new GregorianCalendar has date and time set to now)
         * Not the actual Set. Actually, perhaps it could.
         * Possibly a contact's name
         * Possibly a contact's notes
         */
        if (id <= 0) {
            throw new IllegalArgumentException("Meeting ID must be greater than 0");
        }
        else if (contacts.isEmpty()) {
            throw new IllegalArgumentException("Please make sure the set of contacts is not empty");
        }
        else if (date == null) {
            throw new NullPointerException("Please ensure date is not null");
        }
        else {
            /**Any individual contact's name or notes are null (What about no notes) */
            for (Contact contact : contacts) {
                if (contact.getName() == null || contact.getNotes() == null) {
                    throw new NullPointerException("Make sure none of the contacts have names or notes that are null");
                }
            }
            this.id = id;
            this.date = date;
            this.contacts = contacts;
        }
    }

    /**
     * @return the id of the meeting.
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * @return the date of the meeting.
     */
    @Override
    public Calendar getDate() {
        return date;
    }

    /**
     * The list contains a minimum of one contact (if there were
     * just two people: the user and the contact) and may contain an
     * arbitrary number of them.
     *
     * @return the details of people that attended the meeting.
     */
    @Override
    public Set<Contact> getContacts() {
        return contacts;
    }
}