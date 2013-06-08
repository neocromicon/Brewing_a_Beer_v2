package mods.Brewing_a_Beer_v2;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import mods.Brewing_a_Beer_v2.Handlers.*;
import mods.Brewing_a_Beer_v2.Bier.*;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
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
    public static String modID = "Brewing_a_Beer_v2";
    
    @SidedProxy(clientSide = "mods.Brewing_a_Beer_v2.ClientProxy", serverSide = "mods.Brewing_a_Beer_v2.ServerProxy")
    public static ServerProxy sproxy;
    public static ClientProxy cproxy;
    public static CreativeTabs tabBeerCreative = new CreativeHandler(CreativeTabs.getNextID(), "Brewing a Beer");
    
    //Config Id's
    public static int LeererBierKrugID,PilsBierID;
    
    public static Item LeererBierKrug,PilsBier;
    
    @PreInit
    public void preInit(FMLPreInitializationEvent fml)
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
		
		Configuration config = new Configuration(fml.getSuggestedConfigurationFile());
		try
        {
			config.load();
			LeererBierKrugID = config.getItem("Empty Beer Jug", 8000).getInt();
			PilsBierID = config.getItem("Pilsner Beer", 8002).getInt();
        }
        catch (Exception ex)
        {
        	ex.printStackTrace();
        }
        finally
        {
        	config.save();
        }
		sproxy.renderInformation();
    }
    
    @Init
    public void init(FMLInitializationEvent var1)
    {
    	intBlockAndItems();
    	registerHandlers();    	
    }
    
	private void intBlockAndItems() {
		
		//Gläser
		LeererBierKrug = (new ItemHandler(LeererBierKrugID, "BierGlass")).setCreativeTab(tabBeerCreative).setUnlocalizedName("LeeresBierGlas");
		
		//Bier Sorten
		PilsBier = (new PilsBier(PilsBierID, 0, 0, 0.0F, false, modID + ":WeizenBier")).setAlwaysEdible().setCreativeTab(tabBeerCreative).setUnlocalizedName("PilsBier");
		
	}
	
	private void registerHandlers() {

	}
}
