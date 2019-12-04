package com.devglan.rolebasedoauth2.controller;

import com.devglan.rolebasedoauth2.config.AuthorizationServerConfig;
import com.devglan.rolebasedoauth2.dto.ApiResponse;
import com.devglan.rolebasedoauth2.dto.UserDto;
import com.devglan.rolebasedoauth2.service.UserService;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	AuthorizationServerConfig auth;
	
    public static final String SUCCESS = "success";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    @Autowired
    private UserService userService;
    		
    private String tokenValue = null;
   
    public void accessToken() {
    	  final Map<String, String> params = new HashMap<String, String>();
	        params.put("grant_type", "password");
	        params.put("client_id", "devglan-client");
	        params.put("username", "admin");
	        params.put("password", "admin");
	        final Response response = RestAssured.given().auth().preemptive().basic("devglan-client", "devglan-secret").and().with().params(params).when().post("http://localhost:9099/users/user/login");

	        tokenValue = response.jsonPath().getString("access_token");
    }
    
 
    @GetMapping(value="/getToken")
    public ApiResponse getToken() {
    	accessToken();
		return new ApiResponse(HttpStatus.OK, SUCCESS, tokenValue);
    	
    }
    
    @Secured({ROLE_ADMIN})
    @GetMapping
    public ApiResponse listUser(){   	
    	   return new ApiResponse(HttpStatus.OK, SUCCESS, userService.findAll());
    }

    @Secured({ROLE_ADMIN})
    @PostMapping
    public ApiResponse create(@RequestBody UserDto user){
           return new ApiResponse(HttpStatus.OK, SUCCESS, userService.save(user));
    }

    @Secured({ROLE_ADMIN, ROLE_USER})
    @GetMapping(value = "/{id}")
    public ApiResponse getUser(@PathVariable long id){
             return new ApiResponse(HttpStatus.OK, SUCCESS, userService.findOne(id));
    }

    @Secured({ROLE_ADMIN})
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable(value = "id") Long id){
            userService.delete(id);
    }



}
