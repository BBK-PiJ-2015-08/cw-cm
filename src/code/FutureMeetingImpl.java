package code;

import java.util.Calendar;
import java.util.Set;
/**
 * @see FutureMeeting
 * @author Jade Dickinson jdicki04
 */
public class FutureMeetingImpl extends MeetingImpl implements FutureMeeting {
    private static final long serialVersionUID = 2613046855889857597L;

    public FutureMeetingImpl(int id, Calendar date, Set<Contact> contacts) {
        super(id, date, contacts);
    }

}
