package assets.brewing_a_beer_v2;

import assets.brewing_a_beer_v2.Handlers.TickHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ServerProxy {
	
	//File Locations (String)
    public static String CraftingServer = "Brewing_a_Beer_v2_Crafting_Settings_Server.cfg";	
	public static String UpdateServer = "Brewing_a_Beer_v2_Update_Server.cfg";
	
	//Comments
	public static String UpdateCommentServer = "Brewing a Beer Update Check Server";
	
	/**
	 * Register handlers, renderers and more for SERVER side
	 */
	public void renderInformation()
    {
    	TickRegistry.registerTickHandler(new TickHandler(), Side.SERVER);
    }	

}
