package mods.Brewing_a_Beer_v2.Handlers;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.Brewing_a_Beer_v2.BierMod;
import net.minecraft.creativetab.CreativeTabs;

public final class CreativeHandler extends CreativeTabs
{
    public CreativeHandler(int var1, String var2)
    {
        super(var1, var2);
    }

    @SideOnly(Side.CLIENT)

    /**
     * the itemID for the item to be displayed on the tab
     */
    public int getTabIconItemIndex()
    {
        return BierMod.LeererBierKrug.itemID;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Gets the translated Label.
     */
    public String getTranslatedTabLabel()
    {
        return "Brewing a Beer v2";
    }
}
