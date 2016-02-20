package src;

/**
 * The implementation of this interface must have two constructors. The most general
 * constructor must have three parameters: int, String, String. The first one
 * corresponds to the ID provided by the ContactManager, the next one corresponds
 * to the name, and the last one corresponds to the initial set of notes about the
 * contact. Another, more restricted constructor must have two parameters only:
 * ID and name. If the ID provided is zero or negative, a IllegalArgumentException
 * must be thrown. If any of the references / pointers passed as parameters to the
 * constructor is null, a NullPointerException must be thrown.
 */

public class ContactImpl implements Contact {

    public ContactImpl(int id, String name, String notes) {

    }

    public ContactImpl(int id, String name) {

    }



    /**
     * Returns the ID of the contact.
     *
     * @return the ID of the contact.
     */
    @Override
    public int getId() {

    }




    /**
     * Returns the name of the contact.
     *
     * @return the name of the contact.
     */
    @Override
    public String getName() {

    }




    /**
     * Returns our notes about the contact, if any.
     * <p>
     * If we have not written anything about the contact, the empty
     * string is returned.
     *
     * @return a string with notes about the contact, maybe empty.
     */
    @Override
    public String getNotes() {

    }




    /**
     * Add notes about the contact.
     *
     * @param note the notes to be added
     */
    @Override
    public void addNotes(String note) {

    }
}
