package littleMaidMobX.model.maids;
public class MultiModel_VUD1 extends ModelModchuBaseAug {
	public ModelRenderer SideTailRoot_R;
	public ModelRenderer SideTailRoot_L;
	public ModelRenderer SideTailM_RU;
	public ModelRenderer SideTailM_RB;
	public ModelRenderer SideTailM_LU;
	public ModelRenderer SideTailM_LB;
	public ModelRenderer SideTailF_RU;
	public ModelRenderer SideTailF_RB;
	public ModelRenderer SideTailF_LU;
	public ModelRenderer SideTailF_LB;
	public ModelRenderer SideTailN_LU;
	public ModelRenderer SideTailN_LB;
	public ModelRenderer SideTailR_R;
	public ModelRenderer SideTailR_L;
	public ModelRenderer Tail_T;
	public ModelRenderer Tail_U;
	public ModelRenderer Tail_B;
	public ModelRenderer DropTail;
	public ModelRenderer Roll1_R;
	public ModelRenderer Roll2_R;
	public ModelRenderer Roll3_R;
	public ModelRenderer Roll4_R;
	public ModelRenderer Roll1_L;
	public ModelRenderer Roll2_L;
	public ModelRenderer Roll3_L;
	public ModelRenderer Roll4_L;
	public ModelRenderer DumplingHari_L;
	public ModelRenderer HairOrnamentM_R;
	public ModelRenderer HairOrnamentM_L;
	public ModelRenderer HairOrnamentF_R;
	public ModelRenderer HairOrnamentF_L;
	public ModelRenderer HairOrnamentN_L;
	public ModelRenderer Ribon1_R;
	public ModelRenderer Ribon2_R;
	public ModelRenderer Ribon3_R;
	public ModelRenderer Ribon4_R;
	public ModelRenderer Ribon5_R;
	public ModelRenderer Ribon1_L;
	public ModelRenderer Ribon2_L;
	public ModelRenderer Ribon3_L;
	public ModelRenderer Ribon4_L;
	public ModelRenderer Ribon5_L;
	public ModelRenderer Ribon1_BU;
	public ModelRenderer Ribon2_BU;
	public ModelRenderer Ribon3_BU;
	public ModelRenderer Ribon4_BU;
	public ModelRenderer Ribon5_BU;
	public ModelRenderer Ribon1_BB;
	public ModelRenderer Ribon2_BB;
	public ModelRenderer Ribon3_BB;
	public ModelRenderer Ribon4_BB;
	public ModelRenderer Ribon5_BB;
	public ModelRenderer Ribon1_T;
	public ModelRenderer Ribon2_T;
	public ModelRenderer Ribon3_T;
	public ModelRenderer Headwear_F;
	public ModelRenderer Headwear_B;
	public ModelRenderer Headwear_R;
	public ModelRenderer Headwear_L;
	public ModelRenderer Shaggy_F;
	public ModelRenderer LongHair_F;
	public ModelRenderer LongHair_B;
	public ModelRenderer LongHair_R;
	public ModelRenderer LongHair_L;
	public ModelRenderer Cheek_R;
	public ModelRenderer Cheek_L;
	public ModelRenderer Body_F;
	public ModelRenderer Body_B;
	public ModelRenderer Breast1;
	public ModelRenderer Breast2;
	public ModelRenderer Breast3;
	public ModelRenderer Belt_R;
	public ModelRenderer Belt_L;
	public ModelRenderer Number;
	public ModelRenderer Negi1;
	public ModelRenderer Negi2;
	public ModelRenderer Negi3;
		this(0.0F);
	}
		this(f, 0.0F);
	}
		this(f, f1, 64, 64);
	}
		super(f, f1, i < 0 ? 64 : i, j < 0 ? 64 : j);
	}
	public void initModel(float f, float f1, boolean isAfterInit) {
		textureWidth = 64;
		textureHeight = 64;
		f1 += 8F;
		bipedHead = new ModelRenderer(this, 0, 0);
		bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, f);
		bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		bipedBody = new ModelRenderer(this, 32, 8);
		bipedBody.addBox(-3.0F, -3.5F, -2.0F, 6, 7, 4, f);
		bipedBody.setRotationPoint(0.0F, -3.0F, 0.0F);
		bipedRightArm = new ModelRenderer(this, 48, 0);
		bipedRightArm.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
		bipedRightArm.setRotationPoint(-3.0F, 4.5F, 0.0F);
		bipedLeftArm = new ModelRenderer(this, 56, 0);
		bipedLeftArm.addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
		bipedLeftArm.setRotationPoint(3.0F, 4.5F, 0.0F);
		bipedRightLeg = new ModelRenderer(this, 32, 19);
		bipedRightLeg.addBox(-1.5F, 0.0F, -2.0F, 3, 9, 4, f);
		bipedRightLeg.setRotationPoint(-1.5F, 15F, 0.0F);
		bipedLeftLeg = new ModelRenderer(this, 46, 19);
		bipedLeftLeg.addBox(-1.5F, 0.0F, -2.0F, 3, 9, 4, f);
		bipedLeftLeg.setRotationPoint(1.5F, 15F, 0.0F);
		Skirt = new ModelRenderer(this, 2, 18);
		Skirt.addBox(-4F, -4F, -3F, 8, 8, 6, f);
		Skirt.setRotationPoint(0.0F, 0.0F, 0.0F);
		SideTailR = new ModelRenderer(this);
		SideTailL = new ModelRenderer(this);
		SideTailRoot_R = new ModelRenderer(this, 54, 40);
		SideTailRoot_R.addBox(-1F, -0.5F, -0.5F, 1, 1, 1, f + 0.15F);
		SideTailRoot_R.setRotationPoint(-4F, -7F, 2.0F);
		SideTailRoot_L = new ModelRenderer(this, 54, 40);
		SideTailRoot_L.mirror = true;
		SideTailRoot_L.addBox(0.0F, -0.5F, -0.5F, 1, 1, 1, f + 0.15F);
		SideTailRoot_L.setRotationPoint(4F, -7F, 2.0F);
		SideTailM_RU = new ModelRenderer(this, 56, 40);
		SideTailM_RU.addBox(-2F, -1F, -1F, 2, 10, 2, f - 0.3F);
		SideTailM_RU.setRotationPoint(-4.8F, -7F, 2.0F);
		SideTailM_RU.setRotateAngleZ(0.087266F);
		SideTailM_RB = new ModelRenderer(this, 56, 52);
		SideTailM_RB.addBox(-1.5F, 0.0F, -1F, 2, 10, 2, f);
		SideTailM_RB.setRotationPoint(-0.5F, 8.5F, 0.0F);
		SideTailM_RB.setRotateAngleZ(0.087266F);
		SideTailM_LU = new ModelRenderer(this, 56, 40);
		SideTailM_LU.mirror = true;
		SideTailM_LU.addBox(0.0F, -1F, -1F, 2, 10, 2, f - 0.3F);
		SideTailM_LU.setRotationPoint(4.8F, -7F, 2.0F);
		SideTailM_LU.setRotateAngleZ(-0.087266F);
		SideTailM_LB = new ModelRenderer(this, 56, 52);
		SideTailM_LB.mirror = true;
		SideTailM_LB.addBox(0.5F, 0.0F, -1F, 2, 10, 2, f);
		SideTailM_LB.setRotationPoint(-0.5F, 8.5F, 0.0F);
		SideTailM_LB.setRotateAngleZ(-0.087266F);
		SideTailF_RU = new ModelRenderer(this, 32, 44);
		SideTailF_RU.addBox(-2F, -1.5F, -1F, 2, 8, 2, f - 0.3F);
		SideTailF_RU.setRotationPoint(-4.8F, -7F, 2.0F);
		SideTailF_RU.setRotateAngleZ(0.087266F);
		SideTailF_RB = new ModelRenderer(this, 32, 54);
		SideTailF_RB.addBox(-1.5F, 0.0F, -1F, 2, 8, 2, f);
		SideTailF_RB.setRotationPoint(-0.5F, 6.5F, 0.0F);
		SideTailF_RB.setRotateAngleZ(0.087266F);
		SideTailF_LU = new ModelRenderer(this, 32, 44);
		SideTailF_LU.mirror = true;
		SideTailF_LU.addBox(0.0F, -1.5F, -1F, 2, 8, 2, f - 0.3F);
		SideTailF_LU.setRotationPoint(4.8F, -7F, 2.0F);
		SideTailF_LU.setRotateAngleZ(-0.087266F);
		SideTailF_LB = new ModelRenderer(this, 32, 54);
		SideTailF_LB.mirror = true;
		SideTailF_LB.addBox(0.5F, 0.0F, -1F, 2, 8, 2, f);
		SideTailF_LB.setRotationPoint(-0.5F, 6.5F, 0.0F);
		SideTailF_LB.setRotateAngleZ(-0.087266F);
		SideTailN_LU = new ModelRenderer(this, 48, 40);
		SideTailN_LU.addBox(0.0F, -1F, -1F, 2, 10, 2, f);
		SideTailN_LU.setRotationPoint(5F, -7F, 2.0F);
		SideTailN_LU.setRotateAngleZ(0.0F);
		SideTailN_LB = new ModelRenderer(this, 48, 40);
		SideTailN_LB.addBox(0.5F, 0.0F, -2F, 2, 10, 2, f);
		SideTailN_LB.setRotationPoint(-0.5F, 8.5F, 1.0F);
		SideTailN_LB.setRotateAngleZ(0.0F);
		SideTailR_R = new ModelRenderer(this, 48, 52);
		SideTailR_R.addBox(-2F, -1F, -1F, 2, 10, 2, f);
		SideTailR_R.setRotationPoint(-4.001F, -8F, 0.0F);
		SideTailR_R.setRotateAngleZ(0.0F);
		SideTailR_L = new ModelRenderer(this, 48, 52);
		SideTailR_L.mirror = true;
		SideTailR_L.addBox(0.0F, -1F, -1F, 2, 10, 2, f);
		SideTailR_L.setRotationPoint(4.001F, -8F, 0.0F);
		SideTailR_L.setRotateAngleZ(0.0F);
		Tail_T = new ModelRenderer(this, 32, 40);
		Tail_T.addBox(-1F, -1F, 0.0F, 2, 2, 2, f);
		Tail_T.setRotationPoint(0.0F, -8F, 4F);
		Tail_U = new ModelRenderer(this, 40, 40);
		Tail_U.addBox(-1F, -1F, 0.0F, 2, 10, 2, f);
		Tail_U.setRotationPoint(0.0F, -6F, 4F);
		Tail_B = new ModelRenderer(this, 40, 52);
		Tail_B.addBox(-1F, 0.0F, -1F, 2, 10, 2, f + 0.2F);
		Tail_B.setRotationPoint(0.0F, 9.0F, 1.0F);
		DropTail = new ModelRenderer(this, 24, 40);
		DropTail.addBox(-1F, 0.0F, -1F, 2, 10, 2, f);
		DropTail.setRotationPoint(0.0F, -1F, 4F);
		Roll1_R = new ModelRenderer(this, 24, 60);
		Roll1_R.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 1, f);
		Roll1_R.setRotationPoint(-5.6F, -7F, 1.5F);
		Roll1_R.setRotateAngleX(-((float) Math.PI / 2F));
		Roll2_R = new ModelRenderer(this, 24, 56);
		Roll2_R.addBox(-1F, -1F, 0.0F, 2, 2, 2, f);
		Roll2_R.setRotationPoint(0.0F, 0.0F, 1.0F);
		Roll3_R = new ModelRenderer(this, 24, 56);
		Roll3_R.addBox(-1F, -1F, 0.0F, 2, 2, 2, f);
		Roll3_R.setRotationPoint(0.0F, 0.0F, 3F);
		Roll4_R = new ModelRenderer(this, 26, 53);
		Roll4_R.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, f);
		Roll4_R.setRotationPoint(0.0F, 0.0F, 5F);
		Roll1_L = new ModelRenderer(this, 24, 60);
		Roll1_L.mirror = true;
		Roll1_L.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 1, f);
		Roll1_L.setRotationPoint(5.6F, -7F, 1.5F);
		Roll1_L.setRotateAngleX(-((float) Math.PI / 2F));
		Roll2_L = new ModelRenderer(this, 24, 56);
		Roll2_L.mirror = true;
		Roll2_L.addBox(-1F, -1F, 0.0F, 2, 2, 2, f);
		Roll2_L.setRotationPoint(0.0F, 0.0F, 1.0F);
		Roll3_L = new ModelRenderer(this, 24, 56);
		Roll3_L.mirror = true;
		Roll3_L.addBox(-1F, -1F, 0.0F, 2, 2, 2, f);
		Roll3_L.setRotationPoint(0.0F, 0.0F, 3F);
		Roll4_L = new ModelRenderer(this, 26, 53);
		Roll4_L.mirror = true;
		Roll4_L.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, f);
		Roll4_L.setRotationPoint(0.0F, 0.0F, 5F);
		DumplingHari_L = new ModelRenderer(this, 10, 58);
		DumplingHari_L.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 3, f);
		DumplingHari_L.setRotationPoint(3.5F, -7.5F, 3F);
		HairOrnamentM_R = new ModelRenderer(this, 0, 32);
		HairOrnamentM_R.addBox(-2.5F, -2.5F, 0.0F, 5, 5, 3, f - 0.9F);
		HairOrnamentM_R.setRotationPoint(-3.1F, -7F, 2.0F);
		HairOrnamentM_R.setRotateAngleZ(-((float) Math.PI / 2F));
		HairOrnamentM_R.setRotateAngleY(((float) Math.PI / 4F));
		HairOrnamentM_R.setRotateAngleX(((float) Math.PI / 2F));
		HairOrnamentM_L = new ModelRenderer(this, 0, 32);
		HairOrnamentM_L.addBox(-2.5F, -2.5F, 0.0F, 5, 5, 3, f - 0.9F);
		HairOrnamentM_L.setRotationPoint(3.1F, -7F, 2.0F);
		HairOrnamentM_L.setRotateAngleZ(((float) Math.PI / 2F));
		HairOrnamentM_L.setRotateAngleY(-((float) Math.PI / 4F));
		HairOrnamentM_L.setRotateAngleX(((float) Math.PI / 2F));
		HairOrnamentF_R = new ModelRenderer(this, 8, 40);
		HairOrnamentF_R.addBox(-1.5F, -1.5F, -0.5F, 3, 3, 1, f - 0.2F);
		HairOrnamentF_R.setRotationPoint(-4.5F, -7F, 2.0F);
		HairOrnamentF_R.setRotateAngleY(-((float) Math.PI / 2F));
		HairOrnamentF_L = new ModelRenderer(this, 16, 40);
		HairOrnamentF_L.addBox(-1.5F, -1.5F, -0.5F, 3, 3, 1, f - 0.2F);
		HairOrnamentF_L.setRotationPoint(4.5F, -7F, 2.0F);
		HairOrnamentF_L.setRotateAngleY(((float) Math.PI / 2F));
		HairOrnamentN_L = new ModelRenderer(this, 0, 40);
		HairOrnamentN_L.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 1, f);
		HairOrnamentN_L.setRotationPoint(4F, -7F, 2.0F);
		HairOrnamentN_L.setRotateAngleY(((float) Math.PI / 2F));
		Ribon1_R = new ModelRenderer(this, 0, 56);
		Ribon1_R.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 1, f - 0.3F);
		Ribon1_R.setRotationPoint(-1.5F, 1.1F, 0.0F);
		Ribon1_R.setRotateAngleZ(-((float) Math.PI / 2F));
		Ribon1_R.setRotateAngleX(((float) Math.PI / 2F));
		Ribon2_R = new ModelRenderer(this, 0, 56);
		Ribon2_R.addBox(0.0F, -3F, 0.0F, 4, 3, 1, f);
		Ribon2_R.setRotationPoint(-1.5F, 0.7F, -0.5F);
		Ribon2_R.setRotateAngleZ(((float) Math.PI / 2F));
		Ribon2_R.setRotateAngleY(0.0F);
		Ribon3_R = new ModelRenderer(this, 0, 56);
		Ribon3_R.addBox(0.0F, -3F, -1F, 4, 3, 1, f);
		Ribon3_R.setRotationPoint(-1.5F, -0.7F, -0.5F);
		Ribon3_R.setRotateAngleZ(((float) Math.PI / 2F));
		Ribon3_R.setRotateAngleY(-(float) Math.PI);
		Ribon4_R = new ModelRenderer(this, 0, 56);
		Ribon4_R.addBox(0.0F, -3F, 0.0F, 4, 3, 1, f - 0.2F);
		Ribon4_R.setRotationPoint(0.0F, -0.2F, 0.0F);
		Ribon4_R.setRotateAngleZ(1.308997F);
		Ribon5_R = new ModelRenderer(this, 0, 56);
		Ribon5_R.addBox(0.0F, -3F, -1F, 4, 3, 1, f - 0.2F);
		Ribon5_R.setRotationPoint(0.0F, -0.2F, 0.0F);
		Ribon5_R.setRotateAngleZ(1.308997F);
		Ribon1_L = new ModelRenderer(this, 0, 60);
		Ribon1_L.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 1, f - 0.3F);
		Ribon1_L.setRotationPoint(1.5F, 1.1F, 0.0F);
		Ribon1_L.setRotateAngleZ(((float) Math.PI / 2F));
		Ribon1_L.setRotateAngleX(((float) Math.PI / 2F));
		Ribon2_L = new ModelRenderer(this, 0, 60);
		Ribon2_L.addBox(0.0F, -3F, 0.0F, 4, 3, 1, f);
		Ribon2_L.setRotationPoint(1.5F, 0.7F, 0.5F);
		Ribon2_L.setRotateAngleZ(-((float) Math.PI / 2F));
		Ribon2_L.setRotateAngleY((float) Math.PI);
		Ribon3_L = new ModelRenderer(this, 0, 60);
		Ribon3_L.addBox(0.0F, -3F, -1F, 4, 3, 1, f);
		Ribon3_L.setRotationPoint(1.5F, -0.7F, 0.5F);
		Ribon3_L.setRotateAngleZ(-((float) Math.PI / 2F));
		Ribon3_L.setRotateAngleY(0.0F);
		Ribon4_L = new ModelRenderer(this, 0, 60);
		Ribon4_L.addBox(0.0F, -3F, 0.0F, 4, 3, 1, f - 0.2F);
		Ribon4_L.setRotationPoint(0.0F, -0.2F, 0.0F);
		Ribon4_L.setRotateAngleZ(1.308997F);
		Ribon5_L = new ModelRenderer(this, 0, 60);
		Ribon5_L.addBox(0.0F, -3F, -1F, 4, 3, 1, f - 0.2F);
		Ribon5_L.setRotationPoint(0.0F, -0.2F, 0.0F);
		Ribon5_L.setRotateAngleZ(1.308997F);
		Ribon1_BU = new ModelRenderer(this, 0, 48);
		Ribon1_BU.addBox(-1.5F, -1.5F, 1.0F, 3, 3, 1, f - 0.3F);
		Ribon1_BU.setRotationPoint(0.0F, 1.5F, 0.7F);
		Ribon1_BU.setRotateAngleZ(((float) Math.PI / 2F));
		Ribon2_BU = new ModelRenderer(this, 0, 48);
		Ribon2_BU.addBox(-4F, -3F, 1.0F, 4, 3, 1, f);
		Ribon2_BU.setRotationPoint(1.5F, 0.7F, -0.5F);
		Ribon2_BU.setRotateAngleZ(-((float) Math.PI / 2F));
		Ribon3_BU = new ModelRenderer(this, 0, 48);
		Ribon3_BU.addBox(0.0F, -3F, 1.0F, 4, 3, 1, f);
		Ribon3_BU.setRotationPoint(1.5F, -0.7F, -0.5F);
		Ribon3_BU.setRotateAngleZ(-((float) Math.PI / 2F));
		Ribon4_BU = new ModelRenderer(this, 0, 48);
		Ribon4_BU.addBox(-4F, -3F, 1.0F, 4, 3, 1, f - 0.2F);
		Ribon4_BU.setRotationPoint(0.0F, -0.2F, 0.0F);
		Ribon4_BU.setRotateAngleZ(-1.308997F);
		Ribon5_BU = new ModelRenderer(this, 0, 48);
		Ribon5_BU.addBox(0.0F, -3F, 1.0F, 4, 3, 1, f - 0.2F);
		Ribon5_BU.setRotationPoint(0.0F, -0.2F, 0.0F);
		Ribon5_BU.setRotateAngleZ(1.308997F);
		Ribon1_BB = new ModelRenderer(this, 0, 52);
		Ribon1_BB.addBox(-1.5F, -1.5F, 1.0F, 3, 3, 1, f - 0.3F);
		Ribon1_BB.setRotationPoint(0.0F, 0.5F, -0.3F);
		Ribon1_BB.setRotateAngleZ(((float) Math.PI / 2F));
		Ribon2_BB = new ModelRenderer(this, 0, 52);
		Ribon2_BB.addBox(-4F, -3F, 1.0F, 4, 3, 1, f);
		Ribon2_BB.setRotationPoint(1.5F, 0.7F, -0.5F);
		Ribon2_BB.setRotateAngleZ(-((float) Math.PI / 2F));
		Ribon3_BB = new ModelRenderer(this, 0, 52);
		Ribon3_BB.addBox(0.0F, -3F, 1.0F, 4, 3, 1, f);
		Ribon3_BB.setRotationPoint(1.5F, -0.7F, -0.5F);
		Ribon3_BB.setRotateAngleZ(-((float) Math.PI / 2F));
		Ribon4_BB = new ModelRenderer(this, 0, 52);
		Ribon4_BB.addBox(-4F, -3F, 1.0F, 4, 3, 1, f - 0.2F);
		Ribon4_BB.setRotationPoint(0.0F, -0.2F, 0.0F);
		Ribon4_BB.setRotateAngleZ(-1.308997F);
		Ribon5_BB = new ModelRenderer(this, 0, 52);
		Ribon5_BB.addBox(0.0F, -3F, 1.0F, 4, 3, 1, f - 0.2F);
		Ribon5_BB.setRotationPoint(0.0F, -0.2F, 0.0F);
		Ribon5_BB.setRotateAngleZ(1.308997F);
		Ribon1_T = new ModelRenderer(this, 1, 44);
		Ribon1_T.addBox(-0.5F, -0.5F, -1F, 1, 1, 1, f + 0.1F);
		Ribon1_T.setRotationPoint(0.0F, -7.8F, -1.5F);
		Ribon1_T.setRotateAngleZ(((float) Math.PI / 2F));
		Ribon1_T.setRotateAngleY(1.396263F);
		Ribon2_T = new ModelRenderer(this, 0, 44);
		Ribon2_T.addBox(-4F, -1.5F, -1F, 4, 3, 1, f);
		Ribon2_T.setRotationPoint(0.0F, 0.5F, 0.0F);
		Ribon2_T.setRotateAngleZ(-((float) Math.PI / 2F));
		Ribon2_T.setRotateAngleY(-0.174533F);
		Ribon3_T = new ModelRenderer(this, 0, 44);
		Ribon3_T.mirror = true;
		Ribon3_T.addBox(0.0F, -1.5F, -1F, 4, 3, 1, f);
		Ribon3_T.setRotationPoint(0.0F, -0.5F, 0.0F);
		Ribon3_T.setRotateAngleZ(-((float) Math.PI / 2F));
		Ribon3_T.setRotateAngleY(0.174533F);
		Headwear_F = new ModelRenderer(this, 24, 16);
		Headwear_F.addPlate(-4F, 0.0F, 0.0F, 8, 4, 16, f);
		Headwear_F.setRotationPoint(0.0F, 0.0F, -4F);
		Headwear_B = new ModelRenderer(this, 24, 20);
		Headwear_B.addPlate(-4F, 0.0F, 0.0F, 8, 4, 20, f);
		Headwear_B.setRotationPoint(0.0F, 0.0F, 4F);
		Headwear_R = new ModelRenderer(this, 44, 0);
		Headwear_R.addPlate(0.0F, 0.0F, 0.0F, 4, 4, 17, f);
		Headwear_R.setRotationPoint(-4F, 0.0F, 0.0F);
		Headwear_L = new ModelRenderer(this, 44, 4);
		Headwear_L.addPlate(0.0F, 0.0F, 0.0F, 4, 4, 21, f);
		Headwear_L.setRotationPoint(4F, 0.0F, 0.0F);
		Shaggy_F = new ModelRenderer(this, 34, 0);
		Shaggy_F.addPlate(-5F, 0.0F, 0.0F, 10, 4, 16, f);
		Shaggy_F.setRotationPoint(0.0F, -1.0F, -4F);
		Shaggy_F.setRotateAngleX(-0.4F);
		shaggyB = new ModelRenderer(this, 24, 0);
		shaggyB.addPlate(-5F, 0.0F, 0.0F, 10, 4, 20, f);
		shaggyB.setRotationPoint(0.0F, -1.0F, 4F);
		shaggyB.setRotateAngleX(0.4F);
		shaggyR = new ModelRenderer(this, 24, 4);
		shaggyR.addPlate(0.0F, 0.0F, -5F, 10, 4, 17, f);
		shaggyR.setRotationPoint(-4F, -1.0F, 0.0F);
		shaggyR.setRotateAngleZ(0.4F);
		shaggyL = new ModelRenderer(this, 34, 4);
		shaggyL.addPlate(0.0F, 0.0F, -5F, 10, 4, 21, f);
		shaggyL.setRotationPoint(4F, -1.0F, 0.0F);
		shaggyL.setRotateAngleZ(-0.4F);
		sidetailUpperR = new ModelRenderer(this);
		sidetailUpperL = new ModelRenderer(this);
		LongHair_F = new ModelRenderer(this, 34, 32);
		LongHair_F.addPlate(-5F, 0.0F, 0.0F, 10, 8, 16, f);
		LongHair_F.setRotationPoint(0.0F, -1.0F, -4F);
		LongHair_F.setRotateAngleX(-0.202458F);
		LongHair_B = new ModelRenderer(this, 24, 32);
		LongHair_B.addPlate(-5F, 0.0F, 0.0F, 10, 8, 20, f);
		LongHair_B.setRotationPoint(0.0F, -1.0F, 4F);
		LongHair_B.setRotateAngleX(0.202458F);
		LongHair_R = new ModelRenderer(this, 44, 32);
		LongHair_R.addPlate(0.0F, 0.0F, -5F, 10, 8, 17, f);
		LongHair_R.setRotationPoint(-4F, -1.0F, 0.0F);
		LongHair_R.setRotateAngleZ(0.202458F);
		LongHair_L = new ModelRenderer(this, 54, 32);
		LongHair_L.addPlate(0.0F, 0.0F, -5F, 10, 8, 21, f);
		LongHair_L.setRotationPoint(4F, -1.0F, 0.0F);
		LongHair_L.setRotateAngleZ(-0.202458F);
		sensor1 = new ModelRenderer(this, 0, 0);
		sensor1.addPlate(-8F, -4F, 0.0F, 8, 4, 16, f);
		sensor1.setRotationPoint(0.0F, -8F, 0.0F);
		sensor2 = new ModelRenderer(this, 0, 4);
		sensor2.addPlate(0.0F, -4F, 0.0F, 8, 4, 16, f);
		sensor2.setRotationPoint(0.0F, -8F, 0.0F);
		sensor3 = new ModelRenderer(this, 0, 20);
		sensor3.addPlate(0.0F, -4F, -0F, 8, 4, 21, f);
		sensor3.setRotationPoint(0.0F, -8F, 0.0F);
		sensor4 = new ModelRenderer(this, 0, 16);
		sensor4.addPlate(0.0F, -4F, -8F, 8, 4, 21, f);
		sensor4.setRotationPoint(0.0F, -8F, 0.0F);
		eyeR = new ModelRenderer(this, 42, 19);
		eyeR.addPlate(-4F, -5F, -4.001F, 4, 4, 16, f);
		eyeR.setRotationPoint(0.0F, 0.0F, 0.0F);
		eyeL = new ModelRenderer(this, 46, 19);
		eyeL.addPlate(0.0F, -5F, -4.001F, 4, 4, 16, f);
		eyeL.setRotationPoint(0.0F, 0.0F, 0.0F);
		Cheek_R = new ModelRenderer(this, 32, 8);
		Cheek_R.addPlate(-4.5F, -4.5F, -4.002F, 4, 4, 16, f);
		Cheek_R.setRotationPoint(0.0F, 1.0F, 0.0F);
		Cheek_L = new ModelRenderer(this, 32, 19);
		Cheek_L.addPlate(0.5F, -4.5F, -4.002F, 4, 4, 16, f);
		Cheek_L.setRotationPoint(0.0F, 1.0F, 0.0F);
		Body_F = new ModelRenderer(this, 56, 16);
		Body_F.addPlate(-2.5F, 0.0F, -2.001F, 5, 7, 16, f);
		Body_F.setRotationPoint(0.0F, -3.5F, 0.0F);
		Body_B = new ModelRenderer(this, 18, 44);
		Body_B.addPlate(-2.5F, 0.0F, 2.001F, 5, 7, 20, f);
		Body_B.setRotationPoint(0.0F, -3.5F, 0.0F);
		Breast1 = new ModelRenderer(this, 18, 52);
		Breast1.addBox(0.0F, -2.5F, 0.0F, 2, 5, 1, f);
		Breast1.setRotationPoint(0.0F, -2.5F, -2F);
		Breast1.setRotateAngleZ(((float) Math.PI / 2F));
		Breast1.setRotateAngleY(0.4363323F);
		Breast2 = new ModelRenderer(this, 10, 51);
		Breast2.addBox(0.0F, -2.5F, 0.0F, 2, 5, 2, f);
		Breast2.setRotationPoint(0.0F, -2.5F, -2F);
		Breast2.setRotateAngleZ(((float) Math.PI / 2F));
		Breast2.setRotateAngleY(0.567232F);
		Breast3 = new ModelRenderer(this, 10, 44);
		Breast3.addBox(0.0F, -2.5F, 0.0F, 2, 5, 2, f);
		Breast3.setRotationPoint(0.0F, -2.5F, -2F);
		Breast3.setRotateAngleZ(((float) Math.PI / 2F));
		Breast3.setRotateAngleY(((float) Math.PI * 2F / 9F));
		Belt_R = new ModelRenderer(this, 52, 10);
		Belt_R.addPlate(0.0F, 0.0F, -3F, 6, 6, 5, f);
		Belt_R.setRotationPoint(-3F, 2.5F, 0.0F);
		Belt_R.setRotateAngleZ(((float) Math.PI / 10F));
		Belt_L = new ModelRenderer(this, 58, 10);
		Belt_L.addPlate(0.0F, 0.0F, -3F, 6, 6, 1, f);
		Belt_L.setRotationPoint(3F, 2.5F, 0.0F);
		Belt_L.setRotateAngleZ(-((float) Math.PI / 10F));
		Number = new ModelRenderer(this, 16, 32);
		Number.addPlate(0.0F, -4F, -4F, 8, 8, 1, f - 3.3F);
		Number.setRotationPoint(4.301F, 1.0F, 0.0F);
		Negi1 = new ModelRenderer(this, 60, 23);
		Negi1.addBox(-0.5F, -4F, -0.5F, 1, 8, 1, f);
		Negi1.setRotationPoint(0.0F, 7.0F, 0.0F);
		Negi1.setRotateAngleX(((float) Math.PI / 2F));
		Negi2 = new ModelRenderer(this, 52, 16);
		Negi2.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 1, f);
		Negi2.setRotationPoint(0.0F, 6.5F, -4F);
		Negi2.setRotateAngleX(-2.094395F);
		Negi3 = new ModelRenderer(this, 52, 16);
		Negi3.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 1, f);
		Negi3.setRotationPoint(0.0F, 6.5F, -4F);
		Negi3.setRotateAngleX(-0.8726646F);
		mainFrame = new ModelRenderer(this, 0, 0);
		mainFrame.setRotationPoint(0F, 0F + f1, 0F);
		setCapsValue(null, caps_visible, Negi1, false);
		setCapsValue(null, caps_visible, Negi2, false);
		setCapsValue(null, caps_visible, Negi3, false);
		if (isAfterInit) afterInit(f, f1);
	}
	public void skirtFloatsInit(float f, float f1) {
		if (ModelCapsHelper.getCapsValueInt(this, null, caps_skirtFloats) < 2) return;
		textureWidth = 64;
		textureHeight = 64;
		//ふんわりスカート上
		SkirtTop = new ModelRenderer(this, 8, 18);
		SkirtTop.addPlate(0.0F, 0.0F, 0.0F, 8, 8, ModelBoxPlate.planeXZTop);
		SkirtTop.setRotationPoint(-4.0F, -4.0F, -4.0F);
		SkirtFront = new ModelRenderer(this, 8, 24);
		SkirtFront.addPlate(0.0F, 0.0F, 0.0F, 8, 8, ModelBoxPlate.planeXYFront);
		SkirtFront.setRotationPoint(0.0F, 0.0F, 0.0F);
		SkirtRight = new ModelRenderer(this, 2, 24);
		SkirtRight.addPlate(0.0F, 0.0F, 0.0F, 8, 8, ModelBoxPlate.planeZYRight);
		SkirtRight.setRotationPoint(8.0F, 0.0F, 0.0F);
		SkirtLeft = new ModelRenderer(this, 16, 24);
		SkirtLeft.addPlate(0.0F, 0.0F, 0.0F, 8, 8, ModelBoxPlate.planeZYLeft);
		SkirtLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
		SkirtBack = new ModelRenderer(this, 22, 24);
		SkirtBack.addPlate(0.0F, 0.0F, 0.0F, 8, 8, ModelBoxPlate.planeXYBack);
		SkirtBack.setRotationPoint(0.0F, 0.0F, 8.0F);
		if (Skirt != null) Skirt.setVisible(false);
	}
	public void defaultAddChildSetting() {
		super.defaultAddChildSetting();
		bipedHead.removeChild(ChignonB);
		bipedHead.removeChild(ChignonR);
		bipedHead.removeChild(ChignonL);
		bipedHead.removeChild(SideTailR);
		bipedHead.removeChild(SideTailL);
		bipedHead.removeChild(Tail);
		bipedHead.addChild(SideTailRoot_R);
		bipedHead.addChild(SideTailRoot_L);
		bipedHead.addChild(SideTailM_RU);
		bipedHead.addChild(SideTailM_LU);
		SideTailM_RU.addChild(SideTailM_RB);
		SideTailM_LU.addChild(SideTailM_LB);
		bipedHead.addChild(SideTailF_RU);
		bipedHead.addChild(SideTailF_LU);
		SideTailF_RU.addChild(SideTailF_RB);
		SideTailF_LU.addChild(SideTailF_LB);
		bipedHead.addChild(SideTailN_LU);
		SideTailN_LU.addChild(SideTailN_LB);
		bipedHead.addChild(SideTailR_R);
		bipedHead.addChild(SideTailR_L);
		bipedHead.addChild(Tail_T);
		bipedHead.addChild(Tail_U);
		Tail_U.addChild(Tail_B);
		bipedHead.addChild(DropTail);
		bipedHead.addChild(HairOrnamentM_R);
		bipedHead.addChild(HairOrnamentM_L);
		bipedHead.addChild(HairOrnamentF_R);
		bipedHead.addChild(HairOrnamentF_L);
		bipedHead.addChild(HairOrnamentN_L);
		SideTailF_RU.addChild(Ribon1_R);
		Ribon1_R.addChild(Ribon2_R);
		Ribon1_R.addChild(Ribon3_R);
		Ribon2_R.addChild(Ribon4_R);
		Ribon3_R.addChild(Ribon5_R);
		SideTailF_LU.addChild(Ribon1_L);
		Ribon1_L.addChild(Ribon2_L);
		Ribon1_L.addChild(Ribon3_L);
		Ribon2_L.addChild(Ribon4_L);
		Ribon3_L.addChild(Ribon5_L);
		Tail_U.addChild(Ribon1_BU);
		Ribon1_BU.addChild(Ribon2_BU);
		Ribon1_BU.addChild(Ribon3_BU);
		Ribon2_BU.addChild(Ribon4_BU);
		Ribon3_BU.addChild(Ribon5_BU);
		DropTail.addChild(Ribon1_BB);
		Ribon1_BB.addChild(Ribon2_BB);
		Ribon1_BB.addChild(Ribon3_BB);
		Ribon2_BB.addChild(Ribon4_BB);
		Ribon3_BB.addChild(Ribon5_BB);
		bipedHead.addChild(Ribon1_T);
		Ribon1_T.addChild(Ribon2_T);
		Ribon1_T.addChild(Ribon3_T);
		bipedHead.addChild(Roll1_R);
		Roll1_R.addChild(Roll2_R);
		Roll1_R.addChild(Roll3_R);
		Roll1_R.addChild(Roll4_R);
		bipedHead.addChild(Roll1_L);
		Roll1_L.addChild(Roll2_L);
		Roll1_L.addChild(Roll3_L);
		Roll1_L.addChild(Roll4_L);
		bipedHead.addChild(DumplingHari_L);
		bipedHead.addChild(Headwear_F);
		bipedHead.addChild(Headwear_B);
		bipedHead.addChild(Headwear_R);
		bipedHead.addChild(Headwear_L);
		bipedHead.addChild(Shaggy_F);
		bipedHead.addChild(shaggyB);
		bipedHead.addChild(shaggyR);
		bipedHead.addChild(shaggyL);
		bipedHead.addChild(LongHair_F);
		bipedHead.addChild(LongHair_B);
		bipedHead.addChild(LongHair_R);
		bipedHead.addChild(LongHair_L);
		bipedHead.addChild(sensor1);
		bipedHead.addChild(sensor2);
		bipedHead.addChild(sensor3);
		bipedHead.addChild(sensor4);
		bipedHead.addChild(Cheek_R);
		bipedHead.addChild(Cheek_L);
		bipedBody.addChild(Body_F);
		bipedBody.addChild(Body_B);
		bipedBody.addChild(Breast1);
		bipedBody.addChild(Breast2);
		bipedBody.addChild(Breast3);
		bipedBody.addChild(Belt_R);
		bipedBody.addChild(Belt_L);
		bipedLeftArm.addChild(Number);
		bipedLeftArm.addChild(Negi1);
		bipedLeftArm.addChild(Negi2);
		bipedLeftArm.addChild(Negi3);
	}
	public void setLivingAnimationsLM(IModelCaps entityCaps, float f, float f1, float f2) {
		super.setLivingAnimationsLM(entityCaps, f, f1, f2);
		if (ModelCapsHelper.getCapsValueBoolean(this, entityCaps, caps_isLookSuger)) {
			Cheek_R.setVisible(true);
			Cheek_L.setVisible(true);
		} else {
			Cheek_R.setVisible(false);
			Cheek_L.setVisible(false);
		}
	}
	public void setRotationAnglesLM(float f, float f1, float f2, float f3, float f4, float f5, IModelCaps entityCaps) {
		super.setRotationAnglesLM(f, f1, f2, f3, f4, f5, entityCaps);
		Number.setRotationPoint(-2.0F, 1.0F, 0.0F);
		SideTailR_L.setRotateAngleX(-bipedHead.getRotateAngleX());
		DropTail.setRotateAngleX(0.1745292F - bipedHead.getRotateAngleX());
		sensor1.rotationPointY = -8.0F;
		sensor2.rotationPointY = -8.0F;
		sensor3.rotationPointY = -9.0F;
		sensor4.rotationPointY = -8.0F;
		//if (Modchu_EntityCapsHelper.getCapsValueInt(this, entityCaps, caps_skirtFloats) == 2) Skirt.rotationPointY -= 2.0F;
		//Skirt.rotationPointY -= 2.0F;
		if (bipedHead.getRotateAngleX() < 0.0F) {
			SideTailM_RU.setRotateAngleX(-bipedHead.getRotateAngleX());
			SideTailM_LU.setRotateAngleX(-bipedHead.getRotateAngleX());
			SideTailM_RB.setRotateAngleX(0.08726461F);
			SideTailM_LB.setRotateAngleX(0.08726461F);
			SideTailF_RU.setRotateAngleX(-bipedHead.getRotateAngleX());
			SideTailF_LU.setRotateAngleX(-bipedHead.getRotateAngleX());
			SideTailF_RB.setRotateAngleX(0.08726461F);
			SideTailF_LB.setRotateAngleX(0.08726461F);
			SideTailN_LU.setRotateAngleX(-bipedHead.getRotateAngleX());
			SideTailN_LB.setRotateAngleX(0.0F);
			Tail_U.setRotateAngleX(-bipedHead.getRotateAngleX());
			Tail_B.setRotateAngleX(0.0F + Skirt.getRotateAngleX());
		} else {
			SideTailM_RU.setRotateAngleX(0.0F);
			SideTailM_LU.setRotateAngleX(0.0F);
			SideTailM_RB.setRotateAngleX((0.08726461F - bipedHead.getRotateAngleX()) + Skirt.getRotateAngleX());
			SideTailM_LB.setRotateAngleX((0.08726461F - bipedHead.getRotateAngleX()) + Skirt.getRotateAngleX());
			SideTailF_RU.setRotateAngleX(0.0F);
			SideTailF_LU.setRotateAngleX(0.0F);
			SideTailF_RB.setRotateAngleX((0.08726461F - bipedHead.getRotateAngleX()) + Skirt.getRotateAngleX());
			SideTailF_LB.setRotateAngleX((0.08726461F - bipedHead.getRotateAngleX()) + Skirt.getRotateAngleX());
			SideTailN_LU.setRotateAngleX(0.0F);
			SideTailN_LB.setRotateAngleX((0.0F - bipedHead.getRotateAngleX()) + Skirt.getRotateAngleX());
			Tail_U.setRotateAngleX(0.0F);
			Tail_B.setRotateAngleX(-bipedHead.getRotateAngleX() + Skirt.getRotateAngleX());
		}
		if (ModelCapsHelper.getCapsValueBoolean(this, entityCaps, caps_isSneak)) {
		float f6 = mh_sin(f2 * 0.09F) * 0.05F + 0.05F;
		SideTailM_RU.rotateAngleZ = SideTailF_RU.rotateAngleZ = SideTailN_LU.rotateAngleZ = SideTailR_R.rotateAngleZ = f6;
		SideTailM_LU.rotateAngleZ = SideTailF_LU.rotateAngleZ = SideTailN_LB.rotateAngleZ = SideTailR_L.rotateAngleZ = -f6;
		SideTailM_LU.rotateAngleX = SideTailM_RU.rotateAngleX = SideTailN_LU.rotateAngleX = SideTailN_LB.rotateAngleX = SideTailF_RU.rotateAngleX = SideTailF_LU.rotateAngleX = SideTailR_R.rotateAngleX = SideTailR_L.rotateAngleX = -bipedHead.rotateAngleX / 2.0F;
	}
}