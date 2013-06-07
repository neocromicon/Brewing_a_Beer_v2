package mods.Brewing_a_Beer_v2.Handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertyHandler {
	
	//Instance of Properties()
	public static Properties props = new Properties();
	public static PropertyHandler instance = new PropertyHandler();
	
	/**
	 * Create a File with a new Entry
	 * @param FileName : Name of the new File
	 * @param PropertyName : Name of new property
	 * @param StoreName : Value of property
	 * @param Comment : Comment in property file
	 */
	public static void createNewProperty(String FileName, String PropertyName, String StoreName, String Comment) {
		if (isProperty(FileName)) 
		{
			System.err.println(FileName + " already exists");
		}
		else
		{
			try{
				File file = new File(FileName);
				file.createNewFile();
				props.load(new FileInputStream(file));
				props.setProperty(PropertyName, StoreName);
				props.store(new FileOutputStream(file), Comment);
			} catch (Exception ex) {
				ex.printStackTrace();
			}			
		}
	}
	
	/**
	 * Set a new value to a property
	 * @param File : Open the File
	 * @param Property : Name of the Property
	 * @param Store : Set Value of property
	 */
	public static void setProperty(String File, String Property, String Store, String Comment) {
		if (isProperty(File))
		{
			try{
				props.load(new FileInputStream(File));
				props.setProperty(Property, Store);
				props.store(new FileOutputStream(File), Comment);
			} catch (Exception ex) {
				ex.printStackTrace();
			} 
		}		
	}
	
	/**
	 * Get a property, return null if file or property not exists
	 * @param File : get input file
	 * @param Store : get property in file
	 * @return
	 */
	public static String getProperty(String File, String Store) {
		if (isProperty(File)) {
			try {
				String OpenFile = new StringBuilder().append(File).toString();
				props.load(new FileInputStream(OpenFile));
				String getProp = props.getProperty(Store);
				return getProp;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Checks file if exists 
	 * @param File : get input file
	 * @return : Return false if file not exists
	 */
	public static boolean isProperty(String File) {
		try {
			File OpenFile = new File(File);
			if (OpenFile.exists())
			{ return true; }
		} catch (Exception ex) {
			System.err.println(File + " is not valid File or is missing");
			ex.printStackTrace();
			return false;
		}
		return false;		
	}
}