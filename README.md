### Programming in Java - Coursework 3 - Contact Manager
#### ContactManagerImpl
* See below for details of methods I've created, **validID(), validContact(), changeFutureMeetingToPast(), processMeetings()** and **processMeetingsForLists()**.
* Both Keith and Sergio stated on forum that if a FutureMeeting that's now in the past is noticed, we don't have to consider it as/convert it to a PastMeeting.
* I have opted to do so for **getPastMeeting, getFutureMeeting, getFutureMeetingList** and **getPastMeetingListFor**.
* getPastMeeting and getFutureMeeting use my method processMeetings which checks the list of meetings and then uses my method changeFutureMeetingToPast (see below) for the conversion.
* addMeetingNotes does the conversion, as per the spec. If necessary it uses changeFutureMeetingToPast (see below).
* getFutureMeetingList and getPastMeetingListFor will convert a FutureMeeting now in the past to a PastMeeting, using my method processMeetingsForLists.

#### New methods
##### validID(int id)
* I created this method to check if a meeting ID provided exists in the set of all meetings.
* Returns true if the ID provided has a corresponding Meeting in the ContactManager
* Used by getPastMeeting, getFutureMeeting, getMeeting and addMeetingNotes.

##### validContact(Contact contact)
* I created this method to check if a contact exists in this contact manager.
* Returns true if the contact provided exists in the ContactManager
* Used by getFutureMeetingList and getPastMeetingListFor.

##### changeFutureMeetingToPast(Meeting m)
* I created this method to convert a FutureMeeting that's now in the past to a PastMeeting with the same ID, date and contacts but also the empty string for notes.
* The change is made to the meeting in the set of all the ContactManager's meetings.
* The method then returns the created PastMeeting, though getFutureMeeting doesn't need the output.
* Used by getPastMeeting, getFutureMeeting and addMeetingNotes.

##### processMeetings()
* I created this method for other methods to use at start to check all existing meetings and use changeFutureMeetingToPast on any FutureMeetings that require it, due to now being in the past.
* Used by getPastMeeting and getFutureMeeting.

##### processMeetingsForLists()
* I created this method for methods returning lists to use. For these, it checks all existing meetings and use addMeetingNotes (providing empty string as notes) on any FutureMeetings that require it, due to now being in the past.
* Used by getFutureMeetingList and getPastMeetingList.

--------------

##### getPastMeeting(int id)
* Sergio [stated we should match the spec](https://moodle.bbk.ac.uk/mod/forum/discuss.php?d=47881) and throw an IllegalStateException.
* If a FutureMeeting exists with the ID provided that's now in the past, this will convert it to a PastMeeting with the empty string as notes.

##### getFutureMeeting(int id)
* Uses processMeetings before the bulk of the method (see above)
* If the ID matches a meeting that was a FutureMeeting before the method but is now in the past, this will have been converted, so an IllegalArgumentException protesting that the meeting has already happened will be thrown.

##### getFutureMeetingList(Contact contact)
* Uses processMeetingsForLists before the bulk of the method (see above)

##### getPastMeetingListFor(Contact contact)
* Uses processMeetingsForLists before the bulk of the method (see above)
* Sergio [stated meetings are considered equal](https://moodle.bbk.ac.uk/mod/forum/discuss.php?d=53251) if and only if their IDs are equal.

##### addMeetingNotes(int id, String text)
* If using addMeetingNotes on a PastMeeting which already has notes, even though the specification doesn't state specifically what to do, I've opted to add the notes provided to the old notes, rather than overwriting them. Sergio [implied this should be done](https://moodle.bbk.ac.uk/mod/forum/discuss.php?d=47554).