package code;

import java.util.Calendar;
import java.util.Set;
/**
 * @see PastMeeting
 *
 * @author Jade Dickinson jdicki04
 */
public class PastMeetingImpl extends MeetingImpl implements PastMeeting {
    private static final long serialVersionUID = -4388045743984839327L;
    
    private String text;

    public PastMeetingImpl(int id, Calendar date, Set<Contact> contacts, String text) {
        super(id, date, contacts);
        if (text == null) {
            throw new NullPointerException("Ensure notes that aren't null");
        } else {
            this.text = text;
        }
    }

    /**
     * @see PastMeeting
     */
    @Override
    public String getNotes() {
        return text;
    }

}
