package assets.Brewing_a_Beer_v2.Api;

public class BierModv2_API {	
	
	public static String message;
	public static String addonName;
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public static String getMessage() {
		return message;
	}

	/**
	 * Set the name of the addon 
	 * @param addonName
	 */
	public void setAddonName(String addonName) {
		this.addonName = addonName;
	}
	
	/**
	 * Get the name of the addon
	 * @return
	 */
	public String getAddonName() {		
		return addonName;
	}

}
