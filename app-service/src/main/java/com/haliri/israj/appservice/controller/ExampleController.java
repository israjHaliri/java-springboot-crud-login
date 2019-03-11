package com.haliri.israj.appservice.controller;

import com.haliri.israj.appcore.domain.common.User;
import com.haliri.israj.appcore.utils.AppUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/secret")
public class ExampleController {

	@RequestMapping(value = "/example_get", method = RequestMethod.GET)
	public Map exGet() {
		Map outParam =  new HashMap();
		outParam.put("outParam : ","this is from example controller");
		return outParam;
	}

	@RequestMapping(value = "/example_post", method = RequestMethod.POST)
	public void exPost(@RequestBody User user) {
		AppUtils.getLogger(this).info("POST PARAM : {}",user);
	}
}
