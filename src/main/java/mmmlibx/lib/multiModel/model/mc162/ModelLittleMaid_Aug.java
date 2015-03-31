/*     */ package mmmlibx.lib.multiModel.model.mc162;
/*     */ 
/*     */ 
/*     */ public class ModelLittleMaid_Aug
/*     */   extends ModelLittleMaid_SR2
/*     */ {
/*     */   public ModelRenderer shaggyB;
/*     */   
/*     */   public ModelRenderer shaggyR;
/*     */   
/*     */   public ModelRenderer shaggyL;
/*     */   
/*     */   public ModelRenderer SideTailR;
/*     */   
/*     */   public ModelRenderer SideTailL;
/*     */   
/*     */   public ModelRenderer sensor1;
/*     */   
/*     */   public ModelRenderer sensor2;
/*     */   public ModelRenderer sensor3;
/*     */   public ModelRenderer sensor4;
/*     */   
/*     */   public ModelLittleMaid_Aug() {}
/*     */   
/*     */   public ModelLittleMaid_Aug(float psize)
/*     */   {
/*  27 */     super(psize);
/*     */   }
/*     */   
/*  30 */   public ModelLittleMaid_Aug(float psize, float pyoffset, int pTextureWidth, int pTextureHeight) { super(psize, pyoffset, pTextureWidth, pTextureHeight); }
/*     */   
/*     */ 
/*     */ 
/*     */   public void initModel(float psize, float pyoffset)
/*     */   {
/*  36 */     super.initModel(psize, pyoffset);
/*     */     
/*     */ 
/*  39 */     this.SideTailR = new ModelRenderer(this);
/*  40 */     this.SideTailR.setTextureOffset(46, 20).addParts(ModelBox.class, new Object[] { Float.valueOf(-1.5F), Float.valueOf(-0.5F), Float.valueOf(-1.0F), Integer.valueOf(2), Integer.valueOf(10), Integer.valueOf(2), Float.valueOf(psize) });
/*  41 */     this.SideTailR.setRotationPoint(-5.0F, -7.8F, 1.9F);
/*  42 */     this.SideTailL = new ModelRenderer(this);
/*  43 */     this.SideTailL.setTextureOffset(54, 20).addParts(ModelBox.class, new Object[] { Float.valueOf(0.5F), Float.valueOf(-0.5F), Float.valueOf(-1.0F), Integer.valueOf(2), Integer.valueOf(10), Integer.valueOf(2), Float.valueOf(psize) });
/*  44 */     this.SideTailL.setRotationPoint(4.0F, -7.8F, 1.9F);
/*     */     
/*     */ 
/*     */ 
/*  48 */     this.shaggyB = new ModelRenderer(this, 24, 0);
/*  49 */     this.shaggyB.addParts(ModelPlate.class, new Object[] { Float.valueOf(-5.0F), Float.valueOf(0.0F), Float.valueOf(0.0F), Integer.valueOf(10), Integer.valueOf(4), Integer.valueOf(4), Float.valueOf(psize) });
/*  50 */     this.shaggyB.setRotationPoint(0.0F, -1.0F, 4.0F);
/*  51 */     this.shaggyB.setRotateAngleX(0.4F);
/*  52 */     this.shaggyR = new ModelRenderer(this, 34, 4);
/*  53 */     this.shaggyR.addParts(ModelPlate.class, new Object[] { Float.valueOf(0.0F), Float.valueOf(0.0F), Float.valueOf(-5.0F), Integer.valueOf(10), Integer.valueOf(4), Integer.valueOf(1), Float.valueOf(psize) });
/*  54 */     this.shaggyR.setRotationPoint(4.0F, -1.0F, 0.0F);
/*  55 */     this.shaggyR.setRotateAngleZ(-0.4F);
/*  56 */     this.shaggyL = new ModelRenderer(this, 24, 4);
/*  57 */     this.shaggyL.addParts(ModelPlate.class, new Object[] { Float.valueOf(0.0F), Float.valueOf(0.0F), Float.valueOf(-5.0F), Integer.valueOf(10), Integer.valueOf(4), Integer.valueOf(5), Float.valueOf(psize) });
/*  58 */     this.shaggyL.setRotationPoint(-4.0F, -1.0F, 0.0F);
/*  59 */     this.shaggyL.setRotateAngleZ(0.4F);
/*     */     
/*  61 */     this.sensor1 = new ModelRenderer(this, 0, 0);
/*  62 */     this.sensor1.addParts(ModelPlate.class, new Object[] { Float.valueOf(-8.0F), Float.valueOf(-4.0F), Float.valueOf(0.0F), Integer.valueOf(8), Integer.valueOf(4), Integer.valueOf(0) });
/*  63 */     this.sensor1.setRotationPoint(0.0F, -8.0F + pyoffset, 0.0F);
/*  64 */     this.sensor2 = new ModelRenderer(this, 0, 4);
/*  65 */     this.sensor2.addParts(ModelPlate.class, new Object[] { Float.valueOf(0.0F), Float.valueOf(-4.0F), Float.valueOf(0.0F), Integer.valueOf(8), Integer.valueOf(4), Integer.valueOf(0) });
/*  66 */     this.sensor2.setRotationPoint(0.0F, -8.0F + pyoffset, 0.0F);
/*  67 */     this.sensor3 = new ModelRenderer(this, 44, 0);
/*  68 */     this.sensor3.addParts(ModelPlate.class, new Object[] { Float.valueOf(0.0F), Float.valueOf(-7.0F), Float.valueOf(-4.0F), Integer.valueOf(4), Integer.valueOf(8), Integer.valueOf(1) });
/*  69 */     this.sensor3.setRotationPoint(0.0F, -8.0F + pyoffset, 0.0F);
/*  70 */     this.sensor4 = new ModelRenderer(this, 34, 0);
/*  71 */     this.sensor4.addParts(ModelPlate.class, new Object[] { Float.valueOf(0.0F), Float.valueOf(-4.0F), Float.valueOf(-10.0F), Integer.valueOf(10), Integer.valueOf(4), Integer.valueOf(1) });
/*  72 */     this.sensor4.setRotationPoint(0.0F, -8.0F + pyoffset, 0.0F);
/*     */     
/*     */ 
/*     */ 
/*  76 */     this.bipedHead.clearCubeList();
/*  77 */     this.bipedHead.setMirror(false);
/*  78 */     this.bipedHead.setTextureOffset(0, 0).addParts(ModelBox.class, new Object[] { Float.valueOf(-4.0F), Float.valueOf(-8.0F), Float.valueOf(-4.0F), Integer.valueOf(8), Integer.valueOf(8), Integer.valueOf(8), Float.valueOf(psize) });
/*  79 */     this.bipedHead.setTextureOffset(0, 18).addParts(ModelBox.class, new Object[] { Float.valueOf(-5.0F), Float.valueOf(-8.5F), Float.valueOf(0.2F), Integer.valueOf(1), Integer.valueOf(3), Integer.valueOf(3), Float.valueOf(psize) });
/*  80 */     this.bipedHead.setTextureOffset(24, 18).addParts(ModelBox.class, new Object[] { Float.valueOf(4.0F), Float.valueOf(-8.5F), Float.valueOf(0.2F), Integer.valueOf(1), Integer.valueOf(3), Integer.valueOf(3), Float.valueOf(psize) });
/*  81 */     this.bipedHead.setTextureOffset(52, 10).addParts(ModelBox.class, new Object[] { Float.valueOf(-7.5F), Float.valueOf(-9.5F), Float.valueOf(0.9F), Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(2), Float.valueOf(psize) });
/*  82 */     this.bipedHead.setTextureOffset(52, 15).addParts(ModelBox.class, new Object[] { Float.valueOf(3.5F), Float.valueOf(-9.5F), Float.valueOf(0.9F), Integer.valueOf(4), Integer.valueOf(3), Integer.valueOf(2), Float.valueOf(psize) });
/*  83 */     this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
/*     */     
/*  85 */     this.bipedHead.addChild(this.HeadMount);
/*  86 */     this.bipedHead.addChild(this.HeadTop);
/*  87 */     this.bipedHead.addChild(this.SideTailR);
/*  88 */     this.bipedHead.addChild(this.SideTailL);
/*  89 */     this.bipedHead.addChild(this.shaggyB);
/*  90 */     this.bipedHead.addChild(this.shaggyR);
/*  91 */     this.bipedHead.addChild(this.shaggyL);
/*  92 */     this.bipedHead.addChild(this.sensor1);
/*  93 */     this.bipedHead.addChild(this.sensor2);
/*  94 */     this.bipedHead.addChild(this.sensor3);
/*  95 */     this.bipedHead.addChild(this.sensor4);
/*  96 */     this.bipedHead.addChild(this.eyeR);
/*  97 */     this.bipedHead.addChild(this.eyeL);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setLivingAnimations(IModelCaps pEntityCaps, float par2, float par3, float pRenderPartialTicks)
/*     */   {
/* 103 */     super.setLivingAnimations(pEntityCaps, par2, par3, pRenderPartialTicks);
/*     */     
/* 105 */     float f3 = this.entityTicksExisted + pRenderPartialTicks + this.entityIdFactor;
/*     */     float f4;
/* 107 */     float f4; if (ModelCapsHelper.getCapsValueBoolean(pEntityCaps, 261, new Object[0])) {
/* 108 */       f3 *= 8.0F;
/* 109 */       f4 = -0.2F;
/*     */     } else {
/* 111 */       f4 = (1.0F - ModelCapsHelper.getCapsValueInt(pEntityCaps, 33, new Object[0]) / 20.0F) * 0.5F;
/*     */     }
/* 113 */     float f5 = mh_sin(f3 * 0.067F) * 0.05F - f4;
/* 114 */     float f6 = 0.6981317F;
/* 115 */     this.sensor1.setRotateAngle(f5, -f6, f5);
/* 116 */     this.sensor2.setRotateAngle(-f5, f6, -f5);
/* 117 */     this.sensor3.setRotateAngle(mh_sin(f3 * 0.067F) * 0.05F - 1.2F - f4, mh_sin(f3 * 0.09F) * 0.4F, mh_cos(f3 * 0.09F) * 0.2F);
/* 118 */     this.sensor4.setRotateAngle(mh_sin(f3 * 0.067F) * 0.05F + f4, mh_cos(f3 * 0.09F) * 0.5F, mh_sin(f3 * 0.09F) * 0.2F);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setRotationAngles(float par1, float par2, float pTicksExisted, float pHeadYaw, float pHeadPitch, float par6, IModelCaps pEntityCaps)
/*     */   {
/* 124 */     super.setRotationAngles(par1, par2, pTicksExisted, pHeadYaw, pHeadPitch, par6, pEntityCaps);
/*     */     
/* 126 */     this.SideTailR.setRotateAngleX(this.SideTailL.setRotateAngleX(this.bipedHead.getRotateAngleX() * -0.6666667F));
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/model/mc162/ModelLittleMaid_Aug.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */