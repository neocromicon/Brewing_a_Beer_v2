package mods.Brewing_a_Beer_v2;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

import mods.Brewing_a_Beer_v2.Handlers.PacketHandler;
import mods.Brewing_a_Beer_v2.Handlers.PropertyHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "Brewing_a_Beer", name = "Brewing a Beer", version = "2.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, 
channels = {"BeerModChannel"}, packetHandler = PacketHandler.class)

public class BierMod {

	//Instance of the Mod
	@Instance
	public static BierMod instance = new BierMod();
	
	//Get PropertyHandler instance
	public static PropertyHandler props = PropertyHandler.instance;
	//UpdateHandler version check string
	public static String modVersion = "2.0";
	//TextureHandler folder string
    public static String modID = "Brewing_a_Beer";
    
    @SidedProxy(clientSide = "mods.Brewing_a_Beer_v2.ClientProxy", serverSide = "mods.Brewing_a_Beer_v2.ServerProxy")
    public static ServerProxy sproxy;
    public static ClientProxy cproxy;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent var1)
    {    
    	//Check is property file exists
    	Side side = FMLCommonHandler.instance().getEffectiveSide();
		if (side == Side.SERVER) {
			if (props.isProperty(sproxy.UpdateServer))
			{
				props.setProperty(sproxy.UpdateServer, "UpdateCheck", "true", sproxy.UpdateCommentServer);				
			}			
		} else if (side == Side.CLIENT) {
			if (props.isProperty(cproxy.Update))
			{		
				props.setProperty(cproxy.Update, "UpdateCheck", "true", cproxy.UpdateComment);
			}
		} else {			
		}
		sproxy.renderInformation();
    }
    
    @Init
    public void init(FMLInitializationEvent var1)
    {
    	registerBlockAndItems();
    	registerHandlers();    	
    }
    
	private void registerBlockAndItems() {
		// TODO Auto-generated method stub
		
	}
	
	private void registerHandlers() {
		// TODO Auto-generated method stub
		
	}
}
