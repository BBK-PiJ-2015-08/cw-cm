package code;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;
/**
 * @see Meeting
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
