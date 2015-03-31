/*     */ package mmmlibx.lib.multiModel.model.mc162;
/*     */ 
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModelLittleMaid_Archetype
/*     */   extends ModelLittleMaidBase
/*     */ {
/*     */   public ModelRenderer bipedHeadwear;
/*     */   public ModelRenderer ChignonR;
/*     */   public ModelRenderer ChignonL;
/*     */   public ModelRenderer ChignonB;
/*     */   public ModelRenderer Tail;
/*     */   public ModelRenderer SideTailR;
/*     */   public ModelRenderer SideTailL;
/*     */   
/*     */   public ModelLittleMaid_Archetype() {}
/*     */   
/*     */   public ModelLittleMaid_Archetype(float f)
/*     */   {
/*  25 */     super(f);
/*     */   }
/*     */   
/*     */   public ModelLittleMaid_Archetype(float f, float f1, int pTextureWidth, int pTextureHeight) {
/*  29 */     super(f, f1, pTextureWidth, pTextureHeight);
/*     */   }
/*     */   
/*     */   public void initModel(float psize, float pyoffset)
/*     */   {
/*  34 */     pyoffset += 8.0F;
/*     */     
/*     */ 
/*  37 */     this.Arms = new ModelRenderer[1];
/*  38 */     this.Arms[0] = new ModelRenderer(this, 0, 0);
/*  39 */     this.Arms[0].setRotationPoint(-1.0F, 5.0F, -1.0F);
/*  40 */     this.HeadMount.setRotationPoint(0.0F, -4.0F, 0.0F);
/*  41 */     this.HeadTop.setRotationPoint(0.0F, -13.0F, 0.0F);
/*     */     
/*  43 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/*  44 */     this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, psize);
/*  45 */     this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  46 */     this.bipedHead.addChild(this.HeadMount);
/*  47 */     this.bipedHead.addChild(this.HeadTop);
/*     */     
/*  49 */     this.bipedHeadwear = new ModelRenderer(this, 24, 0);
/*  50 */     this.bipedHeadwear.addBox(-4.0F, 0.0F, 1.0F, 8, 4, 3, psize);
/*  51 */     this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  52 */     this.bipedHead.addChild(this.bipedHeadwear);
/*     */     
/*  54 */     this.bipedBody = new ModelRenderer(this, 32, 8);
/*  55 */     this.bipedBody.addBox(-3.0F, 0.0F, -2.0F, 6, 7, 4, psize);
/*  56 */     this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
/*     */     
/*  58 */     this.bipedRightArm = new ModelRenderer(this, 48, 0);
/*  59 */     this.bipedRightArm.addBox(-2.0F, -1.0F, -1.0F, 2, 8, 2, psize);
/*  60 */     this.bipedRightArm.setRotationPoint(-3.0F, 1.5F, 0.0F);
/*  61 */     this.bipedRightArm.addChild(this.Arms[0]);
/*     */     
/*  63 */     this.bipedLeftArm = new ModelRenderer(this, 56, 0);
/*  64 */     this.bipedLeftArm.addBox(0.0F, -1.0F, -1.0F, 2, 8, 2, psize);
/*  65 */     this.bipedLeftArm.setRotationPoint(3.0F, 1.5F, 0.0F);
/*     */     
/*  67 */     this.bipedRightLeg = new ModelRenderer(this, 32, 19);
/*  68 */     this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 3, 9, 4, psize);
/*  69 */     this.bipedRightLeg.setRotationPoint(-1.0F, 7.0F, 0.0F);
/*     */     
/*  71 */     this.bipedLeftLeg = new ModelRenderer(this, 32, 19);
/*  72 */     this.bipedLeftLeg.setMirror(true);
/*  73 */     this.bipedLeftLeg.addBox(-1.0F, 0.0F, -2.0F, 3, 9, 4, psize);
/*  74 */     this.bipedLeftLeg.setRotationPoint(1.0F, 7.0F, 0.0F);
/*     */     
/*  76 */     this.Skirt = new ModelRenderer(this, 0, 16);
/*  77 */     this.Skirt.addBox(-4.0F, -2.0F, -4.0F, 8, 8, 8, psize);
/*  78 */     this.Skirt.setRotationPoint(0.0F, 7.0F, 0.0F);
/*     */     
/*  80 */     this.ChignonR = new ModelRenderer(this, 24, 18);
/*  81 */     this.ChignonR.addBox(-5.0F, -7.0F, 0.2F, 1, 3, 3, psize);
/*  82 */     this.ChignonR.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  83 */     this.bipedHead.addChild(this.ChignonR);
/*     */     
/*  85 */     this.ChignonL = new ModelRenderer(this, 24, 18);
/*  86 */     this.ChignonL.addBox(4.0F, -7.0F, 0.2F, 1, 3, 3, psize);
/*  87 */     this.ChignonL.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  88 */     this.bipedHead.addChild(this.ChignonL);
/*     */     
/*  90 */     this.ChignonB = new ModelRenderer(this, 52, 10);
/*  91 */     this.ChignonB.addBox(-2.0F, -7.2F, 4.0F, 4, 4, 2, psize);
/*  92 */     this.ChignonB.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  93 */     this.bipedHead.addChild(this.ChignonB);
/*     */     
/*  95 */     this.Tail = new ModelRenderer(this, 46, 20);
/*  96 */     this.Tail.addBox(-1.5F, -6.8F, 4.0F, 3, 9, 3, psize);
/*  97 */     this.Tail.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  98 */     this.bipedHead.addChild(this.Tail);
/*     */     
/* 100 */     this.SideTailR = new ModelRenderer(this, 58, 21);
/* 101 */     this.SideTailR.addBox(-5.5F, -6.8F, 0.9F, 1, 8, 2, psize);
/* 102 */     this.SideTailR.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 103 */     this.bipedHead.addChild(this.SideTailR);
/*     */     
/* 105 */     this.SideTailL = new ModelRenderer(this, 58, 21);
/* 106 */     this.SideTailL.setMirror(true);
/* 107 */     this.SideTailL.addBox(4.5F, -6.8F, 0.9F, 1, 8, 2, psize);
/* 108 */     this.SideTailL.setRotationPoint(0.0F, 0.0F, 0.0F);
/* 109 */     this.bipedHead.addChild(this.SideTailL);
/*     */     
/* 111 */     this.mainFrame = new ModelRenderer(this, 0, 0);
/* 112 */     this.mainFrame.setRotationPoint(0.0F, 0.0F + pyoffset, 0.0F);
/* 113 */     this.mainFrame.addChild(this.bipedHead);
/* 114 */     this.mainFrame.addChild(this.bipedBody);
/* 115 */     this.mainFrame.addChild(this.bipedRightArm);
/* 116 */     this.mainFrame.addChild(this.bipedLeftArm);
/* 117 */     this.mainFrame.addChild(this.bipedRightLeg);
/* 118 */     this.mainFrame.addChild(this.bipedLeftLeg);
/* 119 */     this.mainFrame.addChild(this.Skirt);
/*     */   }
/*     */   
/*     */ 
/*     */   public String getUsingTexture()
/*     */   {
/* 125 */     return null;
/*     */   }
/*     */   
/*     */   public float getHeight()
/*     */   {
/* 130 */     return 1.35F;
/*     */   }
/*     */   
/*     */   public float getWidth()
/*     */   {
/* 135 */     return 0.5F;
/*     */   }
/*     */   
/*     */   public void equippedBlockPosition()
/*     */   {
/* 140 */     GL11.glTranslatef(0.0F, 0.1275F, -0.3125F);
/*     */   }
/*     */   
/*     */   public void equippedItemPosition3D()
/*     */   {
/* 145 */     GL11.glTranslatef(0.02F, 0.13F, 0.0F);
/*     */   }
/*     */   
/*     */   public void equippedItemPosition()
/*     */   {
/* 150 */     GL11.glTranslatef(0.2F, 0.08F, -0.0875F);
/*     */   }
/*     */   
/*     */   public void equippedHeadItemPosition()
/*     */   {
/* 155 */     GL11.glTranslatef(0.0F, 1.0F, 0.0F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void equippedItemBow()
/*     */   {
/* 161 */     equippedItemPosition3D();
/*     */     
/* 163 */     GL11.glTranslatef(-0.05F, -0.075F, 0.1F);
/*     */   }
/*     */   
/*     */   public boolean isItemHolder()
/*     */   {
/* 168 */     return false;
/*     */   }
/*     */   
/*     */   public void setLivingAnimations(IModelCaps pEntityCaps, float par2, float par3, float pRenderPartialTicks)
/*     */   {
/* 173 */     super.setLivingAnimations(pEntityCaps, par2, par3, pRenderPartialTicks);
/* 174 */     float f3 = ModelCapsHelper.getCapsValueFloat(pEntityCaps, 336, new Object[] { Float.valueOf(pRenderPartialTicks) });
/* 175 */     this.bipedHead.rotateAngleZ = f3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRotationAngles(float par1, float par2, float pTicksExisted, float pHeadYaw, float pHeadPitch, float par6, IModelCaps pEntityCaps)
/*     */   {
/* 184 */     this.bipedHead.rotateAngleY = (pHeadYaw / 57.29578F);
/* 185 */     this.bipedHead.rotateAngleX = (pHeadPitch / 57.29578F);
/*     */     
/* 187 */     this.bipedHeadwear.rotateAngleX = 0.0F;
/* 188 */     this.bipedRightArm.rotateAngleX = (mh_cos(par1 * 0.6662F + 3.141593F) * 2.0F * par2 * 0.5F);
/* 189 */     this.bipedLeftArm.rotateAngleX = (mh_cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
/* 190 */     this.bipedRightArm.rotateAngleZ = 0.0F;
/* 191 */     this.bipedLeftArm.rotateAngleZ = 0.0F;
/* 192 */     this.bipedRightLeg.rotateAngleX = (mh_cos(par1 * 0.6662F) * 1.4F * par2);
/* 193 */     this.bipedLeftLeg.rotateAngleX = (mh_cos(par1 * 0.6662F + 3.141593F) * 1.4F * par2);
/* 194 */     this.bipedRightLeg.rotateAngleY = 0.0F;
/* 195 */     this.bipedLeftLeg.rotateAngleY = 0.0F;
/*     */     
/* 197 */     if (this.isRiding)
/*     */     {
/* 199 */       this.bipedRightArm.rotateAngleX += -0.6283185F;
/* 200 */       this.bipedLeftArm.rotateAngleX += -0.6283185F;
/* 201 */       this.bipedRightLeg.rotateAngleX = -1.256637F;
/* 202 */       this.bipedLeftLeg.rotateAngleX = -1.256637F;
/* 203 */       this.bipedRightLeg.rotateAngleY = 0.3141593F;
/* 204 */       this.bipedLeftLeg.rotateAngleY = -0.3141593F;
/*     */     }
/*     */     
/* 207 */     if (this.heldItem[1] != 0.0F) {
/* 208 */       this.bipedLeftArm.rotateAngleX = (this.bipedLeftArm.rotateAngleX * 0.5F - 0.3141593F * this.heldItem[1]);
/*     */     }
/*     */     
/* 211 */     if (this.heldItem[0] != 0.0F) {
/* 212 */       this.bipedRightArm.rotateAngleX = (this.bipedRightArm.rotateAngleX * 0.5F - 0.3141593F * this.heldItem[0]);
/*     */     }
/*     */     
/*     */ 
/* 216 */     this.bipedRightArm.rotateAngleY = 0.0F;
/* 217 */     this.bipedLeftArm.rotateAngleY = 0.0F;
/* 218 */     float lonGround = this.onGrounds[this.dominantArm];
/* 219 */     if ((lonGround > -9990.0F) && (!this.aimedBow))
/*     */     {
/* 221 */       float f6 = lonGround;
/* 222 */       this.bipedBody.rotateAngleY = (mh_sin(mh_sqrt_float(f6) * 3.141593F * 2.0F) * 0.2F);
/* 223 */       this.Skirt.rotateAngleY = this.bipedBody.rotateAngleY;
/* 224 */       this.bipedRightArm.rotationPointZ = (mh_sin(this.bipedBody.rotateAngleY) * 4.0F);
/* 225 */       this.bipedRightArm.rotationPointX = (-mh_cos(this.bipedBody.rotateAngleY) * 4.0F + 1.0F);
/* 226 */       this.bipedLeftArm.rotationPointZ = (-mh_sin(this.bipedBody.rotateAngleY) * 4.0F);
/* 227 */       this.bipedLeftArm.rotationPointX = (mh_cos(this.bipedBody.rotateAngleY) * 4.0F - 1.0F);
/* 228 */       this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
/* 229 */       this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
/* 230 */       this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
/* 231 */       f6 = 1.0F - lonGround;
/* 232 */       f6 *= f6;
/* 233 */       f6 *= f6;
/* 234 */       f6 = 1.0F - f6;
/* 235 */       float f7 = mh_sin(f6 * 3.141593F);
/* 236 */       float f8 = mh_sin(lonGround * 3.141593F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F; ModelRenderer 
/*     */       
/* 238 */         tmp588_585 = this.bipedRightArm;tmp588_585.rotateAngleX = ((float)(tmp588_585.rotateAngleX - (f7 * 1.2D + f8)));
/* 239 */       this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
/* 240 */       this.bipedRightArm.rotateAngleZ = (mh_sin(lonGround * 3.141593F) * -0.4F);
/*     */     }
/* 242 */     if (this.isSneak)
/*     */     {
/* 244 */       this.bipedBody.rotateAngleX = 0.5F;
/* 245 */       this.bipedRightLeg.rotateAngleX -= 0.0F;
/* 246 */       this.bipedLeftLeg.rotateAngleX -= 0.0F;
/* 247 */       this.bipedRightArm.rotateAngleX += 0.4F;
/* 248 */       this.bipedLeftArm.rotateAngleX += 0.4F;
/* 249 */       this.bipedRightLeg.rotationPointZ = 3.0F;
/* 250 */       this.bipedLeftLeg.rotationPointZ = 3.0F;
/* 251 */       this.bipedRightLeg.rotationPointY = 6.0F;
/* 252 */       this.bipedLeftLeg.rotationPointY = 6.0F;
/* 253 */       this.bipedHead.rotationPointY = 1.0F;
/* 254 */       this.bipedHeadwear.rotationPointY = 1.0F;
/* 255 */       this.bipedHeadwear.rotateAngleX += 0.5F;
/* 256 */       this.Skirt.rotationPointY = 5.8F;
/* 257 */       this.Skirt.rotationPointZ = 2.7F;
/* 258 */       this.Skirt.rotateAngleX = 0.2F;
/*     */     }
/*     */     else {
/* 261 */       this.bipedBody.rotateAngleX = 0.0F;
/* 262 */       this.bipedRightLeg.rotationPointZ = 0.0F;
/* 263 */       this.bipedLeftLeg.rotationPointZ = 0.0F;
/* 264 */       this.bipedRightLeg.rotationPointY = 7.0F;
/* 265 */       this.bipedLeftLeg.rotationPointY = 7.0F;
/* 266 */       this.bipedHead.rotationPointY = 0.0F;
/* 267 */       this.bipedHeadwear.rotationPointY = 0.0F;
/* 268 */       this.Skirt.rotationPointY = 7.0F;
/* 269 */       this.Skirt.rotationPointZ = 0.0F;
/* 270 */       this.Skirt.rotateAngleX = 0.0F;
/*     */     }
/* 272 */     if (this.isWait)
/*     */     {
/* 274 */       this.bipedRightArm.rotateAngleX = (mh_sin(pTicksExisted * 0.067F) * 0.05F - 0.7F);
/* 275 */       this.bipedRightArm.rotateAngleY = 0.0F;
/* 276 */       this.bipedRightArm.rotateAngleZ = -0.4F;
/* 277 */       this.bipedLeftArm.rotateAngleX = (mh_sin(pTicksExisted * 0.067F) * 0.05F - 0.7F);
/* 278 */       this.bipedLeftArm.rotateAngleY = 0.0F;
/* 279 */       this.bipedLeftArm.rotateAngleZ = 0.4F;
/*     */     }
/* 281 */     else if (this.aimedBow)
/*     */     {
/* 283 */       float f6 = mh_sin(lonGround * 3.141593F);
/* 284 */       float f7 = mh_sin((1.0F - (1.0F - lonGround) * (1.0F - lonGround)) * 3.141593F);
/*     */       
/* 286 */       this.bipedRightArm.rotateAngleZ = 0.0F;
/* 287 */       this.bipedLeftArm.rotateAngleZ = 0.0F;
/* 288 */       this.bipedRightArm.rotateAngleY = (-(0.1F - f6 * 0.6F));
/* 289 */       this.bipedLeftArm.rotateAngleY = (0.1F - f6 * 0.6F);
/*     */       
/*     */ 
/* 292 */       this.bipedRightArm.rotateAngleX = -1.470796F;
/* 293 */       this.bipedLeftArm.rotateAngleX = -1.470796F;
/* 294 */       this.bipedRightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
/* 295 */       this.bipedLeftArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
/* 296 */       this.bipedRightArm.rotateAngleZ += mh_cos(pTicksExisted * 0.09F) * 0.05F + 0.05F;
/* 297 */       this.bipedLeftArm.rotateAngleZ -= mh_cos(pTicksExisted * 0.09F) * 0.05F + 0.05F;
/* 298 */       this.bipedRightArm.rotateAngleX += mh_sin(pTicksExisted * 0.067F) * 0.05F;
/* 299 */       this.bipedLeftArm.rotateAngleX -= mh_sin(pTicksExisted * 0.067F) * 0.05F;
/* 300 */       this.bipedRightArm.rotateAngleX += this.bipedHead.rotateAngleX;
/* 301 */       this.bipedLeftArm.rotateAngleX += this.bipedHead.rotateAngleX;
/* 302 */       this.bipedRightArm.rotateAngleY += this.bipedHead.rotateAngleY;
/* 303 */       this.bipedLeftArm.rotateAngleY += this.bipedHead.rotateAngleY;
/*     */     }
/*     */     else {
/* 306 */       this.bipedRightArm.rotateAngleZ += 0.5F;
/* 307 */       this.bipedLeftArm.rotateAngleZ -= 0.5F;
/* 308 */       this.bipedRightArm.rotateAngleZ += mh_cos(pTicksExisted * 0.09F) * 0.05F + 0.05F;
/* 309 */       this.bipedLeftArm.rotateAngleZ -= mh_cos(pTicksExisted * 0.09F) * 0.05F + 0.05F;
/* 310 */       this.bipedRightArm.rotateAngleX += mh_sin(pTicksExisted * 0.067F) * 0.05F;
/* 311 */       this.bipedLeftArm.rotateAngleX -= mh_sin(pTicksExisted * 0.067F) * 0.05F;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void renderItems(IModelCaps pEntityCaps)
/*     */   {
/*     */     
/*     */     
/* 321 */     if (pEntityCaps != null) {
/* 322 */       int ldominant = ModelCapsHelper.getCapsValueInt(pEntityCaps, 293, new Object[0]);
/* 323 */       this.Arms[0].loadMatrix().renderItems(this, pEntityCaps, false, ldominant);
/*     */       
/* 325 */       boolean lplanter = ModelCapsHelper.getCapsValueBoolean(pEntityCaps, 274, new Object[0]);
/* 326 */       if ((ModelCapsHelper.getCapsValueBoolean(pEntityCaps, 273, new Object[0])) || (lplanter)) {
/* 327 */         this.HeadMount.loadMatrix();
/* 328 */         if (lplanter) {
/* 329 */           this.HeadTop.loadMatrix().renderItemsHead(this, pEntityCaps);
/*     */         } else {
/* 331 */           this.HeadMount.loadMatrix().renderItemsHead(this, pEntityCaps);
/*     */         }
/*     */       }
/*     */     }
/* 335 */     GL11.glPopMatrix();
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/model/mc162/ModelLittleMaid_Archetype.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */