package assets.Brewing_a_Beer_v2.Handlers;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TickHandler implements ITickHandler{

	public static UpdateHandler updateNotifier;

	@Override
    public void tickStart(EnumSet var1, Object ... var2){
    }

	/**
	 * Tick...tick...tick
	 */
	@Override
    public void tickEnd(EnumSet var1, Object ... var2)
    {
    	if (var1.equals(EnumSet.of(TickType.SERVER)))
        {
            this.onServerRenderTick();
        }
        else if (var1.equals(EnumSet.of(TickType.CLIENT)))
        {
            GuiScreen var3 = Minecraft.getMinecraft().currentScreen;

            if (var3 != null)
            {
                this.onTickInGUI(var3);
            }
            else
            {
                this.onClientTickInGame();
            }
        }        
    }

	/**
	 * Check's tick is CLIENT or SERVER side
	 */
	@Override
    public EnumSet ticks()
    {
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if (side == Side.SERVER) {
			return EnumSet.of(TickType.SERVER);
		} else if (side == Side.CLIENT) {
			return EnumSet.of(TickType.RENDER, TickType.CLIENT);
		} else {
			return null;
		}		      
    }

	@Override
    public String getLabel()
    {
        return null;
    }
    
    public void onTickInGUI(GuiScreen var1) {
    	
    }

    /**
     * Start's Client Tick for Updater
     */
    @SideOnly(Side.CLIENT)
    public void onClientTickInGame()
    {
        updateNotifier = new UpdateHandler();
        updateNotifier.CheckUpdateClient();
    }
    
    /**
     * Start's Server Tick for Updater
     */
    @SideOnly(Side.SERVER)
    public void onServerRenderTick()
    {
		updateNotifier = new UpdateHandler();
	    updateNotifier.CheckUpdateServer();	
    }
}