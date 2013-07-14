package assets.Brewing_a_Beer_v2.Maschines.MaltGrinder;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMaltGrinder extends BlockContainer {
	private Class entityClass;

	public BlockMaltGrinder(int par1, Class tClass) {
		super(par1, Material.wood);
		entityClass = tClass;
	}

	public TileEntity getBlockEntity() {
		try {
			return (TileEntity) entityClass.newInstance();
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}
	}

	public int idDropped(int i, Random rand, int j) {
		return 0;
	}

	public int quantityDropped(Random rand) {
		return 1;
	}

	public int getRenderType() {
		return -1;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public TileEntity createNewTileEntity(World world) {
		return new TileEntityMaltGrinder();
	}

	public boolean hasTileEntity(int metadata) {
		return true;

	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		return true;
	}

	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int par5) {
	}
}