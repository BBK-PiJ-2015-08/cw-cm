# Programming in Java - Coursework 3 - Contact Manager
General comments
--------------
* Both Keith and Sergio stated on forum that if a FutureMeeting that's now in the past is noticed, we don't have to convert it to a PastMeeting.

Note about ContactManagerImpl - addMeetingNotes(int id, String text)
--------------
* If using addMeetingNotes on a PastMeeting which already has notes, even though the specification doesn't state specifically what to do in this case, I have opted to add the notes the user provides to the end of the old notes, rather than overwriting the old notes.

Note about getPastMeetingListFor(Contact contact)
--------------
* Sergio [stated on forum](https://moodle.bbk.ac.uk/mod/forum/discuss.php?d=53251) that meetings are considered equal if and only if their IDs are equal.
* I had previously based equality on all of date, contacts and notes matching (see ContactManagerImpl commit [6869a6d](https://github.com/BBK-PiJ-2015-08/cw-cm/commit/6869a6d0627d03b75464a92ab7e9da4de8478ef0))
* I adjusted this to base equality of meetings only on their having the same ID.