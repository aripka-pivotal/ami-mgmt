package com.gopivotal.cf.workshop.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class AmiDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@OneToOne(fetch=FetchType.EAGER)
	private Attendee attendee;
	
	private String ipAddress;
	private String dnsName;
	
	private Long count;
	
	public Attendee getAttendee() {
		return attendee;
	}
	public void setAttendee(Attendee attendee) {
		this.attendee = attendee;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getDnsName() {
		return dnsName;
	}
	public void setDnsName(String dnsName) {
		this.dnsName = dnsName;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	
	
	
	
	
	
}
