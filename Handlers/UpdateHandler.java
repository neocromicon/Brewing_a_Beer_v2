package mods.Brewing_a_Beer_v2.Handlers;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import mods.Brewing_a_Beer_v2.BierMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.server.MinecraftServer;

public class UpdateHandler extends Thread
{
	public static PropertyHandler props = PropertyHandler.instance;
    private TickHandler modServer;
    private TickHandler modClient;
    private static Minecraft mc;

    UpdateHandler(TickHandler Typ)
    {
        this.modServer = Typ;
        this.modClient = Typ;
    }

    @SideOnly(Side.SERVER)
    public void attemptServer()
    {    	    
    	this.startServer();   	
    }

    @SideOnly(Side.CLIENT)
    public void attemptClient()
    {
    	this.startClient();
    }

    /**
     * Start Update Checker for the SERVER Side
     */
    @SideOnly(Side.SERVER)
    public void startServer()
    {   	
    	if (props.isProperty(props.UpdateServer))
    	{
    		String check = props.getProperty(props.UpdateServer, "UpdateCheck");
    		if (check.endsWith("true"))
    		{
    			props.setProperty(props.UpdateServer, "UpdateCheck", "false", props.UpdateCommentServer);
    			if(checkUpdateServer() == false)
    	    	{
    	    		FMLLog.log(BierMod.modID, Level.INFO, "Update Server is Offline!");
    	    	}
    	    	else
    	    	{ try {
    	    			URL url = new URL("http://46.38.239.84/neo/updater/BeerModUpdate");
    	    			URL urlInfo = new URL("http://46.38.239.84/neo/updater/BeerModUpdateInfo");
    	    			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
    	    			BufferedReader brInfo = new BufferedReader(new InputStreamReader(urlInfo.openStream()));
    	    			
    	    			String HoleDaten;
    	    			String ModInfo = brInfo.readLine();
    	    			String ModVersion = BierMod.modVersion;
    	    		    while ((HoleDaten = br.readLine()) != null) {
    	    				if (HoleDaten.endsWith(ModVersion)) {
    	    					FMLLog.log(BierMod.modID, Level.INFO, "Your version is UpToDate: v" + HoleDaten);
    	    				} else {
    	    					FMLLog.log(BierMod.modID, Level.INFO, "A new version is available: v" + HoleDaten);
    	                    	FMLLog.log(BierMod.modID, Level.INFO, "New Features of v" + HoleDaten + ": " + ModInfo);
    	                    	FMLLog.log(BierMod.modID, Level.INFO, "Your version is: v" + ModVersion);
    	    				}
    	    			}
    	    			br.close();
    	    			brInfo.close();    	    			
    	    		} catch (Exception e) {
    	    		}   		
    	    	}     
    		}
    	}
    	else
    	{ 
    		props.createNewProperty(props.UpdateServer, "UpdateCheck", "true", props.UpdateCommentServer);
    		FMLLog.log(BierMod.modID, Level.INFO, "Generating File " + props.UpdateServer + " successful");
    	}
    	
    }

    /**
     * Start Update Checker for the CLIENT Side
     */
    @SideOnly(Side.CLIENT)
    public void startClient()
    {
    	if (props.isProperty(Minecraft.getAppDir("minecraft")+props.Update))
    	{
    		String check = props.getProperty(Minecraft.getAppDir("minecraft")+props.Update, "UpdateCheck");
    		if (check.endsWith("true"))
    		{
    			EntityClientPlayerMP var8 = FMLClientHandler.instance().getClient().thePlayer;
    			props.setProperty(Minecraft.getAppDir("minecraft")+props.Update, "UpdateCheck", "false", props.UpdateComment);
    	    	if(checkUpdateServer() == false)
    	    	{
    	    		var8.addChatMessage("\u00a72[Brew a Beer]\u00a7r Update Server is Offline!");
    	    	}
    	    	else
    	    	{ try {
    	            URL var1 = new URL("http://46.38.239.84/neo/updater/BeerModUpdate");
    	            URL var2 = new URL("http://46.38.239.84/neo/updater/BeerModUpdateInfo");
    	            BufferedReader var3 = new BufferedReader(new InputStreamReader(var1.openStream()));
    	            BufferedReader var4 = new BufferedReader(new InputStreamReader(var2.openStream()));
    	            String var6 = var4.readLine();
    	            String var7 = BierMod.modVersion;
    	            
    	            String var5;

    	            while ((var5 = var3.readLine()) != null)
    	            {
    	                if (var5.endsWith(var7))
    	                {
    	                    FMLLog.info("[Brew a Beer] Your version is UpToDate: v" + var5, new Object[0]);
    	                }
    	                else
    	                {
    	                    FMLLog.info("[Brew a Beer] A new version is available: v" + var5, new Object[0]);
    	                    var8.addChatMessage("\u00a72[Brew a Beer]\u00a7r A new version is available: v" + var5);
    	                    var8.addChatMessage("\u00a72[Brew a Beer]\u00a7r New Features of v" + var5 + ": " + var6);
    	                    var8.addChatMessage("\u00a72[Brew a Beer]\u00a7r Your version is: v" + var7);
    	                }
    	            }
    	            var3.close();
    	            var4.close();
    	        }
    	        catch (Exception var9)
    	        {    	            
    	        }
    	      }   			
    		}
    	}
    	else
    	{
    		props.createNewProperty(Minecraft.getAppDir("minecraft")+props.Update, "UpdateCheck", "true", props.UpdateComment);
    		FMLLog.log(BierMod.modID, Level.INFO, "Generating File " + props.Update + " successful");
    	}    	    	    	
    }
    
    /**
     * Check's if Update Server Online
     * @return : Network status
     */
    public static boolean checkUpdateServer()
    {
    	try
    	{   		
    		URL var1 = new URL("http://46.38.239.84/neo/updater/BeerModUpdate");
            URL var2 = new URL("http://46.38.239.84/neo/updater/BeerModUpdateInfo");
            BufferedReader var3 = new BufferedReader(new InputStreamReader(var1.openStream()));
            BufferedReader var4 = new BufferedReader(new InputStreamReader(var2.openStream()));
            return true;
    	}catch (Exception var9)
    	{}
    	return false;	
    }
}
