package com.demo.springBootCURD.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

public class Dealer extends ResourceSupport {
	
	@JsonProperty("dealerId")
	private String dealerId;
	
	@JsonProperty(value = "dealerName", required = true)
	private String dealerName; 
	
	private Organisation organisation;
	
	public Dealer() {}
	
	public Dealer(String dealerId, String dealerName, Organisation organisation){
		this.dealerId = dealerId;
		this.dealerName = dealerName;
		this.organisation = organisation;
	}

	public String getDealerId() {
		return dealerId;
	}

	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	@JsonIgnore
	public Organisation getOrganisation() {
		return organisation;
	}

	@JsonProperty("organisation")
	public void setOrganisation(Organisation organisation) {
		this.organisation = organisation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dealerId == null) ? 0 : dealerId.hashCode());
		result = prime * result + ((dealerName == null) ? 0 : dealerName.hashCode());
		result = prime * result + ((organisation == null) ? 0 : organisation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dealer other = (Dealer) obj;
		if (dealerId == null) {
			if (other.dealerId != null)
				return false;
		} else if (!dealerId.equals(other.dealerId))
			return false;
		if (dealerName == null) {
			if (other.dealerName != null)
				return false;
		} else if (!dealerName.equals(other.dealerName))
			return false;
		if (organisation == null) {
			if (other.organisation != null)
				return false;
		} else if (!organisation.equals(other.organisation))
			return false;
		return true;
	}
	
	@Override
    public void add(Link... links) {
        super.add(links);
    }
}
