package littleMaidMobX.model.maids;import littleMaidMobX.Helper;import littleMaidMobX.entity.EntityLittleMaid;import littleMaidMobX.model.caps.IModelCaps;import littleMaidMobX.model.caps.ModelCapsHelper;import littleMaidMobX.model.modchu.ModelModchuBaseSR2;import littleMaidMobX.render.model.ModelRenderer;public class MultiModel_NM extends ModelModchuBaseSR2 {
	public ModelRenderer hairR;
	public ModelRenderer hairL;
	public ModelRenderer Cachusya;
	public ModelRenderer Zukin;
	public ModelRenderer Ribon1R;
	public ModelRenderer Ribon1L;
	public ModelRenderer Ribon2R;
	public ModelRenderer Ribon2L;
	public ModelRenderer Bribonc;
	public ModelRenderer Bribon1R;
	public ModelRenderer Bribon1L;
	public ModelRenderer Bribon2R;
	public ModelRenderer Bribon2L;
	public ModelRenderer Breast1;
	public ModelRenderer Cheek_R;
	public ModelRenderer Cheek_L;
	public ModelRenderer SideTail1R;
	public ModelRenderer SideTail1L;
	public ModelRenderer sensor;
	public ModelRenderer RightArm1;
	public ModelRenderer RightArm2;
	public ModelRenderer RightArm3;
	public ModelRenderer LeftArm1;
	public ModelRenderer LeftArm2;
	public ModelRenderer LeftArm3;
	public ModelRenderer tie;
	public ModelRenderer bow;
	public ModelRenderer BodyA;
	public ModelRenderer BreastA;
	public ModelRenderer RightLeg;
	public ModelRenderer LeftLeg;	public MultiModel_NM() {
		this(0.0F);
	}	public MultiModel_NM(float f) {
		this(f, 0.0F);
	}	public MultiModel_NM(float f, float f1) {
		this(f, f1, 64, 64);
	}	public MultiModel_NM(float f, float f1, int i, int j) {
		super(f, f1, i < 0 ? 64 : i, j < 0 ? 64 : j);
	}	@Override
	public void initModel(float f, float f1, boolean isAfterInit) {
		textureWidth = 64;
		textureHeight = 64;
		f1 += 8F;
		bipedHead = new ModelRenderer(this, 0, 0);
		bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, f);
		bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedHeadwear = new ModelRenderer(this, 24, 0);
		bipedHeadwear.addBox(-4.0F, 0.0F, 1.0F, 8, 5, 3, f);
		bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
		Zukin = new ModelRenderer(this, 0, 32);
		Zukin.addBox(-4F, -8.1F, -4F, 8, 8, 9, f + 0.1F);
		Zukin.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody = new ModelRenderer(this, 32, 8);
		bipedBody.addBox(-3.0F, -3.5F, -2.0F, 6, 7, 4, f);
		bipedBody.setRotationPoint(0.0F, -3.0F, 0.0F);
		tie = new ModelRenderer(this, 0, 6);
		tie.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, f + 0.1F);
		tie.setRotationPoint(-0.5F, -3.5F, -2.1F);
		bow = new ModelRenderer(this, 40, 42);
		bow.addBox(2.0F, -2F, 2.0F, 2, 6, 2, f);
		bow.setRotationPoint(0.0F, 0.0F, 0.0F);
		bow.setRotateAngleZ(0.837758F);
		bipedRightArm = new ModelRenderer(this, 48, 0);
		bipedRightArm.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
		bipedRightArm.setRotationPoint(-3.0F, 4.5F, 0.0F);
		RightArm1 = new ModelRenderer(this, 48, 42);
		RightArm1.addBox(1.0F, 3F, -1F, 2, 1, 2, f + 0.4F);
		RightArm1.setRotationPoint(-2.0F, 1.0F, 0.0F);
		RightArm2 = new ModelRenderer(this, 48, 45);
		RightArm2.addBox(1.0F, 4F, -1F, 2, 3, 2, f + 0.2F);
		RightArm2.setRotationPoint(-2.0F, 1.0F, 0.0F);
		RightArm3 = new ModelRenderer(this, 48, 50);
		RightArm3.addBox(1.0F, 3F, -1F, 2, 4, 2, f + 0.2F);
		RightArm3.setRotationPoint(-2.0F, 1.0F, 0.0F);
		bipedLeftArm = new ModelRenderer(this, 56, 0);
		bipedLeftArm.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
		bipedLeftArm.setRotationPoint(3.0F, 4.5F, 0.0F);
		LeftArm1 = new ModelRenderer(this, 56, 42);
		LeftArm1.addBox(-3F, 4F, -1F, 2, 1, 2, f + 0.4F);
		LeftArm1.setRotationPoint(2.0F, 1.0F, 0.0F);
		LeftArm2 = new ModelRenderer(this, 56, 45);
		LeftArm2.addBox(-3F, 4F, -1F, 2, 3, 2, f + 0.2F);
		LeftArm2.setRotationPoint(2.0F, 1.0F, 0.0F);
		LeftArm3 = new ModelRenderer(this, 56, 50);
		LeftArm3.addBox(-3F, 3F, -1F, 2, 4, 2, f + 0.2F);
		LeftArm3.setRotationPoint(2.0F, 1.0F, 0.0F);
		bipedRightLeg = new ModelRenderer(this, 32, 19);
		bipedRightLeg.addBox(-1.5F, 0.0F, -2.0F, 3, 9, 4, f);
		bipedRightLeg.setRotationPoint(-1.5F, 15F, 0.0F);
		bipedLeftLeg = new ModelRenderer(this, 32, 19);
		bipedLeftLeg.mirror = true;
		bipedLeftLeg.addBox(-1.5F, 0.0F, -2.0F, 3, 9, 4, f);
		bipedLeftLeg.setRotationPoint(1.5F, 15F, 0.0F);
		Skirt = new ModelRenderer(this, 0, 16);
		Skirt.addBox(-4F, -4F, -4F, 8, 8, 8, f);
		Skirt.setRotationPoint(0.0F, 4.0F, 0.0F);
		ChignonR = new ModelRenderer(this, 25, 32);
		ChignonR.addBox(-5F, -7F, -0.5F, 1, 3, 3, f);
		ChignonR.setRotationPoint(0.0F, 0.0F, 0.0F);
		ChignonL = new ModelRenderer(this, 25, 32);
		ChignonL.addBox(4F, -7F, -0.5F, 1, 3, 3, f);
		ChignonL.setRotationPoint(0.0F, 0.0F, 0.0F);
		SideTailR = new ModelRenderer(this, 52, 10);
		SideTailR.addBox(-5.6F, -6.8F, 0.0F, 1, 7, 2, f);
		SideTailR.setRotationPoint(0.0F, 0.0F, 0.0F);
		SideTailR.setRotateAngleZ(0.0872665F);
		SideTailL = new ModelRenderer(this, 58, 10);
		SideTailL.mirror = true;
		SideTailL.addBox(4.6F, -6.8F, 0.0F, 1, 7, 2, f);
		SideTailL.setRotationPoint(0.0F, 0.0F, 0.0F);
		SideTailL.setRotateAngleZ(0.0872665F);
		SideTail1R = new ModelRenderer(this, 34, 32);
		SideTail1R.addBox(-8F, -7F, 0.0F, 3, 7, 2, f);
		SideTail1R.setRotationPoint(0.0F, 0.0F, 0.0F);
		SideTail1R.setRotateAngleZ(0.122173F);
		SideTail1L = new ModelRenderer(this, 44, 32);
		SideTail1L.addBox(5F, -7F, 0.0F, 3, 7, 2, f);
		SideTail1L.setRotationPoint(0.0F, 0.0F, 0.0F);
		SideTail1L.setRotateAngleZ(-0.122173F);
		hairR = new ModelRenderer(this, 52, 19);
		hairR.addBox(-4F, 0.0F, -4F, 1, 3, 2, f);
		hairR.setRotationPoint(0.0F, 0.0F, 0.0F);
		hairL = new ModelRenderer(this, 58, 19);
		hairL.addBox(3F, 0.0F, -4F, 1, 3, 2, f);
		hairL.setRotationPoint(0.0F, 0.0F, 0.0F);
		Cachusya = new ModelRenderer(this, 24, 23);
		Cachusya.addPlate(-2F, -9F, -4F, 4, 1, 0, f);
		Cachusya.setRotationPoint(0.0F, 0.0F, 0.0F);
		Ribon1R = new ModelRenderer(this, 52, 24);
		Ribon1R.addPlate(0.0F, -6F, -4.7F, 3, 6, 0, f);
		Ribon1R.setRotationPoint(0.0F, 0.0F, 0.0F);
		Ribon1R.setRotateAngleX(-0.0872665F);
		Ribon1R.setRotateAngleY(((float) Math.PI / 2F));
		Ribon1L = new ModelRenderer(this, 58, 24);
		Ribon1L.addPlate(-3F, -6F, -4.7F, 3, 6, 0, f);
		Ribon1L.setRotationPoint(0.0F, 0.0F, 0.0F);
		Ribon1L.setRotateAngleX(-0.0872665F);
		Ribon1L.setRotateAngleY(-((float) Math.PI / 2F));
		Bribonc = new ModelRenderer(this, 0, 20);
		Bribonc.addBox(-1F, 4.6F, 3F, 2, 2, 2, f);
		Bribonc.setRotationPoint(0.0F, 0.0F, 0.0F);
		Bribon1R = new ModelRenderer(this, 24, 19);
		Bribon1R.addPlate(-4F, 4.6F, 4.7F, 3, 3, 0, f);
		Bribon1R.setRotationPoint(0.0F, 0.0F, 0.0F);
		Bribon1L = new ModelRenderer(this, 24, 16);
		Bribon1L.addPlate(1.0F, 4.6F, 4.7F, 3, 3, 0, f);
		Bribon1L.setRotationPoint(0.0F, 0.0F, 0.0F);
		Bribon2R = new ModelRenderer(this, 46, 24);
		Bribon2R.addPlate(-4F, 5.6F, 4.3F, 3, 5, 0, f);
		Bribon2R.setRotationPoint(0.0F, 0.0F, 0.0F);
		Bribon2L = new ModelRenderer(this, 46, 19);
		Bribon2L.addPlate(1.0F, 5.6F, 4.3F, 3, 5, 0, f);
		Bribon2L.setRotationPoint(0.0F, 0.0F, 0.0F);
		Breast1 = new ModelRenderer(this, 0, 32);
		Breast1.addBox(0.0F, -2.5F, 0.0F, 2, 5, 1, f);
		Breast1.setRotationPoint(0.0F, -3.0F, -2F);
		Breast1.setRotateAngleZ(((float) Math.PI / 2F));
		Breast1.setRotateAngleY(0.4363323F);
		eyeR = new ModelRenderer(this, 0, 16);
		eyeR.addPlate(-4.0F, -5.0F, -4.001F, 4, 4, 0, f);
		eyeR.setRotationPoint(0.0F, 0.0F, 0.0F);
		eyeL = new ModelRenderer(this, 4, 16);
		eyeL.addPlate(0.0F, -5.0F, -4.001F, 4, 4, 0, f);
		eyeL.setRotationPoint(0.0F, 0.0F, 0.0F);
		Cheek_R = new ModelRenderer(this, 32, 8);
		Cheek_R.addPlate(-4.5F, -4.5F, -4.002F, 4, 4, 0, f);
		Cheek_R.setRotationPoint(0.0F, 1.0F, 0.0F);
		Cheek_L = new ModelRenderer(this, 32, 19);
		Cheek_L.addPlate(0.5F, -4.5F, -4.002F, 4, 4, 0, f);
		Cheek_L.setRotationPoint(0.0F, 1.0F, 0.0F);
		sensor = new ModelRenderer(this, 0, 49);
		sensor.addPlate(0.0F, -4F, -10F, 10, 4, 1);
		sensor.setRotationPoint(0.0F, -8.0F, 0.0F);
		BodyA = new ModelRenderer(this, 0, 53);
		BodyA.addBox(-3F, 0.0F, -2F, 6, 7, 4, f + 0.1F);
		BodyA.setRotationPoint(0.0F, 0.0F, 0.0F);
		BreastA = new ModelRenderer(this, 34, 42);
		BreastA.addBox(0.0F, -2.5F, 0.0F, 2, 5, 1, f + 0.1F);
		BreastA.setRotationPoint(0.0F, 1.0F, -2F);
		BreastA.setRotateAngleZ(((float) Math.PI / 2F));
		BreastA.setRotateAngleY(0.4363323F);
		RightLeg = new ModelRenderer(this, 20, 49);
		RightLeg.addBox(-1F, 1.0F, -2F, 3, 8, 4, f + 0.1F);
		RightLeg.setRotationPoint(-1F, 0.0F, 0.0F);
		LeftLeg = new ModelRenderer(this, 20, 49);
		LeftLeg.mirror = true;
		LeftLeg.addBox(-2F, 1.0F, -2F, 3, 8, 4, f + 0.1F);
		LeftLeg.setRotationPoint(1.0F, 0.0F, 0.0F);
		mainFrame = new ModelRenderer(this, 0, 0);
		mainFrame.setRotationPoint(0F, 0F + f1, 0F);
		if (isAfterInit) afterInit(f, f1);
	}	@Override
	public void skirtFloatsInit(float f, float f1) {
		if (ModelCapsHelper.getCapsValueInt(this, null, caps_skirtFloats) < 2) return;
		textureWidth = 64;
		textureHeight = 64;
		super.skirtFloatsInit(f, f1);
	}	@Override
	public void defaultAddChildSetting() {
		super.defaultAddChildSetting();
		bipedHead.addChild(Ribon1R);
		bipedHead.addChild(Ribon1L);
		bipedHead.addChild(Cachusya);
		bipedHead.addChild(SideTail1R);
		bipedHead.addChild(SideTail1L);
		bipedHead.addChild(Zukin);
		bipedHead.addChild(hairR);
		bipedHead.addChild(hairL);
		bipedHead.addChild(eyeR);
		bipedHead.addChild(eyeL);
		bipedHead.addChild(Cheek_R);
		bipedHead.addChild(Cheek_L);
		bipedHead.addChild(sensor);
		bipedBody.addChild(Bribonc);
		bipedBody.addChild(Bribon1R);
		bipedBody.addChild(Bribon1L);
		bipedBody.addChild(Bribon2R);
		bipedBody.addChild(Bribon2L);
		bipedBody.addChild(tie);
		bipedBody.addChild(bow);
		bipedBody.addChild(BodyA);
		bipedBody.addChild(BreastA);
		bipedBody.addChild(Breast1);
		bipedRightArm.addChild(RightArm1);
		bipedRightArm.addChild(RightArm2);
		bipedRightArm.addChild(RightArm3);
		bipedLeftArm.addChild(LeftArm1);
		bipedLeftArm.addChild(LeftArm2);
		bipedLeftArm.addChild(LeftArm3);
		bipedLeftLeg.addChild(LeftLeg);
		bipedRightLeg.addChild(RightLeg);
	}	@Override	public void setDefaultPause(float f, float f1, float f2, float f3, float f4, float f5, IModelCaps entityCaps) {		super.setDefaultPause(f, f1, f2, f3, f4, f5, entityCaps);		Breast1.setRotationPoint(0.0F, -3.0F, -2F);		BreastA.setRotationPoint(0.0F, 1.0F, -2F);	}	@Override
	public void setLivingAnimationsLM(IModelCaps entityCaps, float f, float f1, float f2) {
		super.setLivingAnimationsLM(entityCaps, f, f1, f2);
		Object entityliving = entityCaps.getCapsValue(entityCaps.caps_Entity);
		if (entityliving != null); else return;
		int ticksExisted = Helper.getEntityTicksExisted(entityliving);
		float f3 = (float)ticksExisted + f2 + ModelCapsHelper.getCapsValueFloat(this, entityCaps, caps_entityIdFactor);
		if (!ModelCapsHelper.getCapsValueBoolean(this, entityCaps, caps_aimedBow)) {
			boolean isWorkingDelay = ModelCapsHelper.getCapsValueBoolean(this, entityCaps, caps_isWorkingDelay);
			if (isWorkingDelay) {
//-@-125
				int maidMode = 0;				if (entityliving instanceof EntityLittleMaid) {					maidMode = ((EntityLittleMaid)entityliving).getMaidModeInt();				}
				int i = 0x0080;
				int i1 = 0x0021;
				int i2 = 0x0083;
//@-@125
/*//125delete
				int maidMode = (Integer)Modchu_Reflect.getFieldObject(LMM_EntityLittleMaid, "getMaidMode", entityliving);
				int i = 4;
				int i1 = 6;
				int i2 = 8;
*///125delete
				RightArm1.setVisible(false);
				LeftArm1.setVisible(false);
				RightArm2.setVisible(false);
				LeftArm2.setVisible(false);
				RightArm3.setVisible(false);
				LeftArm3.setVisible(false);
				Zukin.setVisible(false);
				sensor.setVisible(true);
				Cachusya.setVisible(true);
				RightLeg.setVisible(false);
				LeftLeg.setVisible(false);
				BodyA.setVisible(false);
				BreastA.setVisible(false);
				bow.setVisible(false);
				if (maidMode == i) {
					f3 *= 8F;
					//float f4 = -0.2F;
					RightArm1.setVisible(true);
					LeftArm1.setVisible(true);
					RightArm2.setVisible(false);
					LeftArm2.setVisible(false);
					RightArm3.setVisible(true);
					LeftArm3.setVisible(true);
					BodyA.setVisible(true);
					RightLeg.setVisible(true);
					LeftLeg.setVisible(true);
				}
				if (maidMode == i1) {
					f3 *= 8F;
					float f5 = -0.2F;
					RightArm2.setVisible(true);
					LeftArm2.setVisible(true);
					RightArm3.setVisible(false);
					LeftArm3.setVisible(false);
					Zukin.setVisible(true);
					sensor.setVisible(false);
					Cachusya.setVisible(false);
				}
				if (maidMode == i2) {
					f3 *= 8F;
					float f6 = -0.2F;
					RightArm2.setVisible(false);
					LeftArm2.setVisible(false);
					RightArm3.setVisible(true);
					LeftArm3.setVisible(true);
					BodyA.setVisible(true);
					bow.setVisible(true);
					RightLeg.setVisible(true);
					LeftLeg.setVisible(true);
					BreastA.setVisible(true);
				}
			}
		}
		float f7 = 0.0F;
		if (ModelCapsHelper.getCapsValueBoolean(this, entityCaps, caps_isLookSuger)) {
			f3 *= 8F;
			f7 = -0.2F;
			Cheek_R.setVisible(true);
			Cheek_L.setVisible(true);
		} else {
			f7 = (1.0F - ModelCapsHelper.getCapsValueInt(this, entityCaps, caps_health) / 20F) * 0.5F;
			Cheek_R.setVisible(false);
			Cheek_L.setVisible(false);
		}		//float f8 = mh_sin(f3 * 0.067F) * 0.05F - f7;
		//float f9 = ((float)Math.PI * 2F / 9F);
		sensor.setRotateAngle(mh_sin(f3 * 0.067F) * 0.05F + f7, mh_cos(f3 * 0.09F) * 0.5F, mh_sin(f3 * 0.09F) * 0.2F);
	}
}
