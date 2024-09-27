package httpMethods;

import java.util.Map;


import io.restassured.RestAssured;
import io.restassured.response.Response;
public class GetRequest {

	public Response getRequestWithQueryParameter(String url,Map<String,String> queryParam)
	
	{
		Response res=RestAssured.given().queryParams(queryParam).when().get(url).then().extract().response();
		return res;
	}
}
