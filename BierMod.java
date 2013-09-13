package assets.brewing_a_beer_v2;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import assets.brewing_a_beer_v2.Api.AddonAPI;
import assets.brewing_a_beer_v2.Bier.PilsBier;
import assets.brewing_a_beer_v2.Handlers.CreativeHandler;
import assets.brewing_a_beer_v2.Handlers.DrunkHandler;
import assets.brewing_a_beer_v2.Handlers.DrunkNetworkHandler;
import assets.brewing_a_beer_v2.Handlers.GuiHandler;
import assets.brewing_a_beer_v2.Handlers.ItemHandler;
import assets.brewing_a_beer_v2.Handlers.PropertyHandler;
import assets.brewing_a_beer_v2.Lib.InitHelper;
import assets.brewing_a_beer_v2.Maschines.BrewingTable.BlockBrewingTable;
import assets.brewing_a_beer_v2.Maschines.BrewingTable.CraftingManagerBrewingTable;
import assets.brewing_a_beer_v2.Maschines.MaltGrinder.BlockMaltGrinder;
import assets.brewing_a_beer_v2.Maschines.MaltGrinder.TileEntityMaltGrinder;
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
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = InitHelper.MODID, name = InitHelper.MODNAME, version = InitHelper.MODVERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, 
channels = {"BeerModChannel"}, packetHandler = DrunkNetworkHandler.class)

public class BierMod{
	
	//Instance of the Mod
	@Instance
	public static BierMod instance = new BierMod();
	
	//Get PropertyHandler instance
	public static PropertyHandler props = PropertyHandler.instance;
	
	//get API
	private static AddonAPI api = new AddonAPI();
   
    @SidedProxy(clientSide = InitHelper.CLIENT_PROXY_LOCATION, serverSide = InitHelper.SERVER_PROXY_LOCATION)
    public static ServerProxy sproxy;
    public static ClientProxy cproxy;
    public static CreativeTabs tabBeerCreative = new CreativeHandler(CreativeTabs.getNextID(), InitHelper.MODNAME);
    private GuiHandler guiHandler = new GuiHandler();
    
    //Config Id's
    public static int MaltGrinderID,IngredientsTableID;
    public static int LeererBierKrugID,PilsBierID;
    public static int ModelMaltGrinderID = RenderingRegistry.getNextAvailableRenderId();
    
    public static Block MaltGrinder,IngredientsTable;
    public static Item LeererBierKrug,PilsBier;
    
    private CraftingManagerBrewingTable BrewingTable = CraftingManagerBrewingTable.getInstance();
    
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
			Minecraft mc = Minecraft.getMinecraft();
			if (props.isProperty(mc.mcDataDir+cproxy.Update))
			{		
				props.setProperty(mc.mcDataDir+cproxy.Update, "UpdateCheck", "true", cproxy.UpdateComment);
			}
		} 
		
		Configuration config = new Configuration(fml.getSuggestedConfigurationFile());
		try
        {
			config.load();
			MaltGrinderID = config.getBlock("Malt Grinder", 512).getInt();
			IngredientsTableID = config.getBlock("Ingredients Table", 513).getInt();
			
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
    public void init(FMLInitializationEvent event)
    {
    	intBlockAndItems();
    	registerBlocks();
    	registerHandlers();
    }
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		registerRecipes();
		loadAddons();
	  }
    
	private void intBlockAndItems() {
		
		//Glaeser
		LeererBierKrug = (new ItemHandler(LeererBierKrugID, "BierGlass")).setCreativeTab(tabBeerCreative).setUnlocalizedName("LeeresBierGlas");
		
		//Bier Sorten
		PilsBier = (new PilsBier(PilsBierID, 0, 0, 0.0F, false, InitHelper.MODID + ":PilsBier")).setAlwaysEdible().setCreativeTab(tabBeerCreative).setUnlocalizedName("PilsBier");
		
		//Maschinen
		MaltGrinder = (new BlockMaltGrinder(MaltGrinderID, TileEntityMaltGrinder.class, InitHelper.MODID + ":MaltGrinder/maltGrinder")).setHardness(10.0F).setResistance(8.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("BlockMaltGrinder").setCreativeTab(tabBeerCreative);
		IngredientsTable = new BlockBrewingTable(IngredientsTableID).setHardness(2.5F).setResistance(2000.0F).setStepSound(Block.soundWoodFootstep).setCreativeTab(tabBeerCreative).setUnlocalizedName("IngredientsTable");
	}
	
	private void registerBlocks() {
    	GameRegistry.registerBlock(MaltGrinder);
    	GameRegistry.registerBlock(IngredientsTable);
    }
	
	private void registerHandlers() {	  
		//Gui
		NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
		//Drunk Effect
		DrunkHandler.DrunkEffekt = (new DrunkHandler(30, true, 12196378, "Drunk")).setIconIndex(0, 0);		
	}
	
	private void registerRecipes() {
		BrewingTable.addRecipe(new ItemStack(Block.beacon, 1), new Object[] {"DDDDD", "DDDDD", "DDTDD", "DDDDD", "DDDDD", Character.valueOf('D'), Item.diamond, Character.valueOf('T'), Block.dirt});
	}
	
	private void loadAddons() {
		FMLLog.log(InitHelper.MODID, Level.INFO, "Scan for Addons...");
		
		Set set = api.getAddon().entrySet(); 
		Iterator i = set.iterator(); 
		
		if (set.isEmpty()) {
			FMLLog.log(InitHelper.MODID, Level.WARNING, "No Addons found");
		} else {
			while(i.hasNext()) { 
				Map.Entry me = (Map.Entry)i.next(); 
				FMLLog.log(InitHelper.MODID, Level.INFO, "Found Addon: " + String.valueOf(me.getKey()) + " v" + String.valueOf(me.getValue()));
			}
		}
	}
}
