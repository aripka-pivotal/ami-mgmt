package com.gopivotal.cf.workshop.web;

import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gopivotal.cf.workshop.entity.AmiDetails;
import com.gopivotal.cf.workshop.entity.Attendee;
import com.gopivotal.cf.workshop.repository.AMIDetailsRepository;
import com.gopivotal.cf.workshop.repository.AttendeeRepository;

@Controller
public class AMIManagementController {
	
	@Autowired
	AMIDetailsRepository amiDetailsRepo;
	
	@Autowired
	AttendeeRepository attendeeRepository;
	
	@RequestMapping(value="/amiLookup", method = RequestMethod.GET)
	public String getMyAmi(){
		return "amiLookup";
	}
	
	@RequestMapping(value="/amiDetails", method = RequestMethod.POST)
	public String amiDetails(@RequestParam(value="emailAddr") String emailAddr, Model model){
		
		Attendee attendee = attendeeRepository.findByEmailAddress(emailAddr);
		
		
		if(attendee == null || attendee.getAmiDetails()==null){
			model.addAttribute("emailAddr", attendee != null?attendee.getEmailAddress():emailAddr);
			return "noAMIFound";
		}
		
		//add a step to save if this has been claimed
		AmiDetails amiDetails = attendee.getAmiDetails();
		System.out.println(amiDetails.getCount());
		
		if (amiDetails.getCount() == null){
			amiDetails.setCount(1l);
		}else{
			amiDetails.setCount(amiDetails.getCount().longValue()+1);
		}
		
		amiDetailsRepo.save(amiDetails);
		
		model.addAttribute("attendee", attendee);
		

		
		return "amiDetails";
	}
	
	@RequestMapping(value="/addAttendee", method=RequestMethod.GET)
	public String addAttendee(){
		return "addAttendee";
	}
	
	@RequestMapping(value="/addAttendee", method=RequestMethod.POST)
	public String addAttendee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("emailAddress") String emailAddress){
		
		Attendee attendee = new Attendee();
		attendee.setFirstName(firstName);
		attendee.setLastName(lastName);
		attendee.setEmailAddress(emailAddress);
		
		attendeeRepository.save(attendee);
		
		return "attendeeAdded";
	}
	
	@RequestMapping("/amiAssignment")
	public String amiAssignment(Model model){
		
		List<Attendee> attendees = attendeeRepository.getAttendeesWithoutAMI();
		
		model.addAttribute("attendees", attendees);
		model.addAttribute("amiCount", amiDetailsRepo.getUnassignedAmiCount());
		return "amiAssignment";
	}
	
	@RequestMapping("/amiAssign")
	public String amiAssign(){
		
		List<Attendee> attendees = attendeeRepository.getAttendeesWithoutAMI();
		
		Iterator<AmiDetails> amis = amiDetailsRepo.findUnusedAmis().iterator();
		
		
		for(Attendee attendee: attendees){
			if(amis.hasNext()){
				attendee.setAmiDetails(amis.next());
				attendee.getAmiDetails().setAttendee(attendee);
				attendeeRepository.save(attendee);
				amiDetailsRepo.save(attendee.getAmiDetails());
			}
		}
		
		return "amisAssigned";
	}
	
	@RequestMapping(value="/bulkAddAttendees", method=RequestMethod.GET)
	public String bulkAddAttendees(){
		return "bulkAddAttendees";
	}
	
	@RequestMapping(value="/bulkAddAttendees", method=RequestMethod.POST)
	public String bulkAddAttendees(@RequestParam("attendees") String attendees, Model model){
		
		StringTokenizer linebreaks = new StringTokenizer(attendees, "\n");
		
		while(linebreaks.hasMoreTokens()){
			String attendeeString = linebreaks.nextToken();
			StringTokenizer attendeeTok = new StringTokenizer(attendeeString,",");
			Attendee attendee = new Attendee();
			attendee.setFirstName(attendeeTok.nextToken());
			attendee.setLastName(attendeeTok.nextToken());
			attendee.setEmailAddress(attendeeTok.nextToken());
			attendeeRepository.save(attendee);
		}
		
		return "bulkAddAttendees";
	}
	
	@RequestMapping(value="/bulkAddAMIs", method=RequestMethod.GET)
	public String bulkAddAMIs(){
		return "bulkAddAMIs";
	}
	
	@RequestMapping(value="/bulkAddAMIs", method=RequestMethod.POST)
	public String bulkAddAMIs(@RequestParam("amis") String amis, Model model){
		
		StringTokenizer linebreaks = new StringTokenizer(amis, "\n");
		
		while(linebreaks.hasMoreTokens()){
			String amiString = linebreaks.nextToken();
			StringTokenizer amiTok = new StringTokenizer(amiString,",");
			AmiDetails amiDetails = new AmiDetails();
			amiDetails.setIpAddress(amiTok.nextToken());
			amiDetails.setDnsName(amiTok.nextToken());
			
			amiDetailsRepo.save(amiDetails);
		}
		
		return "bulkAddAMIs";
	}
	

}
