package com.rest.restsfull.restfullwebservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
//1 URI versioning
	@GetMapping("/v1/person")
	public PersonV1 getPersoV1() {
		return new PersonV1("Sai Palem");
	}
	@GetMapping("/v2/person")
	public PersonV2 getPersoV2() {
		return new PersonV2(new Name("Sai", "Palem"));
	}
//2 Params
	@GetMapping(value="/person/param",params="version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Sai Palem");
	}
	@GetMapping(value="/person/param",params="version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Sai", "Palem"));
	}
//3 Headers	
	@GetMapping(value="/person/header",headers="x-wversion=1")
	public PersonV1 headerV1() {
		return new PersonV1("Sai Palem");
	}
	@GetMapping(value="/person/header",headers="x-wversion=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Sai", "Palem"));
	}
//4 Mime type/ Producers.
	
	@GetMapping(value="/person/produces",produces="application/vnd.company.appv1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Sai Palem");
	}
	@GetMapping(value="/person/produces",produces="application/vnd.company.appv2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Sai", "Palem"));
	}
}
