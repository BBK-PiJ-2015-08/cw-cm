/**
 * @author Jade Dickinson jdicki04
 */
package test;

import code.Contact;
import code.ContactImpl;
import code.FutureMeeting;
import code.FutureMeetingImpl;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class FutureMeetingImplTest {
    private int testId;
    private Calendar testDate;
    private Set<Contact> testContacts;
    private FutureMeeting testFutureMeeting;

    @Before
    public void setUp() {
        /**
        testId = ;
        testDate = ;
        testContacts = ;
        */
        testFutureMeeting = new FutureMeetingImpl(testId, testDate, testContacts);
    }

    @Test
    public void testGetId() {

    }

    @Test
    public void testGetDate() {

    }

    @Test
    public void testGetContacts() {

    }
}
