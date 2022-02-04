package com.devistat.server.collector;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class CollectorController {
	@RequestMapping(value="/receiveJSON", method=RequestMethod.POST)
	public String collector() {
		return "임시 응답";
	}
}
