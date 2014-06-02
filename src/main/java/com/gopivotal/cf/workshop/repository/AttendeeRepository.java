package com.gopivotal.cf.workshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gopivotal.cf.workshop.entity.Attendee;

/**
 * JPA repository for the Attendee entity.
 * 
 * @author Brian Jimerson
 *
 */
public interface AttendeeRepository extends CrudRepository<Attendee, Long> {
	Attendee findByEmailAddress(String emailAddress);
	
	@Query("select a from Attendee a where a.amiDetails is null")
	List<Attendee> getAttendeesWithoutAMI();
	
}
