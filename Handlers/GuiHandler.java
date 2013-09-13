package assets.brewing_a_beer_v2.Handlers;

import assets.brewing_a_beer_v2.BierMod;
import assets.brewing_a_beer_v2.Maschines.BrewingTable.ContainerBrewingTable;
import assets.brewing_a_beer_v2.Maschines.BrewingTable.GUIBrewingTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
		switch (id) {
		case 0:
			return id == 0
					&& world.getBlockId(x, y, z) == BierMod.IngredientsTable.blockID ? new ContainerBrewingTable(
					player.inventory, world, x, y, z) : null;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);
		switch (id) {
		case 0:
			return id == 0
					&& world.getBlockId(x, y, z) == BierMod.IngredientsTable.blockID ? new GUIBrewingTable(
					player.inventory, world, x, y, z) : null;
		}
		return null;
	}
}