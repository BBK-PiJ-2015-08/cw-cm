package code;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;
/**
 * The class implementing this interface must be abstract. It must have only
 * one constructor with three parameters: an ID (int), a date, and a set of
 * contacts that must be non-empty (otherwise, an IllegalArgumentException must
 * be thrown). A IllegalArgumentException must also be thrown in the case the
 * ID provided was non-positive or zero. If any of the references / pointers
 * passed as parameters is null, a NullPointerException must be thrown.
 * @author Jade Dickinson jdicki04
 */
public abstract class MeetingImpl implements Meeting, Serializable {
    private static final long serialVersionUID = -4145562617415606420L;
    private int id;
    private Calendar date;
    private Set<Contact> contacts;

    public MeetingImpl(int id, Calendar date, Set<Contact> contacts) {
        if (id <= 0) {
            throw new IllegalArgumentException("Meeting ID must be greater than 0");
        } else if (contacts.isEmpty()) {
            throw new IllegalArgumentException("Set of contacts can't be empty");
        } else if (date == null) {
            throw new NullPointerException("Please ensure date is not null");
        } else {
            //Check that no individual contact's name or notes are null
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
     * @see Meeting
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * @see Meeting
     */
    @Override
    public Calendar getDate() {
        return date;
    }

    /**
     * @see Meeting
     */
    @Override
    public Set<Contact> getContacts() {
        return contacts;
    }
}
