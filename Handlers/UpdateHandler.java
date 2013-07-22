package assets.Brewing_a_Beer_v2.Handlers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;

import assets.Brewing_a_Beer_v2.BierMod;
import assets.Brewing_a_Beer_v2.ClientProxy;
import assets.Brewing_a_Beer_v2.ServerProxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class UpdateHandler extends Thread
{
	private PropertyHandler props = PropertyHandler.instance;
    private ServerProxy sProxy;
    private ClientProxy cProxy;
    
    /**
     * Start Update Checker for the SERVER Side
     */
    @SideOnly(Side.SERVER)
    public void CheckUpdateServer()
    {   	
    	if (props.isProperty(sProxy.UpdateServer))
    	{
    		String checkProp = props.getProperty(sProxy.UpdateServer, "UpdateCheck");
    		if (checkProp.endsWith("true"))
    		{
    			props.setProperty(sProxy.UpdateServer, "UpdateCheck", "false", sProxy.UpdateCommentServer);
    			if(checkUpdateServer() == false)
    	    	{
    	    		FMLLog.log(BierMod.modID, Level.INFO, "Update Server is Offline!");
    	    	}
    	    	else
    	    	{ try {
    	    			URL urlVersion = new URL("http://46.38.239.84/neo/updater/BeerModUpdate");
    	    			URL urlInfo = new URL("http://46.38.239.84/neo/updater/BeerModUpdateInfo");
    	    			BufferedReader readerVersion = new BufferedReader(new InputStreamReader(urlVersion.openStream()));
    	    			BufferedReader readerInfo = new BufferedReader(new InputStreamReader(urlInfo.openStream()));
    	    			
    	    			String HoleDaten;
    	    			String ModInfo = readerInfo.readLine();
    	    			String ModVersion = BierMod.modVersion;
    	    		    while ((HoleDaten = readerVersion.readLine()) != null) {
    	    				if (HoleDaten.endsWith(ModVersion)) {
    	    					FMLLog.log(BierMod.modID, Level.INFO, "Your version is UpToDate: v" + HoleDaten);
    	    				} else {
    	    					FMLLog.log(BierMod.modID, Level.INFO, "A new version is available: v" + HoleDaten);
    	                    	FMLLog.log(BierMod.modID, Level.INFO, "New Features of v" + HoleDaten + ": " + ModInfo);
    	                    	FMLLog.log(BierMod.modID, Level.INFO, "Your version is: v" + ModVersion);
    	    				}
    	    			}
    	    		    readerVersion.close();
    	    		    readerInfo.close();    	    			
    	    		} catch (Exception e) {
    	    			e.printStackTrace();
    	    		}   		
    	    	}     
    		}
    	}
    	else
    	{ 
    		props.createNewProperty(sProxy.UpdateServer, "UpdateCheck", "true", sProxy.UpdateCommentServer);
    		FMLLog.log(BierMod.modID, Level.INFO, "Generating File " + sProxy.UpdateServer + " successful");
    	}
    	
    }

    /**
     * Start Update Checker for the CLIENT Side
     */
    @SideOnly(Side.CLIENT)
    public void CheckUpdateClient()
    {
    	Minecraft mc = Minecraft.getMinecraft();
    	if (props.isProperty(mc.mcDataDir+cProxy.Update))
    	{
    		String checkProp = props.getProperty(mc.mcDataDir+cProxy.Update, "UpdateCheck");
    		if (checkProp.endsWith("true"))
    		{
    			EntityClientPlayerMP player = FMLClientHandler.instance().getClient().thePlayer;
    			props.setProperty(mc.mcDataDir+cProxy.Update, "UpdateCheck", "false", cProxy.UpdateComment);
    	    	if(checkUpdateServer() == false)
    	    	{
    	    		player.addChatMessage("\u00a72[Brew a Beer]\u00a7r Update Server is Offline!");
    	    	}
    	    	else
    	    	{ try {
    	            URL urlVersion = new URL("http://46.38.239.84/neo/updater/BeerModUpdate");
    	            URL urlInfo = new URL("http://46.38.239.84/neo/updater/BeerModUpdateInfo");
    	            BufferedReader readerVersion = new BufferedReader(new InputStreamReader(urlVersion.openStream()));
    	            BufferedReader readerInfo = new BufferedReader(new InputStreamReader(urlInfo.openStream()));
    	            String updateInfo = readerInfo.readLine();
    	            String clientVersion = BierMod.modVersion;
    	            
    	            String updateVersion;

    	            while ((updateVersion = readerVersion.readLine()) != null)
    	            {
    	                if (updateVersion.endsWith(clientVersion))
    	                {
    	                    FMLLog.info("[Brew a Beer] Your version is UpToDate: v" + updateVersion);
    	                }
    	                else
    	                {
    	                    FMLLog.info("[Brew a Beer] A new version is available: v" + updateVersion);
    	                    player.addChatMessage("\u00a72[Brew a Beer]\u00a7r A new version is available: v" + updateVersion);
    	                    player.addChatMessage("\u00a72[Brew a Beer]\u00a7r New Features of v" + updateVersion + ": " + updateInfo);
    	                    player.addChatMessage("\u00a72[Brew a Beer]\u00a7r Your version is: v" + clientVersion);
    	                }
    	            }
    	            readerVersion.close();
    	            readerInfo.close();
    	        }
    	        catch (Exception e) {
    	        	e.printStackTrace();
    	        }
    	      }   			
    	   }
    	}
    	else
    	{
    		props.createNewProperty(mc.mcDataDir+cProxy.Update, "UpdateCheck", "true", cProxy.UpdateComment);
    		FMLLog.log(BierMod.modID, Level.INFO, "Generating File " + cProxy.Update + " successful");
    	}    	    	    	
    }
    
    /**
     * Check's if Update Server Online
     * @return : Network status
    */    
	public static boolean checkUpdateServer() {
		try {
			InetAddress address = InetAddress.getByName("46.38.239.84");
			return true;
		} catch (UnknownHostException e) {
			System.err.println("Unable to lookup 46.38.239.84");
			e.printStackTrace();
		}
		return false;
	}
}
