package httpMethods;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import java.util.*;
import java.io.File;

public class PostRequest {

	public Response sendPostRequestWithJsonBody(
			String url,Object jsonBody)
	{
		Response res= RestAssured.given().body(jsonBody).when().post(url).then().extract().response();
		return res;
	}
	
	 public ValidatableResponse sendPostRequestToVerifyJsonSchemaValidator(
			String url,Object jsonBody,String file)
	{
		ValidatableResponse res=RestAssured.given().body(jsonBody).when().post(url).then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(file)));
		return res;
	}
}
