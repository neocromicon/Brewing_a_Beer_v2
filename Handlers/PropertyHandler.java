package mods.Brewing_a_Beer_v2.Handlers;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyHandler {
	
	protected static String Crafting = "";
	protected static String CraftingServer = "Brewing_a_Beer_Crafting_Settings_Server.cfg";
	protected static String Update = "config/Brewing_a_Beer_Update.cfg";
	protected static String UpdateServer = "Brewing_a_Beer_Update_Server.cfg";
	
	public static void createProperty()
	{
		
	}
	
	public static void setProperty()
	{
		
	}
	
	public static String getProperty(String File, String Store) {
		try {
			Properties props = new Properties();
			String OpenFile = new StringBuilder().append(File).toString();
			props.load(new FileInputStream(OpenFile));
			String getProp = props.getProperty(Store);
			return getProp;
		} catch (Exception exception) {
			System.err.println("Could not detect" + File);
			exception.printStackTrace();
			return null;
		}
	}
	
	public static boolean isProperty()
	{
		return false;		
	}
}
