package assets.brewing_a_beer_v2.Api;

import java.util.HashMap;

public class AddonAPI{
	
	static HashMap addon = new HashMap();
	//static HashMap addonName = new HashMap(); 
	
	/**
	 * Get the Version of the Addon
	 * @param addonVersion
	 */
	public void setAddon(String addonName, String addonVersion) {
		this.addon.put(addonName, addonVersion);	
	}
	/**
	 * Set the Version of the Addon
	 * @return addonVersion
	 */
	public static HashMap getAddon() {
		return addon;
	}
}
