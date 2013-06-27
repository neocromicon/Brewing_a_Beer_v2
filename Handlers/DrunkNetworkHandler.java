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

public class DrunkNetworkHandler implements IPacketHandler
{
	boolean sprite;
	
    public static DrunkNetworkHandler instance;
    public static DrunkHandler DrunkEffekt = DrunkHandler.instance;

	
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
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player p) {
        EntityPlayer player = (EntityPlayer)p;
        ByteArrayDataInput getDrunk = ByteStreams.newDataInput(packet.data);
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
        	DrunkEffekt.setDrunkLevel(0);
            //player.attackEntityFrom(DamageSourceBeer.causeItemDamage(var4), 1000);
        	player.removePotionEffect(Potion.confusion.id);
        	player.removePotionEffect(Potion.hunger.id);
        	player.removePotionEffect(DrunkEffekt.id);
        }
    }
}
