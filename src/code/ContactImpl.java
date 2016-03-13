package code;

import java.io.Serializable;
/**
 * @see Contact
 * @author Jade Dickinson jdicki04
 */
public class ContactImpl implements Contact, Serializable {
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
     * @see Contact
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * @see Contact
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @see Contact
     */
    @Override
    public String getNotes() {
        return notes;
    }

    /**
     * @see Contact
     */
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
