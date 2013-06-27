package mods.Brewing_a_Beer_v2.Handlers;

import mods.Brewing_a_Beer_v2.BierMod;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.potion.Potion;
import net.minecraft.src.ModLoader;

public class DrunkHandler extends Potion{
	
	public static Potion DrunkEffekt;		
    private static int DrunkLevel = 0;
    public static DrunkHandler instance;
	
	public DrunkHandler(int var1, boolean var2, int var3, String var4) {
		super(var1, var2, var3);
		this.setPotionName(var4);
		this.setEffectiveness(0.25D);
	}
	
	public static void DrunkLevelPlus()
    {
        ++DrunkLevel;
    }

    public static void DrunkLevelMinus()
    {
        --DrunkLevel;
    }

    public static int getDrunkLevel()
    {
        return DrunkLevel;
    }

    public static void setDrunkLevel(int var0)
    {
        DrunkLevel = var0;
    }
    /**
     * Returns the index for the icon to display when the potion is active.
     */
    public int getStatusIconIndex()
    {
        RenderEngine var1 = ModLoader.getMinecraftInstance().renderEngine;
        var1.bindTexture("/mods/" + BierMod.modID + "/textures/items/DrunkSymbol.png");
        return super.getStatusIconIndex();
    }   
    /**
     * Set the potion name.
     */    
    public DrunkHandler setPotionName(String name)
    {
      return (DrunkHandler)super.setPotionName(name);
    }

}
