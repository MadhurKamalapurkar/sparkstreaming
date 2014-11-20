package com.daksh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

@Controller
public class HelloController {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ModelAndView dakshView(@RequestParam String deviceName, @RequestParam String property) {
		ModelAndView mv = new ModelAndView("tryit2");	
		mv.addObject("deviceName",deviceName);
		mv.addObject("property",property);
		return mv;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView landingPage() {
		ModelAndView mv = new ModelAndView("land");
		return mv;
	}
	
}