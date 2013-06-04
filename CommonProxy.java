package mods.Brewing_a_Beer_v2;

import mods.Brewing_a_Beer_v2.Handlers.TickHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy {
	
	/**
	 * Register handlers, renderers and more for SERVER side
	 */
	public void renderInformation()
    {
    	TickRegistry.registerTickHandler(new TickHandler(), Side.SERVER);
    }	

}
