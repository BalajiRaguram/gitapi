package com.githubuser.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.githubuser.model.User;

@Controller

public class MainController {
	
	Map<String,User> localStorage = new HashMap<String,User>();

	@RequestMapping(value="/getValue", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> homepage(@RequestParam("user") String personId,Model model){
		User Body = new User();
		if(localStorage.containsKey(personId)) {
			return new ResponseEntity(localStorage.get(personId), HttpStatus.OK);
		}
		try {
    	RestTemplate rest = new RestTemplate();
    	ResponseEntity<User>  result = rest.getForEntity("https://api.github.com/users/"+personId,User.class);
    	if(result.getStatusCode().is2xxSuccessful()) {
    		localStorage.put(result.getBody().getLogin(),result.getBody());
    		Body = result.getBody();
    	}

		return new ResponseEntity<User>(Body, result.getStatusCode());
		}
		catch (HttpStatusCodeException exception) {
		    int statusCode = exception.getStatusCode().value();
		   
		}
		return new ResponseEntity<User>(HttpStatus.ACCEPTED);
    			
       // return result;
    }
}
