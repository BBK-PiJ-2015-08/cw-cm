/**
 * The class implementing this interface must have only one constructor with four
 * parameters: an ID (int), a date, a set of contacts that must be non-empty
 * (otherwise, an IllegalArgumentException must be thrown), and a String containing
 * the notes for the meeting. If any of the references / pointers passed as
 * parameters is null, a NullPointerException must be thrown.
 */
public class PastMeetingImpl implements PastMeeting {
//create constructors
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
}
