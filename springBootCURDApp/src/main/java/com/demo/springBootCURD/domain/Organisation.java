package com.demo.springBootCURD.domain;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Organisation extends ResourceSupport{
	
	@JsonProperty(value = "orgId", required = true)
	private String orgId;
	
	@JsonProperty(value = "orgName", required = true)
	private String orgName; 
	
	@JsonProperty("description")
	private String description;
	
	public Organisation() {}
	
	public Organisation(String orgId, String orgName, String description){
		this.orgId = orgId;
		this.orgName = orgName;
		this.description = description;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
		result = prime * result + ((orgName == null) ? 0 : orgName.hashCode());
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
		Organisation other = (Organisation) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (orgId == null) {
			if (other.orgId != null)
				return false;
		} else if (!orgId.equals(other.orgId))
			return false;
		if (orgName == null) {
			if (other.orgName != null)
				return false;
		} else if (!orgName.equals(other.orgName))
			return false;
		return true;
	}
	
	@Override
    public void add(Link... links) {
        super.add(links);
    }
}
