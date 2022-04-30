package com.rest.restsfull.restfullwebservice.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

	@GetMapping("/filter")
	public FilterBean getFilterBeans() {
		FilterBean filterBean = new FilterBean("V1","V2","V3","V4");
		
		return filterBean;
	}
	
	@GetMapping("/filter-list")
	public List<FilterBean> getFilterListBeans() {
		return Arrays.asList(new FilterBean("V1","V2","V3","V4"),new FilterBean("V11","V22","V33","V44"));
	}
	
}
