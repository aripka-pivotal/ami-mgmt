package com.gopivotal.cf.workshop.repository;

import java.util.List;

import com.gopivotal.cf.workshop.entity.AmiDetails;
import com.gopivotal.cf.workshop.entity.Attendee;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AMIDetailsRepository extends CrudRepository<AmiDetails, Long>{

	AmiDetails findByAttendee(Attendee attendee);
	
	@Query("select a from AmiDetails a where a.attendee is null")
	List<AmiDetails> findUnusedAmis();
	
	@Query("select count(a) from AmiDetails a where a.attendee is null")
	Long getUnassignedAmiCount();
}
