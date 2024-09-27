package testutility;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

public class ConfigPropertyReader {

	private static final String configFile=System.getProperty("user.dir")+File.separator+"config.properties";
	
	private HashMap<String,String> configSettings;
	
	public ConfigPropertyReader()
	{
		configSettings = new HashMap<String,String>();
		configSettings = getSessionConfig();
	}

	public HashMap<String,String> getConfigSettings()
	{
		return configSettings;
	}
	private HashMap<String, String> getSessionConfig() {
		// TODO Auto-generated method stub
		configSettings = readAllPropertyValuesFromConfigFile();
		
		return configSettings;
	}

	private HashMap<String, String> readAllPropertyValuesFromConfigFile() {
		// TODO Auto-generated method stub
		
		HashMap<String,String> map= new HashMap<String,String>();
		Properties prop;
		
		try {
			prop= ResourceLoader.loadProperties(configFile);
			System.out.println(prop.get("base_url"));
			for(final Entry<Object,Object> entry : prop.entrySet())
			{
				map.put((String)entry.getKey(),(String) entry.getValue());
			}
			return map;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;

		}
	}
	
}
