package resourcereader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jayway.jsonpath.JsonPath;

public class EndPointReader {
 
	
	private Object endPoints;
	
	private String baseurl;
	
	public EndPointReader(String baseurl)
	{
		this.baseurl=baseurl;
		endPoints = JsonPath.read(readFromStream().toString(),"$");
	}
	
	public String getUserDetails()
	{
		return baseurl+ JsonPath.read(endPoints, "$.users.getListOfUsers");
	}
	
	public String getCreateUserDetails()
	
	{
		return baseurl+ JsonPath.read(endPoints, "$.users.createUser");
	}

	private StringBuilder readFromStream() {
		// TODO Auto-generated method stub
		InputStream in= getClass().getResourceAsStream("/endpoints/endpoints.json");
		BufferedReader reader= new BufferedReader(new InputStreamReader(in));
		String line="";
		StringBuilder responseStringbuilder= new StringBuilder();
		try {
			while((line= reader.readLine())!= null)
			{
				responseStringbuilder.append(line);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responseStringbuilder;
	}
	
	
	
}
