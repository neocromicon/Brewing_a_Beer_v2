package assets.brewing_a_beer_v2.Maschines.MaltGrinder;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMaltGrinder extends ModelBase {
	// fields
	ModelRenderer GrundForm;
	ModelRenderer Trichter1;
	ModelRenderer Trichter2;
	ModelRenderer tSeiteRechts;
	ModelRenderer tSeiteLinks;
	ModelRenderer tSeiteVorne;
	ModelRenderer tSeiteHinten;
	ModelRenderer Kurbel;
	ModelRenderer KurbelStueck;
	ModelRenderer AusgangLinks;
	ModelRenderer AusgangRechts;
	ModelRenderer AusgangVorne;
	ModelRenderer AusgangHinten;

	public ModelMaltGrinder() {
		textureWidth = 256;
		textureHeight = 256;

		GrundForm = new ModelRenderer(this, 0, 0);
		GrundForm.addBox(0F, 0F, 0F, 16, 8, 14);
		GrundForm.setRotationPoint(-8F, 16F, -6F);
		GrundForm.setTextureSize(256, 256);
		GrundForm.mirror = true;
		setRotation(GrundForm, 0F, 0F, 0F);
		Trichter1 = new ModelRenderer(this, 0, 0);
		Trichter1.addBox(0F, 0F, 0F, 2, 1, 2);
		Trichter1.setRotationPoint(2F, 15F, 2F);
		Trichter1.setTextureSize(256, 256);
		Trichter1.mirror = true;
		setRotation(Trichter1, 0F, 0F, 0F);
		Trichter2 = new ModelRenderer(this, 0, 0);
		Trichter2.addBox(0F, 0F, 0F, 4, 2, 4);
		Trichter2.setRotationPoint(1F, 13F, 1F);
		Trichter2.setTextureSize(256, 256);
		Trichter2.mirror = true;
		setRotation(Trichter2, 0F, 0F, 0F);
		tSeiteRechts = new ModelRenderer(this, 0, 0);
		tSeiteRechts.addBox(0F, 0F, 0F, 1, 2, 6);
		tSeiteRechts.setRotationPoint(5F, 11F, 0F);
		tSeiteRechts.setTextureSize(256, 256);
		tSeiteRechts.mirror = true;
		setRotation(tSeiteRechts, 0F, 0F, 0F);
		tSeiteLinks = new ModelRenderer(this, 0, 0);
		tSeiteLinks.addBox(0F, 0F, 0F, 1, 2, 6);
		tSeiteLinks.setRotationPoint(0F, 11F, 0F);
		tSeiteLinks.setTextureSize(256, 256);
		tSeiteLinks.mirror = true;
		setRotation(tSeiteLinks, 0F, 0F, 0F);
		tSeiteVorne = new ModelRenderer(this, 0, 0);
		tSeiteVorne.addBox(0F, 0F, 0F, 4, 2, 1);
		tSeiteVorne.setRotationPoint(1F, 11F, 0F);
		tSeiteVorne.setTextureSize(256, 256);
		tSeiteVorne.mirror = true;
		setRotation(tSeiteVorne, 0F, 0F, 0F);
		tSeiteHinten = new ModelRenderer(this, 0, 0);
		tSeiteHinten.addBox(0F, 0F, 0F, 4, 2, 1);
		tSeiteHinten.setRotationPoint(1F, 11F, 5F);
		tSeiteHinten.setTextureSize(256, 256);
		tSeiteHinten.mirror = true;
		setRotation(tSeiteHinten, 0F, 0F, 0F);
		Kurbel = new ModelRenderer(this, 0, 0);
		Kurbel.addBox(0F, 0F, 0F, 4, 1, 4);
		Kurbel.setRotationPoint(-7F, 15F, -3F);
		Kurbel.setTextureSize(256, 256);
		Kurbel.mirror = true;
		setRotation(Kurbel, 0F, 0.7853982F, 0F);
		KurbelStueck = new ModelRenderer(this, 0, 0);
		KurbelStueck.addBox(0F, 0F, 0F, 1, 4, 1);
		KurbelStueck.setRotationPoint(-6F, 11F, -3F);
		KurbelStueck.setTextureSize(256, 256);
		KurbelStueck.mirror = true;
		setRotation(KurbelStueck, 0F, 0.7853982F, 0F);
		AusgangLinks = new ModelRenderer(this, 0, 0);
		AusgangLinks.addBox(0F, 0F, 0F, 2, 1, 1);
		AusgangLinks.setRotationPoint(-6F, 21F, -7F);
		AusgangLinks.setTextureSize(256, 256);
		AusgangLinks.mirror = true;
		setRotation(AusgangLinks, 0F, 0F, 0F);
		AusgangRechts = new ModelRenderer(this, 0, 0);
		AusgangRechts.addBox(0F, 0F, 0F, 1, 2, 2);
		AusgangRechts.setRotationPoint(-4F, 20F, -8F);
		AusgangRechts.setTextureSize(256, 256);
		AusgangRechts.mirror = true;
		setRotation(AusgangRechts, 0F, 0F, 0F);
		AusgangVorne = new ModelRenderer(this, 0, 0);
		AusgangVorne.addBox(0F, 0F, 0F, 2, 2, 1);
		AusgangVorne.setRotationPoint(-6F, 20F, -8F);
		AusgangVorne.setTextureSize(256, 256);
		AusgangVorne.mirror = true;
		setRotation(AusgangVorne, 0F, 0F, 0F);
		AusgangHinten = new ModelRenderer(this, 0, 0);
		AusgangHinten.addBox(0F, 0F, 0F, 1, 2, 2);
		AusgangHinten.setRotationPoint(-7F, 20F, -8F);
		AusgangHinten.setTextureSize(256, 256);
		AusgangHinten.mirror = true;
		setRotation(AusgangHinten, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		GrundForm.render(f5);
		Trichter1.render(f5);
		Trichter2.render(f5);
		tSeiteRechts.render(f5);
		tSeiteLinks.render(f5);
		tSeiteVorne.render(f5);
		tSeiteHinten.render(f5);
		Kurbel.render(f5);
		KurbelStueck.render(f5);
		AusgangLinks.render(f5);
		AusgangRechts.render(f5);
		AusgangVorne.render(f5);
		AusgangHinten.render(f5);
	}

	public void renderModel(float f5) {
		GrundForm.render(f5);
		Trichter1.render(f5);
		Trichter2.render(f5);
		tSeiteRechts.render(f5);
		tSeiteLinks.render(f5);
		tSeiteVorne.render(f5);
		tSeiteHinten.render(f5);
		Kurbel.render(f5);
		KurbelStueck.render(f5);
		AusgangLinks.render(f5);
		AusgangRechts.render(f5);
		AusgangVorne.render(f5);
		AusgangHinten.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
	}
}