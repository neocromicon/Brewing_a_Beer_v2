package mods.Brewing_a_Beer_v2.Handlers;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import mods.Brewing_a_Beer_v2.BierMod;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.src.ModLoader;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class DrunkHandler extends Potion implements IPacketHandler
{
	boolean sprite;
	public static Potion DrunkEffekt;		
    private static int DrunkLevel = 0;
    public static DrunkHandler instance;

    public DrunkHandler(int var1, boolean var2, int var3, String var4)
    {
        super(var1, var2, var3);
        this.setPotionName(var4);
        this.setEffectiveness(0.25D);
    }

    public DrunkHandler(int var1, boolean var2, int var3, boolean var4, String var5)
    {
        super(var1, var2, var3);
        this.sprite = var4;
    }
	
    public static Packet250CustomPayload getDrunk(int var0, int var1, int var2, int var3)
    {
        ByteArrayOutputStream var4 = new ByteArrayOutputStream();
        DataOutputStream var5 = new DataOutputStream(var4);

        try
        {
            var5.writeInt(var0);
            var5.writeInt(var1);
            var5.writeInt(var2);
            var5.writeInt(var3);
        }
        catch (IOException var7)
        {
        }

        Packet250CustomPayload var6 = new Packet250CustomPayload();
        var6.channel = "BeerModChannel";
        var6.data = var4.toByteArray();
        var6.length = var4.size();
        return var6;
    }

    /**
     * Receive Drunk Data
     */
    @Override
    public void onPacketData(INetworkManager var1, Packet250CustomPayload var2, Player var3)
    {
        EntityPlayer player = (EntityPlayer)var3;
        ByteArrayDataInput getDrunk = ByteStreams.newDataInput(var2.data);
        int var6 = getDrunk.readInt();
        int var7 = getDrunk.readInt();
        int var8 = getDrunk.readInt();

        if (var6 == 1)
        {
        	player.removePotionEffect(Potion.confusion.id);
        	player.removePotionEffect(Potion.hunger.id);
        	player.removePotionEffect(DrunkEffekt.id);
        	player.addPotionEffect(new PotionEffect(DrunkEffekt.id, var7, 0));
        	player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, var7, 0));
        	player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, var7, 0));
        	player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, var7, 0));
        	player.addPotionEffect(new PotionEffect(Potion.resistance.id, var7, 0));
        }

        if (var6 == 2)
        {
        	player.addPotionEffect(new PotionEffect(Potion.hunger.id, var8, 0));
        }

        if (var6 == 3)
        {
        	this.setDrunkLevel(0);
            //player.attackEntityFrom(DamageSourceBeer.causeItemDamage(var4), 1000);
        	player.removePotionEffect(Potion.confusion.id);
        	player.removePotionEffect(Potion.hunger.id);
        	player.removePotionEffect(DrunkEffekt.id);
        }
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
