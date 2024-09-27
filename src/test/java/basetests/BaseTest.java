package basetests;

import java.util.HashMap;

import testutility.ConfigPropertyReader;

public class BaseTest {

	public HashMap<String,String> configSettings;
	public ConfigPropertyReader configProperties;
	
	public void initialize()
	{
		configProperties= new ConfigPropertyReader();
		configSettings=configProperties.getConfigSettings();
	}
}
