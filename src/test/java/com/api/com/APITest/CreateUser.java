package com.api.com.APITest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import basetests.BaseTest;
import io.restassured.response.Response;
import models.CreateUserRequestModel;
import models.CreateUserResponseModel;
import models.UserDetailsResponseModel;
import services.UserService;
import testutility.PayLoadReader;

public class CreateUser extends BaseTest {
	
	String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
			+ File.separator + "resources" + File.separator + "payloads" ;
	private UserService userservice;
	private  CreateUserResponseModel createUserResponse;
	private  CreateUserRequestModel createUserRequest;
	private HashMap<String,String> queryParameters = new HashMap<String,String>();
	
	@BeforeClass
	public void intializeRequestDetails()
	{
		initialize();
		createUserResponse = new  CreateUserResponseModel();
		createUserRequest=  new CreateUserRequestModel();
		userservice= new UserService(configSettings.get("base_url"));
	}
	
	@DataProvider(name = "userdetails")
	public Object[][] userDetails()
	{
		return new Object[][] {{"Radha","SDET"}};
	}
	
	
	//send a post request with name and job fields to create a user
	@Test(dataProvider= "userdetails")
	public void createUser(String name,String job) throws JsonMappingException, JsonProcessingException
	{
       ObjectMapper mapper= new ObjectMapper();		
       createUserRequest=mapper.readValue(PayLoadReader.getPayloadFromFile("CreateUser.json"), CreateUserRequestModel.class);
        Response res= userservice.createUser(createUserRequest,name ,job);
        assertEquals(res.getStatusLine(),"HTTP/1.1 201 Created");
        createUserResponse= res.as(CreateUserResponseModel.class);
        assertNotNull(createUserResponse.getId());
	}
	
	//verify response schema
	@Test(dataProvider= "userdetails")
	public void createUserAndVerifyResponseSchema(String name,String job) throws JsonMappingException, JsonProcessingException
	{
       ObjectMapper mapper= new ObjectMapper();		
       createUserRequest=mapper.readValue(PayLoadReader.getPayloadFromFile("CreateUser.json"), CreateUserRequestModel.class);
        Response res= (Response) userservice.createUserAndVerifyJsonSchema(createUserRequest,name ,job,filePath+ File.separator+"CreateUserJsonSchema.json");
        assertTrue(res.getBody().asString().contains("id"));    
	}

}
