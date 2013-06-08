package mods.Brewing_a_Beer_v2.Handlers;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import mods.Brewing_a_Beer_v2.BierMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PacketHandler implements IPacketHandler
{
    public static Packet250CustomPayload sendDrunk(int var0, int var1, int var2, int var3)
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
            ;
        }

        Packet250CustomPayload var6 = new Packet250CustomPayload();
        var6.channel = "BeerModChannel";
        var6.data = var4.toByteArray();
        var6.length = var4.size();
        return var6;
    }

    public void onPacketData(INetworkManager var1, Packet250CustomPayload var2, Player var3)
    {
        EntityPlayer var4 = (EntityPlayer)var3;
        ByteArrayDataInput var5 = ByteStreams.newDataInput(var2.data);
        int var6 = var5.readInt();
        int var7 = var5.readInt();
        int var8 = var5.readInt();

        if (var6 == 1)
        {
            var4.removePotionEffect(Potion.confusion.id);
            var4.removePotionEffect(Potion.hunger.id);
            /*var4.removePotionEffect(BierMod.DrunkEffekt.id);
            var4.addPotionEffect(new PotionEffect(BierMod.DrunkEffekt.id, var7, 0));*/
            var4.addPotionEffect(new PotionEffect(Potion.digSpeed.id, var7, 0));
            var4.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, var7, 0));
            var4.addPotionEffect(new PotionEffect(Potion.damageBoost.id, var7, 0));
            var4.addPotionEffect(new PotionEffect(Potion.resistance.id, var7, 0));
        }

        if (var6 == 2)
        {
            var4.addPotionEffect(new PotionEffect(Potion.hunger.id, var8, 0));
        }

        if (var6 == 3)
        {
        	/*DrunkEffect.setDrunkLevel(0);
            var4.attackEntityFrom(DamageSourceBeer.causeItemDamage(var4), 1000);*/
            var4.removePotionEffect(Potion.confusion.id);
            var4.removePotionEffect(Potion.hunger.id);
            //var4.removePotionEffect(BierMod.DrunkEffekt.id);
        }
    }
}
