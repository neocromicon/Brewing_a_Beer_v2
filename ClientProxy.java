package mods.Brewing_a_Beer_v2;

import net.minecraft.client.Minecraft;
import mods.Brewing_a_Beer_v2.Handlers.TickHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends ServerProxy{
	
	//File Locations (String)
	public static String Crafting = Minecraft.getAppDir("minecraft")+"config/Brewing_a_Beer_Crafting_Settings.cfg";	
	public static String Update = Minecraft.getAppDir("minecraft")+"/config/Brewing_a_Beer_Update.cfg";
	
	//Comments
	public static String UpdateComment = "Brewing a Beer Update Check";
	
	/**
	 * Register handlers, renderers and more for CLIENT side
	 */
	public void renderInformation()
    {		
		TickRegistry.registerTickHandler(new TickHandler(), Side.CLIENT);		
    }
}
