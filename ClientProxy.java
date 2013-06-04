package mods.Brewing_a_Beer_v2;

import mods.Brewing_a_Beer_v2.Handlers.TickHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends ServerProxy{
	
	/**
	 * Register handlers, renderers and more for CLIENT side
	 */
	public void renderInformation()
    {		
		TickRegistry.registerTickHandler(new TickHandler(), Side.CLIENT);		
    }
}
