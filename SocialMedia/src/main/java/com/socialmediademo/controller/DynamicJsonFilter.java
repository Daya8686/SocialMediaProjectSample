package com.socialmediademo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.socialmediademo.entity.FieldsForFiltering;

@RestController
public class DynamicJsonFilter {
	
	@GetMapping("/filter")
	public MappingJacksonValue getFields() {
		
		 FieldsForFiltering fieldsForFiltering = new FieldsForFiltering("Value 1", "Value 2", "Value 3");
//		Here we are adding the dynamic filtering
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(fieldsForFiltering);
		SimpleBeanPropertyFilter filter =SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filters =new SimpleFilterProvider().addFilter("SampleBeanFilter", filter);
		mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}
	
	
	@GetMapping("/filter-list")
	public MappingJacksonValue getListOfFields(){
		List<FieldsForFiltering> list = new ArrayList<>();
		list.add( new FieldsForFiltering("Value 1", "Value 2", "Value 3"));
		list.add(new FieldsForFiltering("Value 4", "Value 5", "Value 6"));
		 FieldsForFiltering fieldsForFiltering = new FieldsForFiltering("Value 1", "Value 2", "Value 3");
//			Here we are adding the dynamic filtering
			MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
			SimpleBeanPropertyFilter filter =SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
			FilterProvider filters =new SimpleFilterProvider().addFilter("SampleBeanFilter", filter);
			mappingJacksonValue.setFilters(filters);
		return mappingJacksonValue;
	}

}
