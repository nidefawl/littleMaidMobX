package littleMaidMobX.model.maids;
public class MultiModel_Beverly6 extends MultiModelBeverlyBase {
	public ModelRenderer Ponytail;
	public ModelRenderer BunchR;
	public ModelRenderer BunchL;
	public ModelRenderer hemSkirtR1;
	public ModelRenderer hemSkirtL1;
	public ModelRenderer hemSkirtR2;
	public ModelRenderer hemSkirtL2;
	public ModelRenderer breastR;
	public ModelRenderer breastL;
	public ModelRenderer hipBody;
	protected byte offsetY;
	protected byte headPosY;
	protected byte bodyPosY;
	protected byte legPosY;
	protected boolean isRidingMaster = false;
		super();
	}
		super(f);
	}
		this(f, f1, 64, 64);
	}
		super(f, f1, i < 0 ? 64 : i, j < 0 ? 32 : j);
	}
	public void initModel(float f, float f1, boolean isAfterInit) {
		textureWidth = 128;
		textureHeight = 64;
		headPosY = -9; //Local neck height = 0 - upper bodyLength
		legPosY = 4; //Lcal hip joint height = 0 + lower bodyLength
		eyeR = new ModelRenderer(this, 17, 0);
		eyeR.addPlate(-3.0F, -4F, -4.01F, 2, 3, 0, f);
		eyeL.addPlate(1.0F, -4F, -4.01F, 2, 3, 0, f);
		Ponytail.addBox(-1.5F, -1.5F, -1F, 3, 9, 3, f);
		BunchR.addBox(-1F, -1.3F, -0.8F, 1, 9, 2, f);
		BunchL.mirror = true;
		BunchL.addBox(0F, -1.3F, -0.8F, 1, 9, 2, f);
		bipedHead.setTextureOffset(0, 0).addBox(-4F, -8F, -4F, 8, 8, 8, f); // Head
		bipedHead.setTextureOffset(32, 0).addBox(-4F, -8F, -4F, 8, 12, 8, f + 0.3F); // Hire
		bipedHead.setTextureOffset(72, 0).addBox(-2F, -7.2F, 4F, 4, 4, 2, f); // ChignonB
		bipedHead.setTextureOffset(56, 0).addBox(-5F, -7F, 0.2F, 1, 3, 3, f); // ChignonR
		bipedHead.setMirror(true);
		bipedHead.setTextureOffset(64, 0).addBox(4F, -7F, 0.2F, 1, 3, 3, f); // ChignonL
		rightArm2.addBox(-1.001F, -0F, -1.5F, 2, 8, 3, f);
		leftArm2.mirror = true;
		leftArm2.addBox(-0.999F, -0F, -1.5F, 2, 8, 3, f);
		rightArm.addBox(-1F, -1F, -1F, 2, 6, 3, f);
		leftArm.mirror = true;
		leftArm.addBox(-1F, -1F, -1F, 2, 6, 3, f);
		rightLeg2 = new ModelRenderer(this, 0, 47); //Below Knee
		rightLeg2.addBox(-1.6F, -1F, -2F, 3, 10, 4, f);
		leftLeg2.mirror = true;
		leftLeg2.addBox(-1.4F, -1F, -2F, 3, 10, 4, f);
		rightLeg.addBox(-1.5F, -1F, -1.7F, 3, 7, 4, f + 0.2F);
		leftLeg.mirror = true;
		leftLeg.addBox(-1.5F, -1F, -1.7F, 3, 7, 4, f + 0.2F);
		hemSkirtR2 = new ModelRenderer(this, 68, 48);
		hemSkirtR2.addBox(-3.5F, -2F, -4.5F, 7, 8, 8, f + 0.2F);
		hemSkirtL2.mirror = true;
		hemSkirtL2.addBox(-3.5F, -2F, -4.5F, 7, 8, 8, f + 0.2F);
		hemSkirtR1.addBox(-3F, -1F, -5F, 6, 7, 7, f);
		hemSkirtL1.mirror = true;
		hemSkirtL1.addBox(-3F, -1F, -5F, 6, 7, 7, f);
		Skirt.addBox(-4F, 0F, -2F, 8, 3, 5, f + 0.6F);
		breastR = new ModelRenderer(this, 20, 20);
		breastR.addBox(-3F, 0F, -3F, 3, 3, 3, f + 0.1F);
		breastL.mirror = true;
		breastL.addBox(0F, 0F, -3F, 3, 3, 3, f + 0.1F);
		hipBody.addBox(-4F, 0F, -2.4F, 8, 4, 5, f - 0.2F);
		bipedBody.setTextureOffset(20, 26).addBox(-3F, -8.5F, -2.1F, 6, 9, 4, f); //body
		bipedBody.setTextureOffset(24, 16).addBox(-1F, -9.8F, -1F, 2, 2, 2, f + 0.5F); //neck
		mainFrame = new ModelRenderer(this, 0, 0);
		mainFrame.setRotationPoint(0F, offsetY, 0F);
		bipedLeftArm = new ModelRenderer(this);
		bipedRightLeg = new ModelRenderer(this);
		bipedLeftLeg = new ModelRenderer(this);
	}
	public void actionPartsInit(float f, float f1) {
		rightHand = new ModelRenderer(this, 0, 31);
		rightHand.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 3, f);
		leftHand.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 3, f);
		rightArmPlus2 = new ModelRenderer(this);
		leftArmPlus = new ModelRenderer(this);
		leftArmPlus2 = new ModelRenderer(this);
		rightLegPlus = new ModelRenderer(this);
		rightLegPlus2 = new ModelRenderer(this);
		leftLegPlus = new ModelRenderer(this);
		leftLegPlus2 = new ModelRenderer(this);
		setCapsValue(null, caps_visible, leftHand, false);
	}
	public void skirtFloatsInit(float f, float f1) {
	}
	public void defaultAddChildSetting() {
		HeadMount.clearChildModels();
		HeadTop.clearChildModels();
		bipedHead.clearChildModels();
		bipedBody.clearChildModels();
		bipedBody.addChild(bipedHead);
		bipedBody.addChild(rightArm);
		bipedBody.addChild(leftArm);
		bipedHead.addChild(HeadMount);
		bipedHead.addChild(HeadTop);
		leftArm.addChild(leftArm2);
		rightLeg.addChild(rightLeg2);
		leftLeg.addChild(leftLeg2);
		bipedHead.addChild(eyeR);
		bipedHead.addChild(eyeL);
		bipedHead.addChild(Ponytail);
		bipedHead.addChild(BunchR);
		bipedHead.addChild(BunchL);
		rightLeg2.addChild(hemSkirtR2);
		leftLeg2.addChild(hemSkirtL2);
		rightLeg.addChild(hemSkirtR1);
		leftLeg.addChild(hemSkirtL1);
		bipedBody.addChild(breastR);
		bipedBody.addChild(breastL);
		bipedBody.addChild(hipBody);
		bipedHead.addChild(eyeR);
		bipedHead.addChild(eyeL);
		rightArm2.addChild(Arms[0]);
		leftArm2.addChild(Arms[1]);
		((ModelRenderer) mainFrame).clearChildModels();
		mainFrame.addChild(bipedBody);
		mainFrame.addChild(rightLeg);
		mainFrame.addChild(leftLeg);
	}
	public void defaultSkirtFloatsAddChild() {
	}
	 * 姿勢制御・初期化
	 */
	@Override
	public void setLivingAnimationsLM(IModelCaps entityCaps, float f, float f1, float f2) {
		super.setLivingAnimationsLM(entityCaps, f, f1, f2);
		Object entityliving = entityCaps.getCapsValue(caps_Entity);
		if (entityliving != null); else return;

		float velY = (float)ModelCapsHelper.getCapsValueDouble(this, entityCaps, caps_skirtFloatsMotionY) + 0.1F;
		fwBuf10 = fwBuf10 > 0.5F ? 0.5F : fwBuf10;
		fwBuf10 = fwBuf10 < -0.5F ? -0.5F : fwBuf10;
		Skirt.rotationPointY += fwBuf10;
		fwBuf11 = fwBuf11 > 1F ? 1F : fwBuf11;
		fwBuf11 = fwBuf11 < -2F ? -2F : fwBuf11;
		hemSkirtR1.rotationPointY += fwBuf11;
		hemSkirtL1.rotationPointY += fwBuf11;
		fwBuf12 = fwBuf12 > 1F ? 1F : fwBuf12;
		fwBuf12 = fwBuf12 < -3F ? -3F : fwBuf12;
		hemSkirtR2.rotationPointY += fwBuf12;
		hemSkirtL2.rotationPointY += fwBuf12;
		fwBuf2 = fwBuf2 > 0.1F ? 0.1F : fwBuf2;
		fwBuf2 = fwBuf2 < -0.7F ? -0.7F : fwBuf2;
		Ponytail.rotateAngleX -= fwBuf2;
		BunchR.rotateAngleZ -= fwBuf2;
		BunchL.rotateAngleZ += fwBuf2;
//				&& Modchu_Reflect.loadClass("EntityVillager").isInstance(entityliving)) { //村人モデルとしての使用時
//			//if ((EntityVillager)entityliving.isMating()) { //"KIMASHITAWA-!"
//			if ((Boolean) Modchu_Reflect.invokeMethod("EntityVillager", "isMating", entityliving)) {
//				bipedHead.rotateAngleX += 0.15F;
//				bipedHead.rotateAngleZ += 0.25F;
//				rightArm.rotateAngleX -= 0.3F;
//				leftArm.rotateAngleX -= 0.3F;
//				rightArm2.rotateAngleX -= 2.1F;
//				leftArm2.rotateAngleX -= 2.1F;
//				rightArm.rotateAngleZ -= 0.3F;
//				leftArm.rotateAngleZ += 0.3F;
//				rightArm.rotateAngleY -= 0.3F;
//				leftArm.rotateAngleY += 0.3F;
//				rightArm2.rotateAngleY -= 0.3F;
//				leftArm2.rotateAngleY += 0.3F;
//			}
//		}
	 * 姿勢制御・更新差分
	 */
	@Override
	public void setRotationAnglesLM(float f, float f1, float f2, float f3, float f4, float f5, IModelCaps entityCaps) {
		setDefaultPause(f, f1, f2, f3, f4, f5, entityCaps);
		bipedHead.rotateAngleY += f3 / 57.29578F;
		bipedHead.rotateAngleX += f4 / 57.29578F;
		//ポニテツインテ
		Ponytail.rotateAngleX += BunchR.rotateAngleX = BunchL.rotateAngleX = -bipedHead.rotateAngleX;
		Ponytail.rotateAngleZ -= bipedHead.rotateAngleZ;
		if (bipedHead.rotateAngleZ > 0) {
			BunchR.rotateAngleZ -= bipedHead.rotateAngleZ * 0.2F;
		} else {
			BunchL.rotateAngleZ -= bipedHead.rotateAngleZ * 0.2F;
		}
			// 背負われている
			if (isRidingMaster) {
				rightArm2.rotateAngleX -= 1.3F;
				leftArm2.rotateAngleX -= 1.3F;
				rightLeg.rotateAngleX -= 1.1F;
				leftLeg.rotateAngleX -= 1.1F;
				rightLeg2.rotateAngleX += 0.9F;
				leftLeg2.rotateAngleX += 0.9F;
				rightLeg.rotateAngleY += 0.3F;
				leftLeg.rotateAngleY -= 0.3F;
				mainFrame.rotationPointY += 12F;
				mainFrame.rotationPointZ += 1F;
			}
			// 乗り物に乗っている
			else {
				rightArm.rotateAngleX -= 0.1F;
				leftArm.rotateAngleX -= 0.1F;
				rightLeg.rotateAngleX -= 1.3F;
				leftLeg.rotateAngleX -= 1.3F;
				rightLeg2.rotateAngleX += 2.5F;
				leftLeg2.rotateAngleX += 2.5F;
				mainFrame.rotationPointY += 4F;
				mainFrame.rotationPointZ += 1F;
				if (ModelCapsHelper.getCapsValueBoolean(this, entityCaps, caps_isWait)) {
					mainFrame.rotateAngleY += 0.7F;
					bipedHead.rotateAngleY -= 0.7F;
					if (bipedHead.rotateAngleY < -1.5F) {
						bipedHead.rotateAngleY = -1.5F;
					}
					rightLeg2.rotateAngleX -= 0.5F;
					leftLeg2.rotateAngleX -= 0.5F;
					rightLeg.rotateAngleY += 0.3F;
					leftLeg.rotateAngleY += 0.3F;
					rightLeg2.rotateAngleY += 0.2F;
					leftLeg2.rotateAngleY += 0.2F;
					bipedBody.rotateAngleY += 0.3F;
					hipBody.rotateAngleY += 0.3F;
					hemSkirtR1.rotationPointX += 2F;
					hemSkirtL1.rotationPointX += 2F;
					// hemSkirtR2.rotationPointZ -= 1F;
					//hemSkirtL2.rotationPointZ -= 1F;
				}
			}
		} else {
			if (ModelCapsHelper.getCapsValueBoolean(this, entityCaps, caps_isSneak)) //しゃがみ
			{
				if (ModelCapsHelper.getCapsValueBoolean(this, entityCaps, caps_isWait)) {//膝立ち
					rightLeg.rotateAngleX -= 0.1F;
					leftLeg.rotateAngleX -= 0.2F;
					rightLeg2.rotateAngleX += 1.7F;
					leftLeg2.rotateAngleX += 1.8F;
					mainFrame.rotationPointY += 6F;
				} else {//中腰
					upperBody.rotateAngleX = 0.5F;
					bipedHead.rotateAngleX -= 0.5F;
					bipedHead.rotationPointY += 1.0F;
					bipedBody.rotationPointY += 1.0F;
					rightLeg.rotationPointY -= 1.0F;
					leftLeg.rotationPointY -= 1.0F;
					hipBody.rotateAngleX -= 0.1F + littleMaidMobX.Helper.sin(f2 * 0.057F) * 0.03F;
					rightArm.rotateAngleX += 0.1F;
					leftArm.rotateAngleX += 0.1F;
					rightLeg.rotateAngleY -= 0.07F;
					leftLeg.rotateAngleY += 0.07F;
					rightLeg.rotateAngleX -= 0.7F;
					leftLeg.rotateAngleX -= 0.7F;
					rightLeg2.rotateAngleX += 0.32F;
					leftLeg2.rotateAngleX += 0.22F;
					//mainFrame.rotationPointY += 0.4F;
					rightLeg.rotateAngleX += 0.5F;
					leftLeg.rotateAngleX += 0.5F;
				}
				//しゃがみ歩行
				float f15 = littleMaidMobX.Helper.sin(f * 0.6565F); //wave1
				float f16 = littleMaidMobX.Helper.cos(f * 0.6565F); //wave2
				float f22 = f15 > f16 ? f15 : f16; //upper wave
				float f35 = f15 < f16 ? f15 : f16; //lower wave
				leftArm.rotateAngleX += f15 * 0.2F * f1;
				rightArm2.rotateAngleX -= f22 * 0.7F * f1;
				leftArm2.rotateAngleX += f35 * 0.7F * f1;
				leftLeg.rotateAngleX -= f15 * 0.2F * f1;
				rightLeg2.rotateAngleX += f22 * 0.7F * f1;
				leftLeg2.rotateAngleX -= f35 * 0.7F * f1;
				hipBody.rotateAngleY += f15 * 0.1F * f1 - bipedBody.rotateAngleY;
				breastR.rotateAngleX -= f16 * f16 * 0.18F * f1 - littleMaidMobX.Helper.sin(f2 * 0.057F) * 0.05F;
				breastL.rotateAngleX -= f16 * f16 * 0.18F * f1 - littleMaidMobX.Helper.sin(f2 * 0.057F) * 0.05F;
				mainFrame.rotationPointY += f16 * f16 * 0.5F;
			} else {
				//通常歩行
				upperBody.rotateAngleX = 0.0F;
				rightLeg.rotationPointZ = leftLeg.rotationPointZ = 0.0F;
				float f15 = littleMaidMobX.Helper.sin(f * 0.4444F); //wave1
				float f16 = littleMaidMobX.Helper.cos(f * 0.4444F); //wave2
				float f22 = f15 > f16 ? f15 : f16; //upper wave
				float f35 = f15 < f16 ? f15 : f16; //lower wave
				leftArm.rotateAngleX += f15 * 0.7F * f1;
				rightArm2.rotateAngleX -= f22 * 0.7F * f1;
				leftArm2.rotateAngleX += f35 * 0.7F * f1;
				leftLeg.rotateAngleX -= f15 * 0.9F * f1;
				rightLeg2.rotateAngleX += f22 * 0.9F * f1;
				leftLeg2.rotateAngleX -= f35 * 0.9F * f1;
				hipBody.rotateAngleY += f15 * 0.3F * f1 - bipedBody.rotateAngleY;
				breastR.rotateAngleX -= f16 * f16 * 0.18F * f1 - littleMaidMobX.Helper.sin(f2 * 0.057F) * 0.05F;
				breastL.rotateAngleX -= f16 * f16 * 0.18F * f1 - littleMaidMobX.Helper.sin(f2 * 0.057F) * 0.05F;
				mainFrame.rotationPointY += f16 * f16 * 0.1F;
			}
		}
		if (heldItem[1] != 0) {
			leftArm.rotateAngleX = leftArm.rotateAngleX * 0.5F - (float) Math.PI * 0.1F * heldItem[1];
		}
		if (heldItem[0] != 0) {
			rightArm.rotateAngleX = rightArm.rotateAngleX * 0.5F - (float) Math.PI * 0.1F * heldItem[0];
		}
		armSwing(f, f1, f2, f3, f4, f5, entityCaps);
			rightArm.rotateAngleX += littleMaidMobX.Helper.sin(f2 * 0.057F) * 0.05F - 0.5F;
			rightArm.rotateAngleZ -= 0.3F;
			Arms[0].rotateAngleZ -= 1.5F;
			Arms[0].rotateAngleX -= 0.5F;
			Arms[0].rotateAngleY += 1.5F;
			leftArm.rotateAngleX += littleMaidMobX.Helper.sin(f2 * 0.057F) * 0.05F - 0.5F;
			leftArm.rotateAngleZ += 0.3F;
			Arms[1].rotateAngleZ += 1.5F;
			Arms[1].rotateAngleX -= 0.5F;
			Arms[1].rotateAngleY -= 1.5F;
			breastR.rotationPointX += 0.1F;
			breastL.rotationPointX -= 0.1F;
		} else {
			if (aimedBow) {// 弓構え 腕
				float fr6 = littleMaidMobX.Helper.sin(onGrounds[0] * 3.141593F);
				float fl6 = littleMaidMobX.Helper.sin(onGrounds[1] * 3.141593F);
				float fr7 = littleMaidMobX.Helper.sin((1.0F - (1.0F - onGrounds[0]) * (1.0F - onGrounds[0])) * 3.141593F);
				float fl7 = littleMaidMobX.Helper.sin((1.0F - (1.0F - onGrounds[1]) * (1.0F - onGrounds[1])) * 3.141593F);
				rightArm.rotateAngleZ = 0.0F;
				leftArm.rotateAngleZ = 0.0F;
				rightArm.rotateAngleY = -(0.1F - fr6 * 0.6F);
				leftArm.rotateAngleY = 0.1F - fl6 * 0.6F;
				rightArm.rotateAngleX = -1.470796F;
				leftArm.rotateAngleX = -1.470796F;
				rightArm.rotateAngleX -= fr6 * 1.2F - fr7 * 0.4F;
				leftArm.rotateAngleX -= fl6 * 1.2F - fl7 * 0.4F;
				rightArm.rotateAngleZ += littleMaidMobX.Helper.cos(f2 * 0.08F) * 0.03F + 0.05F;
				leftArm.rotateAngleZ -= littleMaidMobX.Helper.cos(f2 * 0.08F) * 0.03F + 0.05F;
				rightArm.rotateAngleX += littleMaidMobX.Helper.sin(f2 * 0.057F) * 0.05F;
				leftArm.rotateAngleX -= littleMaidMobX.Helper.sin(f2 * 0.057F) * 0.05F;
				rightArm.rotateAngleX += bipedHead.rotateAngleX;
				leftArm.rotateAngleX += bipedHead.rotateAngleX;
				rightArm.rotateAngleY += bipedHead.rotateAngleY;
				leftArm.rotateAngleY += bipedHead.rotateAngleY;
			} else {// 通常
				rightArm.rotateAngleZ += 0.2F;
				leftArm.rotateAngleZ -= 0.2F;
				rightArm2.rotateAngleZ += 0.05F;
				leftArm2.rotateAngleZ -= 0.05F;
				rightArm.rotateAngleZ += littleMaidMobX.Helper.cos(f2 * 0.08F) * 0.03F + 0.05F;
				leftArm.rotateAngleZ -= littleMaidMobX.Helper.cos(f2 * 0.08F) * 0.03F + 0.05F;
				rightArm.rotateAngleX += littleMaidMobX.Helper.sin(f2 * 0.057F) * 0.05F;
				leftArm.rotateAngleX -= littleMaidMobX.Helper.sin(f2 * 0.057F) * 0.05F;
			}
		}
		float sinBody1X = littleMaidMobX.Helper.sin(bipedBody.rotateAngleX);
		float cosBody1X = 1F - littleMaidMobX.Helper.cos(bipedBody.rotateAngleX);
		bipedHead.rotationPointY += -headPosY * cosBody1X;
		breastFloats(f, f1, f2, f3, f4, f5, entityCaps);
	}
	public void setDefaultPause(float f, float f1, float f2, float f3, float f4, float f5, IModelCaps entityCaps) {
		//INIT POSITION
		bipedHead.setRotationPoint(0F, headPosY, 0F);
		HeadMount.setRotationPoint(0F, 0F, 0F);
		eyeR.setRotationPoint(0F, 0F, 0F);
		eyeL.setRotationPoint(0F, 0F, 0F);
		Ponytail.setRotationPoint(0F, -5.2F, 5F);
		BunchR.setRotationPoint(-4.5F, -5.5F, 1.7F);
		BunchL.setRotationPoint(4.5F, -5.5F, 1.7F);
		rightArm2.setRotationPoint(0F, 5F, 0.5F);
		Arms[0].setRotationPoint(-0.5F, 7F, 0F);
		leftArm.setRotationPoint(4F, bodyPosY - 7.5F, 0F);
		leftArm2.setRotationPoint(0F, 5F, 0.5F);
		Arms[1].setRotationPoint(0.5F, 7F, 0F);
		rightLeg2.setRotationPoint(0F, 6F, 0F);
		leftLeg.setRotationPoint(2F, legPosY, 0F);
		leftLeg2.setRotationPoint(0F, 6F, 0F);
		hemSkirtR1.setRotationPoint(0F, 0F, 2F);
		hemSkirtL1.setRotationPoint(0F, 0F, 2F);
		hemSkirtR2.setRotationPoint(0F, 0F, 1F);
		hemSkirtL2.setRotationPoint(0F, 0F, 1F);
		breastR.setRotationPoint(-0.5F, -7.2F, -2.1F);
		breastL.setRotationPoint(0.5F, -7.2F, -2.1F);
		hipBody.setRotationPoint(0F, 0F, 0F);
		bipedHead.rotateAngleX = 0F;
		bipedHead.rotateAngleY = 0F;
		//bipedHead.rotateAngleZ = 0F;
		Ponytail.rotateAngleX = 0.05F;
		Ponytail.rotateAngleY = 0F;
		Ponytail.rotateAngleZ = 0F;
		BunchR.rotateAngleX = 0F;
		BunchR.rotateAngleY = 0F;
		BunchR.rotateAngleZ = 0.05F;
		BunchL.rotateAngleX = 0F;
		BunchL.rotateAngleY = 0F;
		BunchL.rotateAngleZ = -0.05F;
		rightArm.rotateAngleY = 0F;
		rightArm.rotateAngleZ = 0F;
		rightArm2.rotateAngleX = 0F;
		rightArm2.rotateAngleY = 0F;
		rightArm2.rotateAngleZ = 0F;
		Arms[0].rotateAngleX = 0F;
		Arms[0].rotateAngleY = 0F;
		Arms[0].rotateAngleZ = 0F;
		leftArm.rotateAngleX = 0F;
		leftArm.rotateAngleY = 0F;
		leftArm.rotateAngleZ = 0F;
		leftArm2.rotateAngleX = 0F;
		leftArm2.rotateAngleY = 0F;
		leftArm2.rotateAngleZ = 0F;
		Arms[1].rotateAngleX = 0F;
		Arms[1].rotateAngleY = 0F;
		Arms[1].rotateAngleZ = 0F;
		rightLeg.rotateAngleY = 0.05F;
		rightLeg.rotateAngleZ = -0.05F;
		rightLeg2.rotateAngleX = 0.5F;
		rightLeg2.rotateAngleY = -0.1F;
		rightLeg2.rotateAngleZ = 0.02F;
		leftLeg.rotateAngleX = -0.05F;
		leftLeg.rotateAngleY = -0.05F;
		leftLeg.rotateAngleZ = 0.05F;
		leftLeg2.rotateAngleX = 0.5F;
		leftLeg2.rotateAngleY = 0.1F;
		leftLeg2.rotateAngleZ = -0.02F;
		Skirt.rotateAngleY = 0F;
		Skirt.rotateAngleZ = 0F;
		hemSkirtR1.rotateAngleX = 0F;
		hemSkirtR1.rotateAngleY = 0F;
		hemSkirtR1.rotateAngleZ = 0.05F;
		hemSkirtL1.rotateAngleX = 0F;
		hemSkirtL1.rotateAngleY = 0F;
		hemSkirtL1.rotateAngleZ = -0.05F;
		hemSkirtR2.rotateAngleX = 0F;
		hemSkirtR2.rotateAngleY = 0F;
		hemSkirtR2.rotateAngleZ = -0.03F;
		hemSkirtL2.rotateAngleX = 0F;
		hemSkirtL2.rotateAngleY = 0F;
		hemSkirtL2.rotateAngleZ = 0.03F;
		bipedBody.rotateAngleY = 0F;
		breastR.rotateAngleX = 0.785F;
		breastR.rotateAngleY = 0F;
		breastR.rotateAngleZ = -0.15F;
		breastL.rotateAngleX = 0.785F;
		breastL.rotateAngleY = 0F;
		breastL.rotateAngleZ = 0.15F;
		hipBody.rotateAngleX = 0.2F;
		hipBody.rotateAngleY = 0F;
		hipBody.rotateAngleZ = 0F;
		mainFrame.rotateAngleY = 0F;
		mainFrame.rotateAngleZ = 0F;
	}
	public void armSwing(float f, float f1, float f2, float f3, float f4, float f5, IModelCaps entityCaps) {
		if (armSwingFlag(entityCaps)) {
			// 腕振り
			float f6, f7, f8;
			f6 = littleMaidMobX.Helper.sin(littleMaidMobX.Helper.sqrt(onGrounds[0]) * (float) Math.PI * 2.0F);
			f7 = littleMaidMobX.Helper.sin(littleMaidMobX.Helper.sqrt(onGrounds[1]) * (float) Math.PI * 2.0F);
			bipedBody.rotateAngleY = (f6 - f7) * 0.2F;
			// R
			if (onGrounds[0] > 0F) {
				f6 = 1.0F - onGrounds[0];
				f6 *= f6;
				f6 *= f6;
				f6 = 1.0F - f6;
				f7 = littleMaidMobX.Helper.sin(f6 * (float) Math.PI);
				f8 = littleMaidMobX.Helper.sin(onGrounds[0] * (float) Math.PI) * -(bipedHead.rotateAngleX - 0.7F) * 0.75F;
				rightArm.rotateAngleX -= f7 * 1.2D + f8;
				rightArm.rotateAngleY += bipedBody.rotateAngleY * 2.0F;
				rightArm.rotateAngleZ = littleMaidMobX.Helper.sin(onGrounds[0] * 3.141593F) * -0.4F;
			}
			// L
			if (onGrounds[1] > 0F) {
				f6 = 1.0F - onGrounds[1];
				f6 *= f6;
				f6 *= f6;
				f6 = 1.0F - f6;
				f7 = littleMaidMobX.Helper.sin(f6 * (float) Math.PI);
				f8 = littleMaidMobX.Helper.sin(onGrounds[1] * (float) Math.PI) * -(bipedHead.rotateAngleX - 0.7F) * 0.75F;
				leftArm.rotateAngleX -= f7 * 1.2D + f8;
				leftArm.rotateAngleY += bipedBody.rotateAngleY * 2.0F;
				leftArm.rotateAngleZ = littleMaidMobX.Helper.sin(onGrounds[1] * 3.141593F) * 0.4F;
			}
		}
	}
	public void setArmorBipedHeadShowModel(IModelCaps entityCaps, boolean b) {
		super.setArmorBipedHeadShowModel(entityCaps, b);
		setCapsValue(entityCaps, caps_visible, Ponytail, b);
	public void setArmorBipedBodyShowModel(IModelCaps entityCaps, boolean b) {
		super.setArmorBipedBodyShowModel(entityCaps, b);
		setCapsValue(entityCaps, caps_visible, breastR, b);
	public void setArmorBipedRightLegShowModel(IModelCaps entityCaps, boolean b) {
		super.setArmorBipedRightLegShowModel(entityCaps, b);
		setCapsValue(entityCaps, caps_visible, hemSkirtR1, b);
		setCapsValue(entityCaps, caps_visible, hemSkirtR2, b);
	}
	public void setArmorBipedLeftLegShowModel(IModelCaps entityCaps, boolean b) {
		super.setArmorBipedLeftLegShowModel(entityCaps, b);
		setCapsValue(entityCaps, caps_visible, hemSkirtL1, b);
		setCapsValue(entityCaps, caps_visible, hemSkirtL2, b);
	}
	public void setArmorSkirtShowModel(IModelCaps entityCaps, boolean b) {
		setCapsValue(entityCaps, caps_visible, Skirt, b);
		Skirt.isHidden = !b;
		//Modchu_Debug.mDebug("setArmorSkirtShowModel b="+b);
	}
}