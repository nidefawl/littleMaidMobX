/*     */ package mmmlibx.lib;
/*     */ 
/*     */ import mmmlibx.lib.multiModel.model.mc162.IModelCaps;
/*     */ import mmmlibx.lib.multiModel.model.mc162.ModelCapsHelper;
/*     */ import mmmlibx.lib.multiModel.model.mc162.ModelMultiBase;
/*     */ import mmmlibx.lib.multiModel.model.mc162.ModelRenderer;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class MMM_ModelMulti_Steve
/*     */   extends ModelMultiBase
/*     */ {
/*     */   public ModelRenderer bipedHead;
/*     */   public ModelRenderer bipedHeadwear;
/*     */   public ModelRenderer bipedBody;
/*     */   public ModelRenderer bipedRightArm;
/*     */   public ModelRenderer bipedLeftArm;
/*     */   public ModelRenderer bipedRightLeg;
/*     */   public ModelRenderer bipedLeftLeg;
/*     */   public ModelRenderer bipedEars;
/*     */   public ModelRenderer bipedCloak;
/*     */   public ModelRenderer bipedTorso;
/*     */   public ModelRenderer bipedNeck;
/*     */   public ModelRenderer bipedPelvic;
/*     */   public ModelRenderer eyeR;
/*     */   public ModelRenderer eyeL;
/*     */   
/*     */   public MMM_ModelMulti_Steve() {}
/*     */   
/*     */   public MMM_ModelMulti_Steve(float psize)
/*     */   {
/*  31 */     super(psize);
/*     */   }
/*     */   
/*  34 */   public MMM_ModelMulti_Steve(float psize, float pyoffset, int pTextureWidth, int pTextureHeight) { super(psize, pyoffset, pTextureWidth, pTextureHeight); }
/*     */   
/*     */ 
/*     */   public void initModel(float psize, float pyoffset)
/*     */   {
/*  39 */     this.bipedCloak = new ModelRenderer(this, 0, 0);
/*  40 */     this.bipedCloak.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, psize);
/*  41 */     this.bipedEars = new ModelRenderer(this, 24, 0);
/*  42 */     this.bipedEars.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, psize);
/*     */     
/*  44 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/*  45 */     this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, psize);
/*  46 */     this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  47 */     this.bipedHeadwear = new ModelRenderer(this, 32, 0);
/*  48 */     this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, psize + 0.5F);
/*  49 */     this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  50 */     this.eyeL = new ModelRenderer(this, 0, 0);
/*  51 */     this.eyeL.addBox(0.0F, -5.0F, -4.001F, 4, 4, 0, psize);
/*  52 */     this.eyeL.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  53 */     this.eyeR = new ModelRenderer(this, 0, 4);
/*  54 */     this.eyeR.addBox(-4.0F, -5.0F, -4.001F, 4, 4, 0, psize);
/*  55 */     this.eyeR.setRotationPoint(0.0F, 0.0F, 0.0F);
/*     */     
/*  57 */     this.bipedBody = new ModelRenderer(this, 16, 16);
/*  58 */     this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, psize);
/*  59 */     this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
/*     */     
/*  61 */     this.bipedRightArm = new ModelRenderer(this, 40, 16);
/*  62 */     this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, psize);
/*  63 */     this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
/*  64 */     this.bipedLeftArm = new ModelRenderer(this, 40, 16);
/*  65 */     this.bipedLeftArm.mirror = true;
/*  66 */     this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, psize);
/*  67 */     this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
/*     */     
/*  69 */     this.bipedRightLeg = new ModelRenderer(this, 0, 16);
/*  70 */     this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, psize);
/*  71 */     this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
/*  72 */     this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
/*  73 */     this.bipedLeftLeg.mirror = true;
/*  74 */     this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, psize);
/*  75 */     this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
/*     */     
/*  77 */     this.HeadMount.setRotationPoint(0.0F, -4.0F, 0.0F);
/*  78 */     this.HeadTop.setRotationPoint(0.0F, -12.0F, 0.0F);
/*  79 */     this.Arms[0] = new ModelRenderer(this);
/*  80 */     this.Arms[0].setRotationPoint(-1.5F, 7.2F, -1.0F);
/*  81 */     this.Arms[1] = new ModelRenderer(this);
/*  82 */     this.Arms[1].setRotationPoint(1.5F, 7.2F, -1.0F);
/*     */     
/*  84 */     this.bipedTorso = new ModelRenderer(this);
/*  85 */     this.bipedNeck = new ModelRenderer(this);
/*  86 */     this.bipedPelvic = new ModelRenderer(this);
/*     */     
/*  88 */     this.mainFrame = new ModelRenderer(this);
/*  89 */     this.mainFrame.setRotationPoint(0.0F, pyoffset, 0.0F);
/*  90 */     this.mainFrame.addChild(this.bipedTorso);
/*  91 */     this.bipedTorso.addChild(this.bipedNeck);
/*  92 */     this.bipedTorso.addChild(this.bipedPelvic);
/*  93 */     this.bipedTorso.addChild(this.bipedBody);
/*  94 */     this.bipedNeck.addChild(this.bipedHead);
/*  95 */     this.bipedHead.addChild(this.bipedHeadwear);
/*  96 */     this.bipedHead.addChild(this.bipedEars);
/*  97 */     this.bipedHead.addChild(this.HeadMount);
/*  98 */     this.bipedHead.addChild(this.HeadTop);
/*  99 */     this.bipedHead.addChild(this.eyeL);
/* 100 */     this.bipedHead.addChild(this.eyeR);
/* 101 */     this.bipedNeck.addChild(this.bipedRightArm);
/* 102 */     this.bipedNeck.addChild(this.bipedLeftArm);
/* 103 */     this.bipedPelvic.addChild(this.bipedRightLeg);
/* 104 */     this.bipedPelvic.addChild(this.bipedLeftLeg);
/* 105 */     this.bipedRightArm.addChild(this.Arms[0]);
/* 106 */     this.bipedLeftArm.addChild(this.Arms[1]);
/* 107 */     this.bipedBody.addChild(this.bipedCloak);
/*     */     
/* 109 */     this.bipedEars.showModel = false;
/* 110 */     this.bipedCloak.showModel = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void render(IModelCaps pEntityCaps, float par2, float par3, float ticksExisted, float pheadYaw, float pheadPitch, float par7, boolean pIsRender)
/*     */   {
/* 117 */     setRotationAngles(par2, par3, ticksExisted, pheadYaw, pheadPitch, par7, pEntityCaps);
/* 118 */     this.mainFrame.render(par7, pIsRender);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setDefaultPause(float par1, float par2, float pTicksExisted, float pHeadYaw, float pHeadPitch, float par6, IModelCaps pEntityCaps)
/*     */   {
/* 124 */     this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F).setRotateAngle(0.0F, 0.0F, 0.0F);
/* 125 */     this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F).setRotateAngleDeg(pHeadPitch, pHeadYaw, 0.0F);
/* 126 */     this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F).setRotateAngle(0.0F, 0.0F, 0.0F);
/* 127 */     this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F).setRotateAngle(0.0F, 0.0F, 0.0F);
/* 128 */     this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F).setRotateAngle(0.0F, 0.0F, 0.0F);
/* 129 */     this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F).setRotateAngle(0.0F, 0.0F, 0.0F);
/* 130 */     this.bipedTorso.setRotationPoint(0.0F, 0.0F, 0.0F).setRotateAngle(0.0F, 0.0F, 0.0F);
/* 131 */     this.bipedNeck.setRotationPoint(0.0F, 0.0F, 0.0F).setRotateAngle(0.0F, 0.0F, 0.0F);
/* 132 */     this.bipedPelvic.setRotationPoint(0.0F, 0.0F, 0.0F).setRotateAngle(0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setRotationAngles(float par1, float par2, float pTicksExisted, float pHeadYaw, float pHeadPitch, float par6, IModelCaps pEntityCaps)
/*     */   {
/* 138 */     setDefaultPause(par1, par2, pTicksExisted, pHeadYaw, pHeadPitch, par6, pEntityCaps);
/*     */     
/*     */ 
/* 141 */     float lf1 = mh_cos(par1 * 0.6662F);
/* 142 */     float lf2 = mh_cos(par1 * 0.6662F + 3.1415927F);
/* 143 */     this.bipedRightArm.rotateAngleX = (lf2 * 2.0F * par2 * 0.5F);
/* 144 */     this.bipedLeftArm.rotateAngleX = (lf1 * 2.0F * par2 * 0.5F);
/* 145 */     this.bipedRightLeg.rotateAngleX = (lf1 * 1.4F * par2);
/* 146 */     this.bipedLeftLeg.rotateAngleX = (lf2 * 1.4F * par2);
/*     */     
/*     */ 
/* 149 */     if (this.isRiding) {
/* 150 */       this.bipedRightArm.addRotateAngleDegX(-36.0F);
/* 151 */       this.bipedLeftArm.addRotateAngleDegX(-36.0F);
/* 152 */       this.bipedRightLeg.addRotateAngleDegX(-72.0F);
/* 153 */       this.bipedLeftLeg.addRotateAngleDegX(-72.0F);
/* 154 */       this.bipedRightLeg.addRotateAngleDegY(18.0F);
/* 155 */       this.bipedLeftLeg.addRotateAngleDegY(-18.0F);
/*     */     }
/*     */     
/* 158 */     if (this.heldItem[0] > 0.0F) {
/* 159 */       this.bipedRightArm.rotateAngleX *= 0.5F;
/* 160 */       this.bipedRightArm.addRotateAngleDegX(-18.0F * this.heldItem[0]);
/*     */     }
/* 162 */     if (this.heldItem[1] > 0.0F) {
/* 163 */       this.bipedLeftArm.rotateAngleX *= 0.5F;
/* 164 */       this.bipedLeftArm.addRotateAngleDegX(-18.0F * this.heldItem[1]);
/*     */     }
/*     */     
/*     */ 
/* 168 */     if (((this.onGrounds[0] > -9990.0F) || (this.onGrounds[1] > -9990.0F)) && (!this.aimedBow))
/*     */     {
/* 170 */       float lf = 6.2831855F;
/* 171 */       lf1 = mh_sin(mh_sqrt_float(this.onGrounds[0]) * lf);
/* 172 */       lf2 = mh_sin(mh_sqrt_float(this.onGrounds[1]) * lf);
/* 173 */       this.bipedTorso.rotateAngleY = ((lf1 - lf2) * 0.2F);
/*     */       
/*     */ 
/* 176 */       if (this.onGrounds[0] > 0.0F) {
/* 177 */         lf = 1.0F - this.onGrounds[0];
/* 178 */         lf *= lf;
/* 179 */         lf *= lf;
/* 180 */         lf = 1.0F - lf;
/* 181 */         lf1 = mh_sin(lf * 3.1415927F);
/* 182 */         lf2 = mh_sin(this.onGrounds[0] * 3.1415927F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
/* 183 */         this.bipedRightArm.addRotateAngleX(-lf1 * 1.2F - lf2);
/* 184 */         this.bipedRightArm.addRotateAngleY(this.bipedTorso.rotateAngleY * 2.0F);
/* 185 */         this.bipedRightArm.setRotateAngleZ(mh_sin(this.onGrounds[0] * 3.141593F) * -0.4F);
/*     */       } else {
/* 187 */         this.bipedRightArm.rotateAngleX += this.bipedTorso.rotateAngleY;
/*     */       }
/*     */       
/* 190 */       if (this.onGrounds[1] > 0.0F) {
/* 191 */         lf = 1.0F - this.onGrounds[1];
/* 192 */         lf *= lf;
/* 193 */         lf *= lf;
/* 194 */         lf = 1.0F - lf;
/* 195 */         lf1 = mh_sin(lf * 3.1415927F);
/* 196 */         lf2 = mh_sin(this.onGrounds[1] * 3.1415927F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
/* 197 */         this.bipedLeftArm.addRotateAngleX(-lf1 * 1.2F - lf2);
/* 198 */         this.bipedLeftArm.addRotateAngleY(this.bipedTorso.rotateAngleY * 2.0F);
/* 199 */         this.bipedLeftArm.setRotateAngleZ(mh_sin(this.onGrounds[1] * 3.141593F) * 0.4F);
/*     */       } else {
/* 201 */         this.bipedLeftArm.rotateAngleX += this.bipedTorso.rotateAngleY;
/*     */       }
/*     */     }
/*     */     
/* 205 */     if (this.isSneak)
/*     */     {
/* 207 */       this.bipedBody.rotationPointY = 2.0F;
/* 208 */       this.bipedTorso.rotateAngleX += 0.5F;
/* 209 */       this.bipedHead.rotationPointY += 1.0F;
/* 210 */       this.bipedNeck.rotateAngleX -= 0.5F;
/* 211 */       this.bipedRightArm.rotateAngleX += 0.4F;
/* 212 */       this.bipedLeftArm.rotateAngleX += 0.4F;
/* 213 */       this.bipedRightLeg.rotateAngleX -= 0.5F;
/* 214 */       this.bipedLeftLeg.rotateAngleX -= 0.5F;
/* 215 */       this.bipedRightLeg.setRotationPoint(-1.9F, 9.8F, -0.8F);
/* 216 */       this.bipedLeftLeg.setRotationPoint(1.9F, 9.8F, -0.8F);
/*     */       
/* 218 */       this.bipedTorso.rotationPointY += 1.2F;
/*     */     }
/*     */     
/* 221 */     if (this.aimedBow) {
/* 222 */       lf1 = 0.0F;
/* 223 */       lf2 = 0.0F;
/* 224 */       this.bipedRightArm.rotateAngleZ = 0.0F;
/* 225 */       this.bipedLeftArm.rotateAngleZ = 0.0F;
/* 226 */       float lf = 0.1F - lf1 * 0.6F;
/* 227 */       this.bipedRightArm.rotateAngleY = (-lf + this.bipedHead.rotateAngleY);
/* 228 */       this.bipedLeftArm.rotateAngleY = (lf + this.bipedHead.rotateAngleY);
/* 229 */       lf = 1.5707964F;
/* 230 */       this.bipedRightArm.rotateAngleX = (-lf + this.bipedHead.rotateAngleX);
/* 231 */       this.bipedLeftArm.rotateAngleX = (-lf + this.bipedHead.rotateAngleX);
/* 232 */       this.bipedRightArm.rotateAngleX -= lf1 * 1.2F - lf2 * 0.4F;
/* 233 */       this.bipedLeftArm.rotateAngleX -= lf1 * 1.2F - lf2 * 0.4F;
/* 234 */       if (ModelCapsHelper.getCapsValueInt(pEntityCaps, 293, new Object[0]) == 0) {
/* 235 */         this.bipedLeftArm.rotateAngleY += 0.4F;
/*     */       } else {
/* 237 */         this.bipedRightArm.rotateAngleY += 0.4F;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 242 */     float lf = mh_cos(pTicksExisted * 0.09F) * 0.05F + 0.05F;
/* 243 */     this.bipedRightArm.rotateAngleZ += lf;
/* 244 */     this.bipedLeftArm.rotateAngleZ -= lf;
/* 245 */     lf = mh_sin(pTicksExisted * 0.067F) * 0.05F;
/* 246 */     this.bipedRightArm.rotateAngleX += lf;
/* 247 */     this.bipedLeftArm.rotateAngleX -= lf;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void renderItems(IModelCaps pEntityCaps)
/*     */   {
/* 254 */     GL11.glPushMatrix();
/*     */     
/*     */ 
/* 257 */     this.Arms[0].loadMatrix();
/*     */     
/* 259 */     this.Arms[0].renderItems(this, pEntityCaps, false, 0);
/*     */     
/* 261 */     this.Arms[1].loadMatrix();
/*     */     
/* 263 */     this.Arms[1].renderItems(this, pEntityCaps, false, 1);
/*     */     
/* 265 */     boolean lplanter = ModelCapsHelper.getCapsValueBoolean(pEntityCaps, 274, new Object[0]);
/* 266 */     if ((ModelCapsHelper.getCapsValueBoolean(pEntityCaps, 273, new Object[0])) || (lplanter)) {
/* 267 */       if (lplanter) {
/* 268 */         this.HeadTop.loadMatrix().renderItemsHead(this, pEntityCaps);
/*     */       } else {
/* 270 */         this.HeadMount.loadMatrix().renderItemsHead(this, pEntityCaps);
/*     */       }
/*     */     }
/* 273 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */   public void renderFirstPersonHand(IModelCaps pEntityCaps)
/*     */   {
/* 279 */     float var2 = 1.0F;
/* 280 */     GL11.glColor3f(var2, var2, var2);
/* 281 */     this.onGrounds[0] = (this.onGrounds[1] = 0.0F);
/* 282 */     setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, pEntityCaps);
/* 283 */     this.bipedRightArm.render(0.0625F);
/*     */   }
/*     */   
/*     */   public float[] getArmorModelsSize()
/*     */   {
/* 288 */     return new float[] { 0.5F, 1.0F };
/*     */   }
/*     */   
/*     */   public float getHeight()
/*     */   {
/* 293 */     return 1.8F;
/*     */   }
/*     */   
/*     */   public float getWidth()
/*     */   {
/* 298 */     return 0.6F;
/*     */   }
/*     */   
/*     */   public float getyOffset()
/*     */   {
/* 303 */     return 1.62F;
/*     */   }
/*     */   
/*     */   public float getMountedYOffset()
/*     */   {
/* 308 */     return getHeight() * 0.75F;
/*     */   }
/*     */   
/*     */   public boolean isItemHolder()
/*     */   {
/* 313 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   public float getLeashOffset(IModelCaps pEntityCaps)
/*     */   {
/* 319 */     return 0.2F;
/*     */   }
/*     */   
/*     */   public int showArmorParts(int parts, int index)
/*     */   {
/* 324 */     if (index == 0) {
/* 325 */       this.bipedHead.isRendering = (parts == 3);
/* 326 */       this.bipedHeadwear.isRendering = (parts == 3);
/* 327 */       this.bipedBody.isRendering = (parts == 1);
/* 328 */       this.bipedRightArm.isRendering = (parts == 2);
/* 329 */       this.bipedLeftArm.isRendering = (parts == 2);
/* 330 */       this.bipedRightLeg.isRendering = (parts == 1);
/* 331 */       this.bipedLeftLeg.isRendering = (parts == 1);
/*     */     } else {
/* 333 */       this.bipedHead.isRendering = (parts == 3);
/* 334 */       this.bipedHeadwear.isRendering = (parts == 3);
/* 335 */       this.bipedBody.isRendering = (parts == 2);
/* 336 */       this.bipedRightArm.isRendering = (parts == 2);
/* 337 */       this.bipedLeftArm.isRendering = (parts == 2);
/* 338 */       this.bipedRightLeg.isRendering = (parts == 0);
/* 339 */       this.bipedLeftLeg.isRendering = (parts == 0);
/*     */     }
/* 341 */     return -1;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_ModelMulti_Steve.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */