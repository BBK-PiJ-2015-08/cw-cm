package code; /**
 * The implementation of this interface must have two constructors. The most general
 * constructor must have three parameters: int, String, String. The first one
 * corresponds to the ID provided by the code.ContactManager, the next one corresponds
 * to the name, and the last one corresponds to the initial set of notes about the
 * contact. Another, more restricted constructor must have two parameters only:
 * ID and name. If the ID provided is zero or negative, a IllegalArgumentException
 * must be thrown. If any of the references / pointers passed as parameters to the
 * constructor is null, a NullPointerException must be thrown.
 */

/**
 * @author Jade Dickinson jdicki04
 */
public class ContactImpl implements Contact {
    private int id;
    private String name;
    private String notes;

    /**If the ID provided is zero or negative, a IllegalArgumentException
     * must be thrown. If any of the references / pointers passed as parameters to the
     * constructor is null, a NullPointerException must be thrown.
     * */
    public ContactImpl(int id, String name, String notes) {
        if (id <= 0) {
            throw new IllegalArgumentException("Contact ID must be greater than 0");
        }
        else if (name == null || notes == null) {
            throw new NullPointerException("Please make sure neither name or notes are null");
        }
        else {
            this.id = id;
            this.name = name;
            this.notes = notes;
        }
    }



    /**If the ID provided is zero or negative, a IllegalArgumentException
     * must be thrown. If any of the references / pointers passed as parameters to the
     * constructor is null, a NullPointerException must be thrown.
     * */
    public ContactImpl(int id, String name) {
        if (id <= 0) {
            throw new IllegalArgumentException("Contact ID must be greater than 0");
        }
        else if (name == null) {
            throw new NullPointerException("Please make sure that name is not null");
        }
        else {
            this.id = id;
            this.name = name;
            this.notes = "";
        }
    }


    /**
     * Returns the ID of the contact.
     *
     * @return the ID of the contact.
     */
    @Override
    public int getId() {
        return id;
    }




    /**
     * Returns the name of the contact.
     *
     * @return the name of the contact.
     */
    @Override
    public String getName() {
        return name;
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
        return notes;
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
