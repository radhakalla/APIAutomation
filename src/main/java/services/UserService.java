package services;

import java.util.HashMap;

import httpMethods.GetRequest;
import httpMethods.PostRequest;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import models.CreateUserRequestModel;
import resourcereader.EndPointReader;

public class UserService {

	
	private GetRequest get;
	private PostRequest post;
	private EndPointReader endPointReader;
	private String getUserDetailsEndPoint,getCreateUserEndpoint;
	
	public UserService(String domainUrl) {
		// TODO Auto-generated constructor stub
		endPointReader= new EndPointReader(domainUrl);
		getUserDetailsEndPoint= endPointReader.getUserDetails();
		getCreateUserEndpoint = endPointReader.getCreateUserDetails();
		get = new GetRequest();
		post =new PostRequest();
		
	}

	public Response getUserDetails(HashMap<String, String> queryParameters) {
		// TODO Auto-generated method stub
		return get.getRequestWithQueryParameter(getUserDetailsEndPoint, queryParameters);
	}

	public Response createUser(CreateUserRequestModel createUserRequest, String name, String job) {
		// TODO Auto-generated method stub
		createUserRequest.setName(name);
		createUserRequest.setJob(job);
		return post.sendPostRequestWithJsonBody(getCreateUserEndpoint, createUserRequest);
	}

	public ValidatableResponse createUserAndVerifyJsonSchema(CreateUserRequestModel createUserRequest, String name, String job,
			String file) {
		createUserRequest.setName(name);
		createUserRequest.setJob(job);
		return post.sendPostRequestToVerifyJsonSchemaValidator(getCreateUserEndpoint, createUserRequest,file);
	}
	
	
}
