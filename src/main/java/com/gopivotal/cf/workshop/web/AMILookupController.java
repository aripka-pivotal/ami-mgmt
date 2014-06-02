package com.gopivotal.cf.workshop.web;

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
public class AMILookupController {

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
}
