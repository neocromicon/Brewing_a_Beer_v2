package assets.Brewing_a_Beer_v2;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import assets.Brewing_a_Beer_v2.Api.AddonAPI;
import assets.Brewing_a_Beer_v2.Bier.PilsBier;
import assets.Brewing_a_Beer_v2.Handlers.CreativeHandler;
import assets.Brewing_a_Beer_v2.Handlers.DrunkHandler;
import assets.Brewing_a_Beer_v2.Handlers.DrunkNetworkHandler;
import assets.Brewing_a_Beer_v2.Handlers.ItemHandler;
import assets.Brewing_a_Beer_v2.Handlers.PropertyHandler;
import assets.Brewing_a_Beer_v2.Maschines.MaltGrinder.BlockMaltGrinder;
import assets.Brewing_a_Beer_v2.Maschines.MaltGrinder.TileEntityMaltGrinder;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "Brewing_a_Beer", name = "Brewing a Beer", version = "2.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, 
channels = {"BeerModChannel"}, packetHandler = DrunkNetworkHandler.class)

public class BierMod{

	//Instance of the Mod
	@Instance
	public static BierMod instance = new BierMod();
	
	//Get PropertyHandler instance
	public static PropertyHandler props = PropertyHandler.instance;
	
	//get MC
	private Minecraft mc = Minecraft.getMinecraft();
	
	//get API
	private static AddonAPI api = new AddonAPI();
	
	//UpdateHandler version check string
	public static String modVersion = "2.0";
	
	//TextureHandler folder string
    public static String modID = "Brewing_a_Beer_v2";
    
    @SidedProxy(clientSide = "assets.Brewing_a_Beer_v2.ClientProxy", serverSide = "assets.Brewing_a_Beer_v2.ServerProxy")
    public static ServerProxy sproxy;
    public static ClientProxy cproxy;
    public static CreativeTabs tabBeerCreative = new CreativeHandler(CreativeTabs.getNextID(), "Brewing a Beer");
    
    //Config Id's
    public static int MaltGrinderID;
    public static int LeererBierKrugID,PilsBierID;
    public static int ModelMaltGrinderID = RenderingRegistry.getNextAvailableRenderId();
    
    public static Block MaltGrinder;
    public static Item LeererBierKrug,PilsBier;   
    
    @EventHandler
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
			if (props.isProperty(mc.mcDataDir+cproxy.Update))
			{		
				props.setProperty(mc.mcDataDir+cproxy.Update, "UpdateCheck", "true", cproxy.UpdateComment);
			}
		} else {			
		}
		
		Configuration config = new Configuration(fml.getSuggestedConfigurationFile());
		try
        {
			config.load();
			MaltGrinderID = config.getBlock("Malt Grinder", 512).getInt();
			
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
    
	@EventHandler
    public void init(FMLInitializationEvent event) throws IOException
    {
    	intBlockAndItems();
    	registerBlocks();
    	registerHandlers();
    }
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		loadAddons();
	  }
    
	private void intBlockAndItems() {
		
		//Glaeser
		LeererBierKrug = (new ItemHandler(LeererBierKrugID, "BierGlass")).setCreativeTab(tabBeerCreative).setUnlocalizedName("LeeresBierGlas");
		
		//Bier Sorten
		PilsBier = (new PilsBier(PilsBierID, 0, 0, 0.0F, false, modID + ":WeizenBier")).setAlwaysEdible().setCreativeTab(tabBeerCreative).setUnlocalizedName("PilsBier");
		
		//Maschinen
		MaltGrinder = (new BlockMaltGrinder(MaltGrinderID, TileEntityMaltGrinder.class)).setHardness(10.0F).setResistance(8.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("BlockGaerTank").setCreativeTab(tabBeerCreative);
		
	}
	
	private void registerBlocks() {
    	GameRegistry.registerBlock(MaltGrinder);
    }
	
	private void registerHandlers() {	    
		//Drunk Effect
		DrunkHandler.DrunkEffekt = (new DrunkHandler(30, true, 12196378, "Drunk")).setIconIndex(0, 0);		
	}
	
	private void loadAddons() {
		FMLLog.log(BierMod.modID, Level.INFO, "Scan for Addons...");
		
		Set set = api.getAddon().entrySet(); 
		Iterator i = set.iterator(); 
		
		if (set.isEmpty()) {
			FMLLog.log(BierMod.modID, Level.WARNING, "No Addons found");
		} else {
			while(i.hasNext()) { 
				Map.Entry me = (Map.Entry)i.next(); 
				FMLLog.log(BierMod.modID, Level.INFO, "Found Addon: " + String.valueOf(me.getKey()) + " v" + String.valueOf(me.getValue()));
			}
		}
	}
}
