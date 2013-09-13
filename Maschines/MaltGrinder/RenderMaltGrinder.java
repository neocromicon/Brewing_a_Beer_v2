package assets.brewing_a_beer_v2.Maschines.MaltGrinder;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import assets.brewing_a_beer_v2.Lib.InitHelper;
import cpw.mods.fml.client.FMLClientHandler;

public class RenderMaltGrinder extends TileEntitySpecialRenderer {

	private ModelMaltGrinder aModel;

	public static final ResourceLocation MaltGrinderTexture = new ResourceLocation("/assets/" + InitHelper.MODID + "/textures/blocks/MaltGrinder/modelMaltGrinder.png");

	public RenderMaltGrinder() {
		aModel = new ModelMaltGrinder();
	}

	public void renderAModelAt(TileEntityMaltGrinder tileEnt, double x, double y, double z, float f) {

		int metadata = tileEnt.getBlockMetadata();
		int rotationAngle = 0;
		if (metadata % 4 == 0) {
			rotationAngle = 0;
		}
		if (metadata % 4 == 1) {
			rotationAngle = 270;
		}
		if (metadata % 4 == 2) {
			rotationAngle = 180;
		}
		if (metadata % 4 == 3) {
			rotationAngle = 90;
		}

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);

		GL11.glRotatef(180, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(rotationAngle, 0.0F, 1.0F, 0.0F);

		//FMLClientHandler.instance().getClient().renderEngine.func_110577_a(MaltGrinderTexture);
		GL11.glPushMatrix();
		aModel.renderModel(0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		renderAModelAt((TileEntityMaltGrinder) tileentity, x, y, z, f);
	}
}