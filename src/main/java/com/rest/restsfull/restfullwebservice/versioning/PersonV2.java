package com.rest.restsfull.restfullwebservice.versioning;

public class PersonV2 {

	private Name name;

	public PersonV2() {}
	
	public PersonV2(Name name) {
		super();
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public Name getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(Name name) {
		this.name = name;
	}
	
	
}
