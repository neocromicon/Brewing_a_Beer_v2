package assets.brewing_a_beer_v2.Maschines.MaltGrinder;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

public class ItemGaerTank implements IItemRenderer {

	private ModelMaltGrinder modelMaltGrinder;

	public ItemGaerTank() {

		modelMaltGrinder = new ModelMaltGrinder();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {

		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {

		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		TileEntityRenderer.instance.renderTileEntityAt(new TileEntityMaltGrinder(), 0.0D, 0.0D, 0.0D, 0.0F);
	}
}