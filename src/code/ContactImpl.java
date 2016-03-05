package code;
/**
 * The implementation of this interface must have two constructors. The most general
 * constructor must have three parameters: int, String, String.
 * The first one
 * corresponds to the ID provided by the ContactManager, the next one corresponds
 * to the name, and the last one corresponds to the initial set of notes about the
 * contact.
 * Another, more restricted constructor must have two parameters only:
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

    public ContactImpl(int id, String name, String notes) {
        if (id <= 0) {
            throw new IllegalArgumentException("Contact ID must be greater than 0");
        }
        else if (name == null || notes == null) {
            throw new NullPointerException("Please make sure neither name or notes are null");
        }
        else if (name.equals("")) {
            throw new IllegalArgumentException("Name must not be empty");
        }
        else {
            this.id = id;
            this.name = name;
            this.notes = notes;
        }
    }




    public ContactImpl(int id, String name) {
        if (id <= 0) {
            throw new IllegalArgumentException("Contact ID must be greater than 0");
        }
        else if (name == null) {
            throw new NullPointerException("Please make sure that name is not null");
        }
        else if (name.equals("")) {
            throw new IllegalArgumentException("Name must not be empty");
        }
        else {
            this.id = id;
            this.name = name;
            this.notes = "";
        }
    }


    /**
     * @return the ID of the contact.
     */
    @Override
    public int getId() {
        return id;
    }




    /**
     * @return the name of the contact.
     */
    @Override
    public String getName() {
        return name;
    }




    /**
     * @return a string with notes about the contact, maybe empty if we have not written anything about the contact.
     */
    @Override
    public String getNotes() {
        return notes;
    }




    /**
     * @param note the notes to be added
     */
    //What if there's not already notes and note = null
    //What if there are already notes and note = null
    //What if there's not already notes and note = "" (the empty string)
    //What if there's already notes and note = "" (the empty string)
    //What if there's not already notes and note is a valid string
    @Override
    public void addNotes(String note) {
        if (note != null && !note.equals("")) {
            if (notes == null) {
                this.notes = note;
            }
            else {
                this.notes += note;
            }
        }
    }
}
