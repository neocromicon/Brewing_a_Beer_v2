package assets.brewing_a_beer_v2;

import assets.brewing_a_beer_v2.Handlers.TickHandler;
import assets.brewing_a_beer_v2.Maschines.MaltGrinder.ItemGaerTank;
import assets.brewing_a_beer_v2.Maschines.MaltGrinder.RenderMaltGrinder;
import assets.brewing_a_beer_v2.Maschines.MaltGrinder.TileEntityMaltGrinder;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends ServerProxy{	
	
	//File Locations (String)
	public static String Crafting = "/config/Brewing_a_Beer_v2_Crafting_Settings.cfg";	
	public static String Update = "/config/Brewing_a_Beer_v2_Update.cfg";
	
	//Comments
	public static String UpdateComment = "Brewing a Beer v2 Update Check";
	
	/**
	 * Register handlers, renderers and more for CLIENT side
	 */
	public void renderInformation()
    {		
		TickRegistry.registerTickHandler(new TickHandler(), Side.CLIENT);	
		
		ClientRegistry.registerTileEntity(TileEntityMaltGrinder.class, "tileEntityMaltGrinder", new RenderMaltGrinder());
		//MinecraftForgeClient.registerItemRenderer(BierMod.MaltGrinderID, new ItemGaerTank());
    }
}
