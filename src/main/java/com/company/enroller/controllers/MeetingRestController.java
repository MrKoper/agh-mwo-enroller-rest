package com.company.enroller.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.enroller.model.Meeting;
import com.company.enroller.model.Participant;
import com.company.enroller.persistence.MeetingService;

@RestController
@RequestMapping("/meetings")
public class MeetingRestController {

	@Autowired
	MeetingService meetingService;
	

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getMeeting() {
		Collection<Meeting> meetings = meetingService.getAll();
		return new ResponseEntity<Collection<Meeting>>(meetings, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getMeeting(@PathVariable("id") Long id) {
		Meeting meeting = meetingService.findById(id);
		if (meeting == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> addMeeting(@RequestBody Meeting meeting) {
		if (meetingService.findById(meeting.getId()) != null) {
			return new ResponseEntity("Meeting with id: " + meeting.getId() + " exists", HttpStatus.CONFLICT);
		}
		meetingService.addMeeting(meeting);
		return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
	}
	
//	 @RequestMapping(value = "/{id}/participants", method = RequestMethod.POST)
//	 public ResponseEntity<?> addParticipantToMeeting(@PathVariable("id")int id, @RequestBody Participant newParticipant) {
//		 pobrać spotkanie
//		 pobrać uczestnika
//		 
//		 dodać uczestnika do spotkania
//
//	}
//	
//	 @RequestMapping(value = "/{id}/participants/{participantid", method = RequestMethod.DELETE)
//	 public ResponseEntity<?> deleteParticipant(@PathVariable("id") String login) {
//		 Participant participant = participantService.findByLogin(login);
//	     if (participant  == null) {
//	    	 return new ResponseEntity(HttpStatus.NOT_FOUND);
//	     }
//	 	 participantService.delete(participant);
//	 	 return new ResponseEntity<Participant>(participant, HttpStatus.OK);
//	}
//	 
//	 @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//	 public ResponseEntity<?> update(@PathVariable("id") String login,@RequestBody Participant updateParticipant) {
//		 Participant participant = participantService.findByLogin(login);
//	     if (participant == null) {
//	    	 return new ResponseEntity(HttpStatus.NOT_FOUND);
//	     }
//	     participant.setPassword(updateParticipant.getPassword());
//	 	 participantService.update(participant);
//	 	 return new ResponseEntity<Participant>(participant, HttpStatus.OK);
//	}
	 
	 
}
