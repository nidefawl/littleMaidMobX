package littleMaidMobX.model.modchu;import littleMaidMobX.Helper;import littleMaidMobX.LittleMaidMobX;import littleMaidMobX.config.ModelConfig;import littleMaidMobX.entity.EntityLittleMaid;import littleMaidMobX.model.ModelMultiBase;import littleMaidMobX.model.caps.IModelCaps;import littleMaidMobX.model.caps.ModelCapsHelper;import littleMaidMobX.render.model.ModelRenderer;import net.minecraft.block.Block;import net.minecraft.init.Blocks;import net.minecraft.inventory.IInventory;import net.minecraft.item.Item;import net.minecraft.item.ItemSkull;import net.minecraft.item.ItemStack;import org.lwjgl.opengl.GL11;public abstract class ModelModchuBaseBiped extends ModelMultiBase {	public ModelRenderer bipedHead;	public ModelRenderer bipedHeadwear;	public ModelRenderer bipedBody;	public ModelRenderer bipedRightArm;	public ModelRenderer bipedLeftArm;	public ModelRenderer bipedRightLeg;	public ModelRenderer bipedLeftLeg;	public ModelRenderer bipedEars;	public ModelRenderer bipedCloak;	public float modelSize;		public ModelModchuBaseBiped() {		this(0.0F);	}		public ModelModchuBaseBiped(float psize) {		this(psize, 0.0F);	}		public ModelModchuBaseBiped(float psize, float pyoffset) {		this(psize, pyoffset, 64, 32);	}		public ModelModchuBaseBiped(float psize, float pyoffset, int par3, int par4, Object... o) {		super(psize, pyoffset, par3, par4);		isSneak = false;		aimedBow = false;		textureWidth = par3 < 0 ? 64 : par3;		textureHeight = par4 < 0 ? 32 : par4;		initModel(psize, pyoffset, true);		modelSize = psize;		//Modchu_Debug.mDebug("this="+this+" modelSize="+modelSize);	}	public void afterInit(float f, float f1) {		armsinit(f, f1);		defaultAddChild();	}		public void armsinit(float f, float f1) {	}		public void defaultAddChild() {	}	@Override	public void render(IModelCaps entityCaps, float f, float f1, float f2, float pheadYaw, float pheadPitch, float f5, boolean pIsRender) {		setRotationAngles(f, f1, f2, pheadYaw, pheadPitch, f5, entityCaps);		mainFrame.render(f5, pIsRender);	}	@Override	public void setLivingAnimations(IModelCaps entityCaps, float f, float f1, float f2) {//		entityCaps.setCapsValue(caps_setLivingAnimationsBefore, this, f, f1, f2);		setLivingAnimationsLM(entityCaps, f, f1, f2);//		if (entityCaps instanceof IModelCaps//				&& ModelCapsHelper.getCapsValueByte(this, entityCaps, entityCaps.caps_EntityType) == entityCaps.entityType_LMM) {			setLivingAnimationsAfter(entityCaps, f, f1, f2);//		} else {//			entityCaps.setCapsValue(caps_setLivingAnimationsAfter, this, f, f1, f2);//		}	}	public void setLivingAnimationsLM(IModelCaps entityCaps, float f, float f1, float f2) {	}	@Override	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, IModelCaps entityCaps) {		if (entityCaps != null); else {			LittleMaidMobX.DebugModel("setRotationAngles entityCaps == null !! this="+this);			//return;		}//		if (entityCaps != null) entityCaps.setCapsValue(caps_setRotationAnglesBefore, this, f, f1, f2, f3, f4, f5);		setRotationAnglesLM(f, f1, f2, f3, f4, f5, entityCaps);//		if (entityCaps != null) entityCaps.setCapsValue(caps_setRotationAnglesAfter, this, f, f1, f2, f3, f4, f5);	}	public void setRotationAnglesLM(float f, float f1, float f2, float f3, float f4, float f5, IModelCaps entityCaps) {	}	@Override	public void renderItems(IModelCaps entityCaps) {		if (entityCaps != null) {			renderItemsLM(entityCaps);		}	}	public void renderItemsLM(IModelCaps entityCaps) {				GL11.glPushMatrix();		// R		Arms[0].loadMatrix();		GL11.glTranslatef(0F, 0.05F, -0.05F);		Arms[0].renderItems(this, entityCaps, false, 0);		// L		Arms[1].loadMatrix();		GL11.glTranslatef(0F, 0.05F, -0.05F);		Arms[1].renderItems(this, entityCaps, false, 1);		renderItemsHead(entityCaps);		renderItemsArmorHead(entityCaps);		GL11.glPopMatrix();	}	public void renderItemsHead(IModelCaps entityCaps) {				if (entityCaps != null); else return;		Object itemstack = entityCaps.getCapsValue(caps_HeadMount, 9);		boolean isPlanter = ModelCapsHelper.getCapsValueBoolean(entityCaps, caps_isPlanter, itemstack);		boolean isCamouflage = ModelCapsHelper.getCapsValueBoolean(entityCaps, caps_isCamouflage, itemstack);		int addSupport = addSupportChecks(entityCaps, itemstack);		//Modchu_Debug.mDebug("renderItemsHead itemstack="+itemstack);		//Modchu_Debug.mDebug("renderItemsHead isPlanter="+isPlanter);		//Modchu_Debug.mDebug("renderItemsHead isCamouflage="+isCamouflage);		//Modchu_Debug.mDebug("renderItemsHead addSupport="+addSupport);		if (isCamouflage				| isPlanter				| addSupport > -1) {//			float scale = 1.0F;			Object inventory = entityCaps.getCapsValue(caps_Inventory);			//Modchu_Debug.mDebug("renderItemsHead 2 inventory="+inventory);			if (inventory instanceof IInventory) {				/*Object pEntity = entityCaps.getCapsValue(caps_Entity);				int slot = pEntity instanceof EntityPlayer ? 10 : 16;				ItemStack itemstack1 = ((IInventory) inventory).getStackInSlot(slot);				//Modchu_Debug.mDebug("renderItemsHead 3 itemstack1="+itemstack1);				if (itemstack1 != null) {					Item item2 = itemstack1.getItem();					//Modchu_Debug.mDebug("renderItemsHead 4 item2="+item2);					//Modchu_Debug.mDebug("renderItemsHead 5 dyePowder="+dyePowder);					if (item2 == Items.dye) {						scale = 1.0F + (0.2F * itemstack1.getItemDamage());						//Modchu_Debug.mDebug("renderItemsHead 6 ");					}				}*/				//Modchu_Debug.mDebug("renderItemsHead addSupport="+addSupport);				if (isPlanter						| (addSupport > -1								&& addSupport < 3)) {					headTopLoadMatrix();					//TODO: implement new item render code in ModelRenderer. 					// tho i'm not sure what it does besides scaling and 1.8 support										HeadTop.renderItemsHead(this, entityCaps);//					HeadTop.renderItemsHead(this, entityCaps, scale, addSupport);//					Modchu_Reflect.invokeMethod(ModelRenderer.class, "renderItemsHead", new Class[]{ Object.class, Modchu_IEntityCapsBase.class, float.class, int.class }, HeadTop, new Object[]{ this, entityCaps, scale, addSupport });					//HeadTop.loadMatrix().renderItemsHead(this, entityCaps);				} else {					headMountLoadMatrix();					HeadMount.renderItemsHead(this, entityCaps);//					Modchu_Reflect.invokeMethod(ModelRenderer.class, "renderItemsHead", new Class[]{ Object.class, Modchu_IEntityCapsBase.class, float.class, int.class }, HeadMount, new Object[]{ this, entityCaps, scale, addSupport });					//HeadMount.loadMatrix().renderItemsHead(this, entityCaps);				}			}		}	}	public void headMountLoadMatrix() {		HeadMount.loadMatrix();	}	public void headTopLoadMatrix() {		HeadTop.loadMatrix();	}	public void renderItemsArmorHead(IModelCaps entityCaps) {				//Modchu_Debug.mDebug("renderItemsArmorHead");		Object itemstack = ModelCapsHelper.getCapsValue(entityCaps, caps_currentArmor, new Object[]{ 3 });		if (itemstack != null) {			//Modchu_Debug.mDebug("renderItemsArmorHead 1");			int addSupport = addSupportChecks(entityCaps, itemstack, 1);			headMountRenderItems(entityCaps, itemstack, addSupport);		}	}	public void headMountRenderItems(IModelCaps entityCaps, Object itemstack, int addSupport) {		Object pEntity = ModelCapsHelper.getCapsValue(entityCaps, IModelCaps.caps_Entity);		if (Helper.isCamouflage(itemstack) || Helper.isPlanter(itemstack) || addSupport > -1) {			//Modchu_Debug.mDebug("headMountRenderItems 1");			/*Object inventory = entityCaps.getCapsValue(caps_Inventory);			//Modchu_Debug.mDebug("renderItemsHead 2 inventory="+inventory);			float scale = 1.0F;			if (inventory instanceof IInventory) {				int slot = pEntity instanceof EntityPlayer ? 10 : 16;				ItemStack itemstack1 = ((IInventory) inventory).getStackInSlot(slot);				//Modchu_Debug.mDebug("renderItemsHead 3 itemstack1="+itemstack1);				if (itemstack1 != null) {					Item item2 = itemstack1.getItem();					//Modchu_Debug.mDebug("renderItemsHead 4 item2="+item2);					//Modchu_Debug.mDebug("renderItemsHead 5 dyePowder="+dyePowder);					if (item2 == Items.dye) {						scale = 1.0F + (0.2F * itemstack1.getItemDamage());						//Modchu_Debug.mDebug("renderItemsHead 6 ");					}				}			}			*/			GL11.glPushMatrix();			headMountLoadMatrix();			HeadMount.renderItemsHead(this, entityCaps);//			Modchu_Reflect.invokeMethod(ModelRenderer.class, "renderItemsHead", new Class[]{ Object.class, Modchu_IEntityCapsBase.class, Modchu_Reflect.loadClass("ItemStack"), float.class, int.class }, HeadMount, new Object[]{ this, entityCaps, itemstack, scale, addSupport });			GL11.glPopMatrix();			//Modchu_Debug.mDebug("headMountRenderItems end");		}	}	public int addSupportChecks(IModelCaps entityCaps, Object itemstack) {		return addSupportChecks(entityCaps, itemstack, 0);	}		public int addSupportChecks(IModelCaps entityCaps, Object itemstack, int type) {		if (itemstack instanceof ItemStack) {			ItemStack stack = (ItemStack) itemstack;			Item item = stack.getItem();			if (item instanceof ItemSkull) {				return 3;			}			Block b = Block.getBlockFromItem(item);			if (b == Blocks.pumpkin) {				return 4;			}		}		return -1;	}	public ModelRenderer getArms(int i) {		return Arms[i];	}	public ModelRenderer getHeadMount() {		return HeadMount;	}		public void setRotationAnglesfirstPerson(float f, float f1, float f2, float f3, float f4, float f5, IModelCaps entityCaps) {	}		public void changeColor(IModelCaps entityCaps) {	}		public void boneProcessing(float f, float f1, float f2, float f3, float f4, float f5, IModelCaps entityCaps) {	}	@Override	public float getyOffset(IModelCaps pEntityCaps) {		return 1.62F;	}	public float getRidingHeight(IModelCaps entityCaps) {		return getHeight(entityCaps);	}	public float getRidingWidth(IModelCaps entityCaps) {		return getWidth(entityCaps);	}	public float getRidingYOffset(IModelCaps entityCaps) {		return getyOffset(entityCaps);	}		@Override	public float getMountedYOffset(IModelCaps entityCaps) {		return 0.75F;	}		public double getSittingYOffset(IModelCaps entityCaps) {		return -0.35D;	}		public float ridingViewCorrection(IModelCaps entityCaps) {		return 0.0F;	}		public float getModelScale(IModelCaps entityCaps) {		return 0.9375F;	}	private int getMaidColor(IModelCaps entityCaps) {		Object entityliving = ModelCapsHelper.getCapsValue(entityCaps, IModelCaps.caps_Entity);		if (entityliving instanceof EntityLittleMaid) {			return ((EntityLittleMaid)entityliving).getColor();		}		return 0;	}	private void setModchuRemodelingModel(boolean b) {		ModelConfig.modchuRemodelingModel = b;	}	public ModelRenderer getBipedHead(IModelCaps entityCaps) {		return bipedHead;	}	public Object getRightArm(IModelCaps entityCaps) {		return getBipedRightArm(entityCaps);	}	@Override	public Object getCapsValue(int pIndex, Object... pArg) {		if (pIndex == caps_armorType) {			return getArmorType();		}		return super.getCapsValue(pIndex, pArg);	}	private int getArmorType() {		float[] f = getArmorModelsSize();		//Modchu_Debug.mlDebug("getArmorType() modelSize="+modelSize+" "+this);		//Modchu_Debug.mlDebug("getArmorType() modelSize="+modelSize+" f[0]="+f[0]+" f[1]="+f[1]);		return modelSize == f[0] ? 1 : modelSize == f[1] ? 2 : 0;	}	public ModelRenderer getDominantArm(IModelCaps entityCaps) {//		if (ModelCapsHelper.getCapsValueInt(this, entityCaps, caps_dominantArm, getCapsValue(caps_armorType)) == 0) return bipedRightArm;		return bipedLeftArm;	}	public ModelRenderer getBipedRightArm(IModelCaps entityCaps) {		return bipedRightArm;	}	public ModelRenderer getBipedLeftArm(IModelCaps entityCaps) {		return bipedLeftArm;	}	public ModelRenderer getBipedRightLeg(IModelCaps entityCaps) {		return bipedRightLeg;	}	public ModelRenderer getBipedLeftLeg(IModelCaps entityCaps) {		return bipedLeftLeg;	}//	public ModelRenderer getNotDominantArm(IModelCaps entityCaps) {//		if (ModelCapsHelper.getCapsValueInt(this, entityCaps, caps_dominantArm, getCapsValue(caps_armorType)) == 0) return bipedLeftArm;//		return bipedRightArm;//	}	@Override	public void renderFirstPersonHand(IModelCaps entityCaps/*, int i*/) {		if (entityCaps != null) {			ModelRenderer ModelRenderer = /*i == 0 ? */getDominantArm(entityCaps)/* : getNotDominantArm(entityCaps)*/;			if (ModelRenderer != null) ModelRenderer.render(0.0625F);		}	}	public boolean armSwingFlag(IModelCaps entityCaps) {		return (onGrounds[0] > -9990F				| onGrounds[1] > -9990F)				&& !ModelCapsHelper.getCapsValueBoolean(this, entityCaps, caps_aimedBow)//				&& !ModelCapsHelper.getCapsValueBoolean(this, entityCaps, caps_oldwalking)//				&& (!isGulliver()//						| (isGulliver()//								&& !ModelCapsHelper.getCapsValueBoolean(entityCaps, caps_freeVariable, "isGliding")))								;	}		public void setModelAttributes(Object model) {	}			public void setLivingAnimationsAfter(IModelCaps entityCaps, float f, float f1, float f2) {		if (bipedHead != null				&& entityCaps != null) {			float angle = ModelCapsHelper.getCapsValueFloat(this, entityCaps, caps_interestedAngle, f2);			bipedHead.rotateAngleZ = angle;		}	}	public void setArmorBipedBodyShowModel(IModelCaps entityCaps, boolean b) {		setCapsValue(entityCaps, caps_visible, bipedBody, b);	}	public void setArmorBipedRightArmShowModel(IModelCaps entityCaps, boolean b) {		setCapsValue(entityCaps, caps_visible, bipedRightArm, b);	}	public void setArmorBipedLeftArmShowModel(IModelCaps entityCaps, boolean b) {		setCapsValue(entityCaps, caps_visible, bipedLeftArm, b);	}	public void setArmorBipedRightLegShowModel(IModelCaps entityCaps, boolean b) {		setCapsValue(entityCaps, caps_visible, bipedRightLeg, b);	}	public void setArmorBipedLeftLegShowModel(IModelCaps entityCaps, boolean b) {		setCapsValue(entityCaps, caps_visible, bipedLeftLeg, b);	}	public void setArmorSkirtShowModel(IModelCaps entityCaps, boolean b) {	}	public void setArmorBipedHeadShowModel(IModelCaps entityCaps, boolean b) {		setCapsValue(entityCaps, caps_visible, bipedHead, b);		setCapsValue(entityCaps, caps_visible, bipedHeadwear, b);	}}
