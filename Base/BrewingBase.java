package mods.Brewing_a_Beer_v2.Base;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "Brewing_a_Beer", name = "Brewing a Beer", version = "2.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, 
channels = {"BeerModChannel"}, packetHandler = PacketHandler.class)

public class BrewingBase {

	@Instance
	public static BrewingBase instance = new BrewingBase();
	public static String modVersion = "2.0";
    public static String modID = "Brewing_a_Beer";

}
