package com.api.com.APITest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import basetests.BaseTest;
import io.restassured.response.Response;
import models.UserDetailsResponseModel;
import services.UserService;

public class GetUserDetails extends BaseTest{

	private UserService userservice;
	private UserDetailsResponseModel userDetails;
	private HashMap<String,String> queryParameters = new HashMap<String,String>();
	
	@BeforeClass
	public void intializeRequestDetails()
	{
		initialize();
		userDetails=new UserDetailsResponseModel();
		userservice= new UserService(configSettings.get("base_url"));
	}
	
	@DataProvider(name = "userids")
	public Object[][] userIds()
	{
		return new Object[][] {{"1"},{"2"},{"3"},{"4"}};
	}
	
	@Test(dataProvider = "userids")
	public void sendAGetRequestAndSendIdsDynamically(String id)
	{
		queryParameters.put("page", id);
		Response response=userservice.getUserDetails(queryParameters);
		assertEquals(response.getStatusCode(),200);
	}
	
	@Test(dataProvider = "userids")
	public void verifyResponseHasSupportObjectWhenUserIdsAreSendDynamically(String id)
	{
		queryParameters.put("page", id);
		Response response=userservice.getUserDetails(queryParameters);
		assertEquals(response.getStatusCode(),200);
		assertTrue(response.asString().contains("support"));
		userDetails= response.as(UserDetailsResponseModel.class);
		System.out.println(userDetails.getSupport().getUrl());
		System.out.println(userDetails.getSupport().getText());
	}
	
}
