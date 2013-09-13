package assets.brewing_a_beer_v2.Handlers;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemHandler extends Item
{
    public String Image;

    public ItemHandler(int id, String image)
    {
        super(id);
        this.Image = image;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister var1)
    {
    	System.out.println("IMAGE");
        this.itemIcon = var1.registerIcon("Brewing_a_Beer_v2:"+ this.Image);
    }
}
