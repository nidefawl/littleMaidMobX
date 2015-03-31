/*     */ package mmmlibx.lib.multiModel.model.mc162;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ModelLittleMaidBase
/*     */   extends ModelMultiMMMBase
/*     */ {
/*     */   public ModelRenderer bipedTorso;
/*     */   public ModelRenderer bipedNeck;
/*     */   public ModelRenderer bipedHead;
/*     */   public ModelRenderer bipedRightArm;
/*     */   public ModelRenderer bipedLeftArm;
/*     */   public ModelRenderer bipedBody;
/*     */   public ModelRenderer bipedPelvic;
/*     */   public ModelRenderer bipedRightLeg;
/*     */   public ModelRenderer bipedLeftLeg;
/*     */   public ModelRenderer Skirt;
/*     */   
/*     */   public ModelLittleMaidBase() {}
/*     */   
/*     */   public ModelLittleMaidBase(float psize)
/*     */   {
/*  33 */     super(psize);
/*     */   }
/*     */   
/*     */ 
/*     */   public ModelLittleMaidBase(float psize, float pyoffset, int pTextureWidth, int pTextureHeight)
/*     */   {
/*  39 */     super(psize, pyoffset, pTextureWidth, pTextureHeight);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void initModel(float psize, float pyoffset)
/*     */   {
/*  47 */     this.Arms[0] = new ModelRenderer(this);
/*  48 */     this.Arms[0].setRotationPoint(-1.0F, 5.0F, -1.0F);
/*  49 */     this.Arms[1] = new ModelRenderer(this);
/*  50 */     this.Arms[1].setRotationPoint(1.0F, 5.0F, -1.0F);
/*  51 */     this.Arms[1].isInvertX = true;
/*     */     
/*  53 */     this.HeadMount.setRotationPoint(0.0F, -4.0F, 0.0F);
/*  54 */     this.HeadTop.setRotationPoint(0.0F, -13.0F, 0.0F);
/*     */     
/*     */ 
/*  57 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/*  58 */     this.bipedHead.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, psize);
/*  59 */     this.bipedHead.setTextureOffset(24, 0).addBox(-4.0F, 0.0F, 1.0F, 8, 4, 3, psize);
/*  60 */     this.bipedHead.setTextureOffset(24, 18).addBox(-5.0F, -7.0F, 0.2F, 1, 3, 3, psize);
/*  61 */     this.bipedHead.setTextureOffset(24, 18).addBox(4.0F, -7.0F, 0.2F, 1, 3, 3, psize);
/*  62 */     this.bipedHead.setTextureOffset(52, 10).addBox(-2.0F, -7.2F, 4.0F, 4, 4, 2, psize);
/*  63 */     this.bipedHead.setTextureOffset(46, 20).addBox(-1.5F, -6.8F, 4.0F, 3, 9, 3, psize);
/*  64 */     this.bipedHead.setTextureOffset(58, 21).addBox(-5.5F, -6.8F, 0.9F, 1, 8, 2, psize);
/*  65 */     this.bipedHead.setMirror(true);
/*  66 */     this.bipedHead.setTextureOffset(58, 21).addBox(4.5F, -6.8F, 0.9F, 1, 8, 2, psize);
/*  67 */     this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
/*     */     
/*  69 */     this.bipedRightArm = new ModelRenderer(this, 48, 0);
/*  70 */     this.bipedRightArm.addBox(-2.0F, -1.0F, -1.0F, 2, 8, 2, psize);
/*  71 */     this.bipedRightArm.setRotationPoint(-3.0F, 1.5F, 0.0F);
/*     */     
/*  73 */     this.bipedLeftArm = new ModelRenderer(this, 56, 0);
/*  74 */     this.bipedLeftArm.addBox(0.0F, -1.0F, -1.0F, 2, 8, 2, psize);
/*  75 */     this.bipedLeftArm.setRotationPoint(3.0F, 1.5F, 0.0F);
/*     */     
/*  77 */     this.bipedRightLeg = new ModelRenderer(this, 32, 19);
/*  78 */     this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 3, 9, 4, psize);
/*  79 */     this.bipedRightLeg.setRotationPoint(-1.0F, 0.0F, 0.0F);
/*     */     
/*  81 */     this.bipedLeftLeg = new ModelRenderer(this, 32, 19);
/*  82 */     this.bipedLeftLeg.setMirror(true);
/*  83 */     this.bipedLeftLeg.addBox(-1.0F, 0.0F, -2.0F, 3, 9, 4, psize);
/*  84 */     this.bipedLeftLeg.setRotationPoint(1.0F, 0.0F, 0.0F);
/*     */     
/*  86 */     this.Skirt = new ModelRenderer(this, 0, 16);
/*  87 */     this.Skirt.addBox(-4.0F, -2.0F, -4.0F, 8, 8, 8, psize);
/*  88 */     this.Skirt.setRotationPoint(0.0F, 0.0F, 0.0F);
/*     */     
/*  90 */     this.bipedBody = new ModelRenderer(this, 32, 8);
/*  91 */     this.bipedBody.addBox(-3.0F, 0.0F, -2.0F, 6, 7, 4, psize);
/*  92 */     this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
/*     */     
/*  94 */     this.bipedTorso = new ModelRenderer(this);
/*  95 */     this.bipedNeck = new ModelRenderer(this);
/*  96 */     this.bipedPelvic = new ModelRenderer(this);
/*  97 */     this.bipedPelvic.setRotationPoint(0.0F, 7.0F, 0.0F);
/*  98 */     this.mainFrame = new ModelRenderer(this, 0, 0);
/*  99 */     this.mainFrame.setRotationPoint(0.0F, 0.0F + pyoffset + 8.0F, 0.0F);
/* 100 */     this.mainFrame.addChild(this.bipedTorso);
/* 101 */     this.bipedTorso.addChild(this.bipedNeck);
/* 102 */     this.bipedTorso.addChild(this.bipedBody);
/* 103 */     this.bipedTorso.addChild(this.bipedPelvic);
/* 104 */     this.bipedNeck.addChild(this.bipedHead);
/* 105 */     this.bipedNeck.addChild(this.bipedRightArm);
/* 106 */     this.bipedNeck.addChild(this.bipedLeftArm);
/* 107 */     this.bipedHead.addChild(this.HeadMount);
/* 108 */     this.bipedHead.addChild(this.HeadTop);
/* 109 */     this.bipedRightArm.addChild(this.Arms[0]);
/* 110 */     this.bipedLeftArm.addChild(this.Arms[1]);
/* 111 */     this.bipedPelvic.addChild(this.bipedRightLeg);
/* 112 */     this.bipedPelvic.addChild(this.bipedLeftLeg);
/* 113 */     this.bipedPelvic.addChild(this.Skirt);
/*     */   }
/*     */   
/*     */   public float[] getArmorModelsSize()
/*     */   {
/* 118 */     return new float[] { 0.1F, 0.5F };
/*     */   }
/*     */   
/*     */   public float getHeight()
/*     */   {
/* 123 */     return 1.35F;
/*     */   }
/*     */   
/*     */   public float getWidth()
/*     */   {
/* 128 */     return 0.5F;
/*     */   }
/*     */   
/*     */   public float getyOffset()
/*     */   {
/* 133 */     return getHeight() * 0.9F;
/*     */   }
/*     */   
/*     */   public float getMountedYOffset()
/*     */   {
/* 138 */     return 0.35F;
/*     */   }
/*     */   
/*     */   public void setLivingAnimations(IModelCaps pEntityCaps, float par2, float par3, float pRenderPartialTicks)
/*     */   {
/* 143 */     float angle = ModelCapsHelper.getCapsValueFloat(pEntityCaps, 336, new Object[] { Float.valueOf(pRenderPartialTicks) });
/* 144 */     this.bipedHead.setRotateAngleZ(angle);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRotationAngles(float par1, float par2, float pTicksExisted, float pHeadYaw, float pHeadPitch, float par6, IModelCaps pEntityCaps)
/*     */   {
/* 154 */     setDefaultPause(par1, par2, pTicksExisted, pHeadYaw, pHeadPitch, par6, pEntityCaps);
/*     */     
/* 156 */     if (this.isRiding)
/*     */     {
/* 158 */       this.bipedRightArm.addRotateAngleX(-0.6283185F);
/* 159 */       this.bipedLeftArm.addRotateAngleX(-0.6283185F);
/* 160 */       this.bipedRightLeg.setRotateAngleX(-1.256637F);
/* 161 */       this.bipedLeftLeg.setRotateAngleX(-1.256637F);
/* 162 */       this.bipedRightLeg.setRotateAngleY(0.3141593F);
/* 163 */       this.bipedLeftLeg.setRotateAngleY(-0.3141593F);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 169 */     if (this.heldItem[1] != 0.0F) {
/* 170 */       this.bipedLeftArm.setRotateAngleX(this.bipedLeftArm.getRotateAngleX() * 0.5F);
/* 171 */       this.bipedLeftArm.addRotateAngleDegX(-18.0F * this.heldItem[1]);
/*     */     }
/* 173 */     if (this.heldItem[0] != 0.0F) {
/* 174 */       this.bipedRightArm.setRotateAngleX(this.bipedRightArm.getRotateAngleX() * 0.5F);
/* 175 */       this.bipedRightArm.addRotateAngleDegX(-18.0F * this.heldItem[0]);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 181 */     if (((this.onGrounds[0] > -9990.0F) || (this.onGrounds[1] > -9990.0F)) && (!this.aimedBow))
/*     */     {
/*     */ 
/* 184 */       float f6 = mh_sin(mh_sqrt_float(this.onGrounds[0]) * 3.1415927F * 2.0F);
/* 185 */       float f7 = mh_sin(mh_sqrt_float(this.onGrounds[1]) * 3.1415927F * 2.0F);
/* 186 */       this.bipedTorso.setRotateAngleY((f6 - f7) * 0.2F);
/* 187 */       this.Skirt.addRotateAngleY(this.bipedTorso.rotateAngleY);
/* 188 */       this.bipedRightArm.addRotateAngleY(this.bipedTorso.rotateAngleY);
/* 189 */       this.bipedLeftArm.addRotateAngleY(this.bipedTorso.rotateAngleY);
/* 190 */       this.bipedPelvic.addRotateAngleY(-this.bipedTorso.rotateAngleY);
/* 191 */       this.bipedHead.addRotateAngleY(-this.bipedTorso.rotateAngleY);
/*     */       
/* 193 */       if (this.onGrounds[0] > 0.0F) {
/* 194 */         f6 = 1.0F - this.onGrounds[0];
/* 195 */         f6 *= f6;
/* 196 */         f6 *= f6;
/* 197 */         f6 = 1.0F - f6;
/* 198 */         f7 = mh_sin(f6 * 3.1415927F);
/* 199 */         float f8 = mh_sin(this.onGrounds[0] * 3.1415927F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
/* 200 */         this.bipedRightArm.addRotateAngleX(-f7 * 1.2F - f8);
/* 201 */         this.bipedRightArm.addRotateAngleY(this.bipedTorso.rotateAngleY * 2.0F);
/* 202 */         this.bipedRightArm.setRotateAngleZ(mh_sin(this.onGrounds[0] * 3.141593F) * -0.4F);
/*     */       } else {
/* 204 */         this.bipedRightArm.addRotateAngleX(this.bipedTorso.rotateAngleY);
/*     */       }
/*     */       
/* 207 */       if (this.onGrounds[1] > 0.0F) {
/* 208 */         f6 = 1.0F - this.onGrounds[1];
/* 209 */         f6 *= f6;
/* 210 */         f6 *= f6;
/* 211 */         f6 = 1.0F - f6;
/* 212 */         f7 = mh_sin(f6 * 3.1415927F);
/* 213 */         float f8 = mh_sin(this.onGrounds[1] * 3.1415927F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F; ModelRenderer 
/* 214 */           tmp575_572 = this.bipedLeftArm;tmp575_572.rotateAngleX = ((float)(tmp575_572.rotateAngleX - (f7 * 1.2D + f8)));
/* 215 */         this.bipedLeftArm.rotateAngleY += this.bipedTorso.rotateAngleY * 2.0F;
/* 216 */         this.bipedLeftArm.setRotateAngleZ(mh_sin(this.onGrounds[1] * 3.141593F) * 0.4F);
/*     */       } else {
/* 218 */         this.bipedLeftArm.rotateAngleX += this.bipedTorso.rotateAngleY;
/*     */       }
/*     */     }
/* 221 */     if (this.isSneak)
/*     */     {
/* 223 */       this.bipedTorso.rotateAngleX += 0.5F;
/* 224 */       this.bipedNeck.rotateAngleX -= 0.5F;
/* 225 */       this.bipedRightArm.rotateAngleX += 0.2F;
/* 226 */       this.bipedLeftArm.rotateAngleX += 0.2F;
/*     */       
/* 228 */       this.bipedPelvic.addRotationPointY(-0.5F);
/* 229 */       this.bipedPelvic.addRotationPointZ(-0.6F);
/* 230 */       this.bipedPelvic.addRotateAngleX(-0.5F);
/* 231 */       this.bipedHead.rotationPointY = 1.0F;
/*     */       
/* 233 */       this.Skirt.rotationPointY -= 0.25F;
/* 234 */       this.Skirt.rotationPointZ += 0.0F;
/* 235 */       this.Skirt.addRotateAngleX(0.2F);
/* 236 */       this.bipedTorso.rotationPointY += 1.0F;
/*     */     }
/*     */     
/*     */ 
/* 240 */     if (this.isWait)
/*     */     {
/* 242 */       float lx = mh_sin(pTicksExisted * 0.067F) * 0.05F - 0.7F;
/* 243 */       this.bipedRightArm.setRotateAngle(lx, 0.0F, -0.4F);
/* 244 */       this.bipedLeftArm.setRotateAngle(lx, 0.0F, 0.4F);
/*     */ 
/*     */     }
/* 247 */     else if (this.aimedBow)
/*     */     {
/* 249 */       float lonGround = this.onGrounds[this.dominantArm];
/* 250 */       float f6 = mh_sin(lonGround * 3.141593F);
/* 251 */       float f7 = mh_sin((1.0F - (1.0F - lonGround) * (1.0F - lonGround)) * 3.141593F);
/* 252 */       float la = 0.1F - f6 * 0.6F;
/* 253 */       this.bipedRightArm.setRotateAngle(-1.470796F, -la, 0.0F);
/* 254 */       this.bipedLeftArm.setRotateAngle(-1.470796F, la, 0.0F);
/* 255 */       la = this.bipedHead.rotateAngleX;
/* 256 */       float lb = mh_sin(pTicksExisted * 0.067F) * 0.05F;
/* 257 */       float lc = f6 * 1.2F - f7 * 0.4F;
/* 258 */       this.bipedRightArm.addRotateAngleX(la + lb - lc);
/* 259 */       this.bipedLeftArm.addRotateAngleX(la - lb - lc);
/* 260 */       la = this.bipedHead.rotateAngleY;
/* 261 */       this.bipedRightArm.addRotateAngleY(la);
/* 262 */       this.bipedLeftArm.addRotateAngleY(la);
/* 263 */       la = mh_cos(pTicksExisted * 0.09F) * 0.05F + 0.05F;
/* 264 */       this.bipedRightArm.addRotateAngleZ(la);
/* 265 */       this.bipedLeftArm.addRotateAngleZ(-la);
/*     */     }
/*     */     else {
/* 268 */       float la = mh_sin(pTicksExisted * 0.067F) * 0.05F;
/* 269 */       float lc = 0.5F + mh_cos(pTicksExisted * 0.09F) * 0.05F + 0.05F;
/* 270 */       this.bipedRightArm.addRotateAngleX(la);
/* 271 */       this.bipedLeftArm.addRotateAngleX(-la);
/* 272 */       this.bipedRightArm.addRotateAngleZ(lc);
/* 273 */       this.bipedLeftArm.addRotateAngleZ(-lc);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setDefaultPause() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void setDefaultPause(float par1, float par2, float pTicksExisted, float pHeadYaw, float pHeadPitch, float par6, IModelCaps pEntityCaps)
/*     */   {
/* 285 */     super.setDefaultPause(par1, par2, pTicksExisted, pHeadYaw, pHeadPitch, par6, pEntityCaps);
/* 286 */     this.mainFrame.setRotationPoint(0.0F, 8.0F, 0.0F);
/* 287 */     this.bipedTorso.setRotationPoint(0.0F, 0.0F, 0.0F).setRotateAngle(0.0F, 0.0F, 0.0F);
/* 288 */     this.bipedNeck.setRotationPoint(0.0F, 0.0F, 0.0F).setRotateAngle(0.0F, 0.0F, 0.0F);
/* 289 */     this.bipedPelvic.setRotationPoint(0.0F, 7.0F, 0.0F).setRotateAngle(0.0F, 0.0F, 0.0F);
/* 290 */     this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 291 */     this.bipedHead.setRotateAngleDegY(pHeadYaw);
/* 292 */     this.bipedHead.setRotateAngleDegX(pHeadPitch);
/* 293 */     this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F).setRotateAngle(0.0F, 0.0F, 0.0F);
/* 294 */     this.bipedRightArm.setRotationPoint(-3.0F, 1.6F, 0.0F);
/* 295 */     this.bipedRightArm.setRotateAngle(mh_cos(par1 * 0.6662F + 3.141593F) * 2.0F * par2 * 0.5F, 0.0F, 0.0F);
/* 296 */     this.bipedLeftArm.setRotationPoint(3.0F, 1.6F, 0.0F);
/* 297 */     this.bipedLeftArm.setRotateAngle(mh_cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F, 0.0F, 0.0F);
/* 298 */     this.bipedRightLeg.setRotationPoint(-1.0F, 0.0F, 0.0F);
/* 299 */     this.bipedRightLeg.setRotateAngle(mh_cos(par1 * 0.6662F) * 1.4F * par2, 0.0F, 0.0F);
/* 300 */     this.bipedLeftLeg.setRotationPoint(1.0F, 0.0F, 0.0F);
/* 301 */     this.bipedLeftLeg.setRotateAngle(mh_cos(par1 * 0.6662F + 3.141593F) * 1.4F * par2, 0.0F, 0.0F);
/*     */     
/* 303 */     this.Skirt.setRotationPoint(0.0F, 0.0F, 0.0F).setRotateAngle(0.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void showAllParts()
/*     */   {
/* 310 */     this.bipedHead.setVisible(true);
/* 311 */     this.bipedBody.setVisible(true);
/* 312 */     this.bipedRightArm.setVisible(true);
/* 313 */     this.bipedLeftArm.setVisible(true);
/* 314 */     this.Skirt.setVisible(true);
/* 315 */     this.bipedRightLeg.setVisible(true);
/* 316 */     this.bipedLeftLeg.setVisible(true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int showArmorParts(int parts, int index)
/*     */   {
/* 324 */     boolean f = parts == 3;
/* 325 */     this.bipedHead.setVisible(f);
/*     */     
/* 327 */     f = parts == 2;
/* 328 */     this.bipedBody.setVisible(f);
/* 329 */     this.bipedRightArm.setVisible(f);
/* 330 */     this.bipedLeftArm.setVisible(f);
/*     */     
/* 332 */     f = parts == 1;
/* 333 */     this.Skirt.setVisible(f);
/*     */     
/* 335 */     f = parts == 0;
/* 336 */     this.bipedRightLeg.setVisible(f);
/* 337 */     this.bipedLeftLeg.setVisible(f);
/*     */     
/* 339 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */   public void renderItems(IModelCaps pEntityCaps)
/*     */   {
/* 345 */     GL11.glPushMatrix();
/*     */     
/* 347 */     this.Arms[0].loadMatrix();
/* 348 */     GL11.glTranslatef(0.0F, 0.05F, -0.05F);
/* 349 */     this.Arms[0].renderItems(this, pEntityCaps, false, 0);
/*     */     
/* 351 */     this.Arms[1].loadMatrix();
/* 352 */     GL11.glTranslatef(0.0F, 0.05F, -0.05F);
/* 353 */     this.Arms[1].renderItems(this, pEntityCaps, false, 1);
/*     */     
/* 355 */     boolean lplanter = ModelCapsHelper.getCapsValueBoolean(pEntityCaps, 274, new Object[0]);
/* 356 */     if ((ModelCapsHelper.getCapsValueBoolean(pEntityCaps, 273, new Object[0])) || (lplanter)) {
/* 357 */       this.HeadMount.loadMatrix();
/* 358 */       if (lplanter) {
/* 359 */         this.HeadTop.loadMatrix().renderItemsHead(this, pEntityCaps);
/*     */       } else {
/* 361 */         this.HeadMount.loadMatrix().renderItemsHead(this, pEntityCaps);
/*     */       }
/*     */     }
/* 364 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public void renderFirstPersonHand(IModelCaps pEntityCaps)
/*     */   {
/* 369 */     float var2 = 1.0F;
/* 370 */     GL11.glColor3f(var2, var2, var2);
/* 371 */     this.onGrounds[0] = (this.onGrounds[1] = 0.0F);
/* 372 */     setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, pEntityCaps);
/* 373 */     this.bipedRightArm.render(0.0625F);
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/model/mc162/ModelLittleMaidBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */