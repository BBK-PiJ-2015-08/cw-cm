package code;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Jade Dickinson jdicki04
 */
public class ContactManagerImpl implements ContactManager {
    private static final String FILENAME = "contacts.txt";
    private int meetingId;
    private int contactId;
    private Calendar currentDate;
    private List<Meeting> allMeetings;
    private Set<Contact> allContacts;

    public ContactManagerImpl() {
        currentDate = Calendar.getInstance();
        File file = new File(FILENAME);
        if (file.exists() && file.length() > 0) {
            ObjectInputStream fromFile = null;
            try {
                fromFile = new ObjectInputStream(
                        new BufferedInputStream(
                                new FileInputStream(FILENAME)));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                if (fromFile != null) {
                    allMeetings = (List<Meeting>) fromFile.readObject();
                    allContacts = (Set<Contact>) fromFile.readObject();
                    meetingId = (int) fromFile.readObject();
                    contactId = (int) fromFile.readObject();
                }
            } catch (IOException | ClassNotFoundException ex2) {
                ex2.printStackTrace();
            }
            try {
                if (fromFile != null) {
                    fromFile.close();
                }
            } catch (IOException ex3) {
                ex3.printStackTrace();
            }
        } else {
            /**
             * The below should happen both when file didn't originally exist
             * and if file.length() = 0
             */
            allMeetings = new ArrayList<>();
            allContacts = new HashSet<>();
            meetingId = 0;
            contactId = 0;
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
     * @see ContactManager
     */
    @Override
    public int addFutureMeeting(Set<Contact> contacts, Calendar date) {
        /**
         * There should be an int field for meetingID. This should be the
         * current size of the list of all meetings, plus 1. I.e. if no meetings
         * exist, meetingID will be 1, 1 meeting, meetingID will be 2, etc.
         */
        currentDate = Calendar.getInstance();
        if (contacts == null || date == null) {
            throw new NullPointerException("Ensure date and set of contacts aren't null");
        } else if (date.before(currentDate) || date.equals(currentDate)) {
            throw new IllegalArgumentException("Date can't be in the past");
        } else if (!allContacts.containsAll(contacts)) {
            throw new IllegalArgumentException("All contacts must already exist");
        } else {
            meetingId = allMeetings.size() + 1;
            allMeetings.add(new FutureMeetingImpl(meetingId, date, contacts));
            return meetingId;
        }
    }

    /**
     * @see ContactManager
     */
    @Override
    public PastMeeting getPastMeeting(int id) {
        processMeetings();
        PastMeeting thisMeetingOrNull = null;
        if (validID(id)) {
            for (Meeting m : allMeetings) {
                if (m.getId() == id) {
                    if (!m.getDate().before(currentDate) && !m.getDate().equals(currentDate)) {
                        throw new IllegalStateException("Meeting date must be in past");
                    }
                    if (m instanceof PastMeeting) {
                        thisMeetingOrNull = (PastMeeting) m;
                    }
                }
            }
        }
        return thisMeetingOrNull;
    }

    /**
     * @see ContactManager
     */
    @Override
    public FutureMeeting getFutureMeeting(int id) {
        processMeetings();
        FutureMeeting thisMeetingOrNull = null;
        if (validID(id)) {
            for (Meeting m : allMeetings) {
                if (m.getId() == id) {
                    if (m instanceof PastMeeting) {
                        throw new IllegalArgumentException("Meeting has already happened");
                    } else {
                        thisMeetingOrNull = (FutureMeeting) m;
                    }
                }
            }
        }
        return thisMeetingOrNull;
    }

    /**
     * @see ContactManager
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
     * @see ContactManager
     */
    @Override
    public List<Meeting> getFutureMeetingList(Contact contact) {
        /**
         * Two meetings are equal if and only if their IDs are equal.
         * If this finds a FutureMeeting that's now passed, it will convert it
         * in the main list of allMeetings, and thus it will ignore it when
         * adding FutureMeetings to the list this method returns.
         */
        processMeetingsForLists();
        if (contact == null) {
            throw new NullPointerException("The contact you provided was null");
        }
        if (!validContact(contact)) {
            throw new IllegalArgumentException("Contact isn't in this Contact Manager");
        }
        Set<Meeting> unsortedMeetings = new HashSet<>();
        for (Meeting m : allMeetings) {
            if (m instanceof FutureMeeting) {
                for (Contact c : m.getContacts()) {
                    if (c.getId() == contact.getId()) {
                        unsortedMeetings.add(m);
                    }
                }
            }
        }
        List<Meeting> sortedMeetings = new ArrayList<>();
        for (Meeting m : unsortedMeetings) {
            boolean containsDuplicate = false;
            for (Meeting n : sortedMeetings) {
                if (m.getId() == n.getId()) {
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
     * @see ContactManager
     */
    @Override
    public List<Meeting> getMeetingListOn(Calendar date) {
        if (date == null) {
            throw new NullPointerException("Please ensure date is not null");
        }
        List<Meeting> unsortedMeetings = new ArrayList<>();
        for (Meeting m : allMeetings) {
            if (m.getDate().equals(date)) {
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
    }

    /**
     * @see ContactManager
     * If this finds a FutureMeeting that's now passed, it will convert it
     * in the main list of allMeetings, and thus it will include it when adding
     * PastMeetings to the list this method returns.
     */
    @Override
    public List<PastMeeting> getPastMeetingListFor(Contact contact) {
        processMeetingsForLists();
        if (contact == null) {
            throw new NullPointerException("Please ensure contact is not null");
        }
        if (!validContact(contact)) {
            throw new IllegalArgumentException("Contact isn't in this Contact Manager");
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
                if (m.getId() == s.getId()) {
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
     * @see ContactManager
     */
    @Override
    public void addNewPastMeeting(Set<Contact> contacts, Calendar date, String text) {
        /**
         * Check the date IS in the past. If it isn't; throw an
         * IllegalArgumentException (forum)
         */
        currentDate = Calendar.getInstance();
        if (contacts == null || date == null || text == null) {
            throw new NullPointerException("Ensure nothing entered is null");
        }
        if (date.after(currentDate) && !date.equals(currentDate)) {
            throw new IllegalArgumentException("Date must be in the past");
        }
        if (contacts.isEmpty()) {
            throw new IllegalArgumentException("Set of contacts mustn't be empty");
        }
        if (!allContacts.containsAll(contacts)) {
            throw new IllegalArgumentException("All contacts must exist already");
        } else {
            meetingId = allMeetings.size() + 1;
            allMeetings.add(new PastMeetingImpl(meetingId, date, contacts, text));
        }
    }

    /**
     * @see ContactManager
     */
    @Override
    public PastMeeting addMeetingNotes(int id, String text) {
        currentDate = Calendar.getInstance();
        if (text == null) {
            throw new NullPointerException("Notes to be added can't be null");
        }
        if (!validID(id)) {
            throw new IllegalArgumentException("This ID matches no meeting");
        }
        if (getMeeting(id).getDate().after(currentDate)) {
            throw new IllegalStateException("That meeting hasn't yet happened");
        }
        PastMeeting pastPlusNotes;
        if (getMeeting(id) instanceof FutureMeeting) {
            pastPlusNotes = changeFutureMeetingToPast(getMeeting(id));
            addMeetingNotes(id, text);
        } else {
            PastMeeting temp = getPastMeeting(id);
            pastPlusNotes = new PastMeetingImpl(id, temp.getDate(), temp.getContacts(), temp.getNotes() + text);
            allMeetings.add(pastPlusNotes);
            allMeetings.remove(temp);
        }
        return pastPlusNotes;
    }

    /**
     * @see ContactManager
     */
    @Override
    public int addNewContact(String name, String notes) {
        /**
         * There'll be an int field for contactID. This should be the current
         * size of the Set of all contacts, plus 1. When creating a new contact,
         * the ID will be the current value of contactID.
         */
        if (name == null || notes == null) {
            throw new NullPointerException("Ensure name and notes aren't null");
        } else if (name.equals("") || notes.equals("")) {
            throw new IllegalArgumentException("Ensure name & notes aren't empty");
        }
        contactId = allContacts.size() + 1;
        allContacts.add(new ContactImpl(contactId, name, notes));
        int result = contactId;
        return result;
    }

    /**
     * @see ContactManager
     * If the string is the empty string, this methods returns the set that
     * contains all current contacts.
     */
    @Override
    public Set<Contact> getContacts(String name) {
        //Don't plan to make this lenient with upper/lowercase. If want to, could use toLowerCase()
        if (name == null) {
            throw new NullPointerException("Please make sure name is not null");
        } else if (name.equals("")) {
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
     * @see ContactManager
     */
    @Override
    public Set<Contact> getContacts(int... ids) {
        if (ids.length < 1) {
            throw new IllegalArgumentException("Please provide at least one ID");
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
            throw new IllegalArgumentException("At least 1 ID didn't match a contact");
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
     * Checks if a meeting ID provided exists in the set of all meetings.
     * Used by getPastMeeting, getFutureMeeting, getMeeting and addMeetingNotes
     * @param id The meeting ID which is being checked for having a
     *           corresponding Meeting in the ContactManager
     * @return A boolean value, true if the ID provided matches a Meeting in the
     * ContactManager
     */
    private boolean validID(int id) {
        boolean validID = false;
        for (Meeting m : allMeetings) {
            if (m.getId() == id) {
                validID = true;
            }
        }
        return validID;
    }

    /**
     * Checks if a contact exists in this contact manager.
     * Used by getFutureMeetingList and getPastMeetingListFor
     * @param contact A single contact whose existence in the ContactManager is
     *                being checked.
     * @return A boolean value, true if the contact provided exists in the
     * ContactManager's set of all contacts.
     */
    private boolean validContact(Contact contact) {
        boolean validContact = false;
        for (Contact c : allContacts) {
            if (c.getId() == contact.getId()) {
                validContact = true;
            }
        }
        return validContact;
    }

    /**
     * Used to convert a FutureMeeting that's now in the past to a PastMeeting
     * with no notes, and returns this. Checking whether a FutureMeeting should
     * be a PastMeeting is not done by this method.
     * Used by getPastMeeting, getFutureMeeting and addMeetingNotes
     * @param m A meeting that exists as a FutureMeeting but should now be a
     *          past meeting
     * @return A PastMeeting with the same ID, date and contacts as the meeting
     * entered as well as empty string for notes.
     * @throws IllegalArgumentException if the meeting hasn't yet happened
     */
    private PastMeeting changeFutureMeetingToPast(Meeting m) {
        if (m.getDate().after(currentDate)) {
            throw new IllegalArgumentException("Ensure meeting provided is now or has already happened");
        }
        FutureMeeting temp = (FutureMeeting) getMeeting(m.getId());
        PastMeeting nowPastMeeting = new PastMeetingImpl(m.getId(), temp.getDate(), temp.getContacts(), "");
        allMeetings.add(nowPastMeeting);
        allMeetings.remove(temp);
        return nowPastMeeting;
    }

    /**
     * Used to check all the existing meetings and use addMeetingNotes on any
     * FutureMeetings that require it, due to now being in the past.
     * Used by getPastMeeting and getFutureMeeting.
     */
    private void processMeetings() {
        currentDate = Calendar.getInstance();
        for (Meeting m : allMeetings) {
            if (m instanceof FutureMeeting && (m.getDate().before(currentDate) || m.getDate().equals(currentDate))) {
                addMeetingNotes(m.getId(), "");
            }
        }
    }

    /**
     * A method used by methods returning lists, to check all existing meetings
     * and use addMeetingNotes on any FutureMeetings that require it, due to now
     * being in the past.
     */
    private void processMeetingsForLists() {
        int limit = allMeetings.size();
        for (int i = 0; i < limit; i++) {
            Meeting m = allMeetings.get(i);
            if (m instanceof FutureMeeting && (m.getDate().before(currentDate) || m.getDate().equals(currentDate))) {
                addMeetingNotes(m.getId(), "");
            }
        }
    }

    /**
     * @see ContactManager
     * For testing:call flush and check stuff written on the outside is the same
     * as written on the inside
     */
    @Override
    public void flush() {
        ObjectOutputStream toFile = null;
        try {
            toFile = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(FILENAME)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            if (toFile != null) {
                toFile.writeObject(allMeetings);
                toFile.writeObject(allContacts);
                toFile.writeObject(meetingId);
                toFile.writeObject(contactId);
            }
        } catch (IOException ex2) {
            ex2.printStackTrace();
        }
        try {
            if (toFile != null) {
                toFile.close();
            }
        } catch (IOException ex3) {
            ex3.printStackTrace();
        }
    }
}
