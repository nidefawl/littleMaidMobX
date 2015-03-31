/*     */ package mmmlibx.lib;
/*     */ 
/*     */ import mmmlibx.lib.multiModel.model.mc162.IModelCaps;
/*     */ import mmmlibx.lib.multiModel.model.mc162.ModelRenderer;
/*     */ 
/*     */ public class MMM_ModelMulti_Stef extends MMM_ModelMulti_Steve
/*     */ {
/*     */   public ModelRenderer bust;
/*     */   
/*     */   public MMM_ModelMulti_Stef() {}
/*     */   
/*     */   public MMM_ModelMulti_Stef(float psize)
/*     */   {
/*  14 */     super(psize);
/*     */   }
/*     */   
/*     */   public MMM_ModelMulti_Stef(float psize, float pyoffset, int pTextureWidth, int pTextureHeight)
/*     */   {
/*  19 */     super(psize, pyoffset, 64, 32);
/*     */   }
/*     */   
/*     */   public void initModel(float psize, float pyoffset)
/*     */   {
/*  24 */     this.bipedCloak = new ModelRenderer(this, 0, 0);
/*  25 */     this.bipedCloak.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, psize);
/*  26 */     this.bipedEars = new ModelRenderer(this, 24, 0);
/*  27 */     this.bipedEars.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, psize);
/*     */     
/*  29 */     this.bipedHead = new ModelRenderer(this, 0, 0);
/*  30 */     this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, psize - 0.2F);
/*  31 */     this.bipedHead.setRotationPoint(0.0F, 1.35F, 0.0F);
/*  32 */     this.bipedHeadwear = new ModelRenderer(this, 32, 0);
/*  33 */     this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, psize + 0.3F);
/*  34 */     this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  35 */     this.eyeL = new ModelRenderer(this, 0, 0);
/*  36 */     this.eyeL.addBox(0.0F, -5.0F, -4.001F, 4, 4, 0, psize - 0.2F);
/*  37 */     this.eyeL.setRotationPoint(0.0F, 0.0F, 0.0F);
/*  38 */     this.eyeR = new ModelRenderer(this, 0, 4);
/*  39 */     this.eyeR.addBox(-4.0F, -5.0F, -4.001F, 4, 4, 0, psize - 0.2F);
/*  40 */     this.eyeR.setRotationPoint(0.0F, 0.0F, 0.0F);
/*     */     
/*  42 */     this.bipedBody = new ModelRenderer(this, 16, 16);
/*  43 */     this.bipedBody.addBox(-4.0F, 0.2F, -2.0F, 8, 12, 4, psize - 0.3F);
/*  44 */     this.bipedBody.setRotationPoint(0.0F, 0.7F, 0.0F);
/*  45 */     this.bust = new ModelRenderer(this, 16, 21);
/*  46 */     this.bust.addBox(-4.0F, -2.0F, -2.0F, 8, 4, 4, psize - 0.3001F);
/*  47 */     this.bust.setRotationPoint(0.0F, 4.25F, -1.4F).setRotateAngleDeg(50.0F, 0.0F, 0.0F);
/*     */     
/*  49 */     this.bipedRightArm = new ModelRenderer(this, 40, 16);
/*  50 */     this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, psize - 0.4F);
/*  51 */     this.bipedRightArm.setRotationPoint(-4.4F, 2.85F, 0.0F);
/*  52 */     this.bipedLeftArm = new ModelRenderer(this, 40, 16);
/*  53 */     this.bipedLeftArm.mirror = true;
/*  54 */     this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, psize - 0.4F);
/*  55 */     this.bipedLeftArm.setRotationPoint(4.4F, 2.85F, 0.0F);
/*     */     
/*  57 */     this.bipedRightLeg = new ModelRenderer(this, 0, 16);
/*  58 */     this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, psize - 0.3F);
/*  59 */     this.bipedRightLeg.setRotationPoint(-1.7F, 12.3F, 0.0F);
/*  60 */     this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
/*  61 */     this.bipedLeftLeg.mirror = true;
/*  62 */     this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, psize - 0.3F);
/*  63 */     this.bipedLeftLeg.setRotationPoint(1.7F, 12.3F, 0.0F);
/*     */     
/*  65 */     this.HeadMount.setRotationPoint(0.0F, -4.0F, 0.0F);
/*  66 */     this.HeadTop.setRotationPoint(0.0F, -12.0F, 0.0F);
/*  67 */     this.Arms[0] = new ModelRenderer(this);
/*  68 */     this.Arms[0].setRotationPoint(-1.5F, 7.2F, -1.0F);
/*  69 */     this.Arms[1] = new ModelRenderer(this);
/*  70 */     this.Arms[1].setRotationPoint(1.5F, 7.2F, -1.0F);
/*     */     
/*  72 */     this.bipedTorso = new ModelRenderer(this);
/*  73 */     this.bipedNeck = new ModelRenderer(this);
/*  74 */     this.bipedPelvic = new ModelRenderer(this);
/*     */     
/*     */ 
/*  77 */     this.mainFrame = new ModelRenderer(this);
/*  78 */     this.mainFrame.setRotationPoint(0.0F, pyoffset, 0.0F);
/*  79 */     this.mainFrame.addChild(this.bipedTorso);
/*  80 */     this.bipedTorso.addChild(this.bipedNeck);
/*  81 */     this.bipedTorso.addChild(this.bipedPelvic);
/*  82 */     this.bipedTorso.addChild(this.bipedBody);
/*  83 */     this.bipedNeck.addChild(this.bipedHead);
/*  84 */     this.bipedHead.addChild(this.bipedHeadwear);
/*  85 */     this.bipedHead.addChild(this.bipedEars);
/*  86 */     this.bipedHead.addChild(this.HeadMount);
/*  87 */     this.bipedHead.addChild(this.HeadTop);
/*  88 */     this.bipedHead.addChild(this.eyeL);
/*  89 */     this.bipedHead.addChild(this.eyeR);
/*  90 */     this.bipedNeck.addChild(this.bipedRightArm);
/*  91 */     this.bipedNeck.addChild(this.bipedLeftArm);
/*  92 */     this.bipedPelvic.addChild(this.bipedRightLeg);
/*  93 */     this.bipedPelvic.addChild(this.bipedLeftLeg);
/*  94 */     this.bipedRightArm.addChild(this.Arms[0]);
/*  95 */     this.bipedLeftArm.addChild(this.Arms[1]);
/*  96 */     this.bipedBody.addChild(this.bust);
/*  97 */     this.bipedBody.addChild(this.bipedCloak);
/*     */     
/*  99 */     this.bipedEars.showModel = false;
/* 100 */     this.bipedCloak.showModel = false;
/*     */   }
/*     */   
/*     */ 
/*     */   public String getUsingTexture()
/*     */   {
/* 106 */     return "Crafter";
/*     */   }
/*     */   
/*     */ 
/*     */   public void setDefaultPause(float par1, float par2, float pTicksExisted, float pHeadYaw, float pHeadPitch, float par6, IModelCaps pEntityCaps)
/*     */   {
/* 112 */     this.bipedBody.setRotationPoint(0.0F, 0.7F, 0.0F).setRotateAngle(0.0F, 0.0F, 0.0F);
/* 113 */     this.bipedHead.setRotationPoint(0.0F, 1.35F, 0.0F).setRotateAngleDeg(pHeadPitch, pHeadYaw, 0.0F);
/* 114 */     this.bipedRightArm.setRotationPoint(-4.4F, 2.85F, 0.0F).setRotateAngleDeg(0.0F, 0.0F, 10.0F);
/* 115 */     this.bipedLeftArm.setRotationPoint(4.4F, 2.85F, 0.0F).setRotateAngleDeg(0.0F, 0.0F, -10.0F);
/* 116 */     this.bipedRightLeg.setRotationPoint(-2.3F, 11.5F, 0.0F).setRotateAngleDeg(0.0F, 0.0F, -2.0F);
/* 117 */     this.bipedLeftLeg.setRotationPoint(2.3F, 11.5F, 0.0F).setRotateAngleDeg(0.0F, 0.0F, 2.0F);
/* 118 */     this.bipedNeck.setRotationPoint(0.0F, 0.0F, 0.0F).setRotateAngle(0.0F, 0.0F, 0.0F);
/* 119 */     this.bust.setRotationPoint(0.0F, 4.25F, -1.4F).setRotateAngleDeg(50.0F, 0.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public int showArmorParts(int parts, int index)
/*     */   {
/* 124 */     if (index == 0) {
/* 125 */       this.bust.isRendering = (parts == 1);
/*     */     } else {
/* 127 */       this.bust.isRendering = (parts == 2);
/*     */     }
/* 129 */     return super.showArmorParts(parts, index);
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_ModelMulti_Stef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */