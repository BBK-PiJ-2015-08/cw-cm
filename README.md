# Programming in Java - Coursework 3 - Contact Manager
Note about ContactManagerImpl - addMeetingNotes(int id, String text)
* If using addMeetingNotes on a PastMeeting which already has notes, even though the specification doesn't state specifically what to do in this case, I have opted to add the notes the user provides to the end of the old notes, rather than overwriting the old notes.
Note about getPastMeetingListFor(Contact contact)
* Sergio stated on forum that meetings are considered equal if and only if their IDs are equal.
* I had previously based equality on all of date, contacts and notes matching (see ContactManagerImpl commit 6869a6d)
* I adjusted this to base equality of meetings only on their having the same ID.