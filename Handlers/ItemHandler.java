package assets.Brewing_a_Beer_v2.Handlers;

import assets.Brewing_a_Beer_v2.BierMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemHandler extends Item
{
    public String Image;

    public ItemHandler(int var1, String var2)
    {
        super(var1);
        this.Image = var2;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister var1)
    {
        this.itemIcon = var1.registerIcon(BierMod.modID + ":" + this.Image);
    }
}
