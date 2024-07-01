package com.socialmediademo.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/hello")
	public String getHello() {
		Locale locale =LocaleContextHolder.getLocale();
		return messageSource.getMessage("spring.hello.message", null, "Default Message", locale);
	}

}
