# Programming in Java - Coursework 3 - Contact Manager
General comments
--------------
* Both Keith and Sergio stated on forum that if a FutureMeeting that's now in the past is noticed, we don't have to consider it as/convert it to a PastMeeting.
* I have opted to do so for getPastMeeting and getFutureMeeting.
* addMeetingNotes will do this, as per the spec.
* The above use my method changeFutureMeetingToPast for the conversion, avoiding duplicated code.
* getFutureMeetingList and getPastMeetingListFor will also convert a FutureMeeting now in the past; but both use a combination of a ListIterator and and Iterator to do so.

ContactManagerImpl: getPastMeeting(int id)
--------------
* Would assume that if a meeting happening with ID provided is happening in the future, this should throw an IllegalArgumentException as in getFutureMeeting; Sergio [stated we should match the spec](https://moodle.bbk.ac.uk/mod/forum/discuss.php?d=47881) and throw an IllegalStateException.
* If a FutureMeeting exists with the ID provided that's now in the past, this will convert it to a PastMeeting with the empty string as notes.

ContactManagerImpl: getPastMeetingListFor(Contact contact)
--------------
* Sergio [stated meetings are considered equal](https://moodle.bbk.ac.uk/mod/forum/discuss.php?d=53251) if and only if their IDs are equal.
* I had previously based equality on all of date, contacts and notes matching (see ContactManagerImpl commit [6869a6d](https://github.com/BBK-PiJ-2015-08/cw-cm/commit/6869a6d0627d03b75464a92ab7e9da4de8478ef0)).
* I adjusted this to base equality of meetings only on their having the same ID.

ContactManagerImpl: addMeetingNotes(int id, String text)
--------------
* If using addMeetingNotes on a PastMeeting which already has notes, even though the specification doesn't state specifically what to do, I've opted to add the notes provided to the old notes, rather than overwriting them. Sergio [implied this should be done](https://moodle.bbk.ac.uk/mod/forum/discuss.php?d=47554).
* If using addMeetingNotes on a FutureMeeting which is now in the past, the method will remove the FutureMeeting from the list and add a PastMeeting which has the attributes of the FutureMeeting as well as the notes provided as String text.

ContactManagerImpl: validID(int id)
--------------
* I created this method to check if a meeting ID provided exists in the set of all meetings.
* Returns true if the ID provided has a corresponding Meeting in the ContactManager
* Used by getPastMeeting, getFutureMeeting, getMeeting and addMeetingNotes.

ContactManagerImpl: validContact(Contact contact)
--------------
* I created this method to check if a contact exists in this contact manager.
* Returns true if the contact provided exists in the ContactManager
* Used by getFutureMeetingList and getPastMeetingListFor.

ContactManagerImpl: changeFutureMeetingToPast(Meeting m)
--------------
* I created this method to convert a FutureMeeting that's now in the past to a PastMeeting with the same ID, date and contacts but also the empty string for notes.
* The change is made to the meeting in the set of all the ContactManager's meetings.
* The method then returns the created PastMeeting, though getFutureMeeting doesn't need the output.
* Used by getPastMeeting, getFutureMeeting and addMeetingNotes.
* getFutureMeetingList and getPastMeetingListFor manage any needed conversions of Future to PastMeetings themselves.