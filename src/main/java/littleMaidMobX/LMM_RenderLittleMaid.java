/*     */ package littleMaidMobX;
/*     */ 
/*     */ import java.util.List;
/*     */ import mmmlibx.lib.multiModel.model.mc162.IModelCaps;
/*     */ import mmmlibx.lib.multiModel.model.mc162.ModelBaseSolo;
/*     */ import mmmlibx.lib.multiModel.model.mc162.RenderModelMulti;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class LMM_RenderLittleMaid extends RenderModelMulti
/*     */ {
/*     */   public LMM_RenderLittleMaid(float f)
/*     */   {
/*  22 */     super(f);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setModelValues(EntityLivingBase par1EntityLiving, double par2, double par4, double par6, float par8, float par9, IModelCaps pEntityCaps)
/*     */   {
/*  29 */     LMM_EntityLittleMaid lmaid = (LMM_EntityLittleMaid)par1EntityLiving;
/*  30 */     super.setModelValues(par1EntityLiving, par2, par4, par6, par8, par9, pEntityCaps);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  38 */     this.modelMain.setCapsValue(16, new Object[] { Integer.valueOf(0) });
/*  39 */     this.modelMain.setCapsValue(17, new Object[] { Integer.valueOf(0) });
/*     */     
/*  41 */     this.modelMain.setCapsValue(1, new Object[] { Float.valueOf(lmaid.mstatSwingStatus[0].getSwingProgress(par9)), Float.valueOf(lmaid.mstatSwingStatus[1].getSwingProgress(par9)) });
/*     */     
/*     */ 
/*  44 */     this.modelMain.setCapsValue(2, new Object[] { Boolean.valueOf(lmaid.isRiding()) });
/*  45 */     this.modelMain.setCapsValue(19, new Object[] { Boolean.valueOf(lmaid.isSneaking()) });
/*  46 */     this.modelMain.setCapsValue(20, new Object[] { Boolean.valueOf(lmaid.isAimebow()) });
/*  47 */     this.modelMain.setCapsValue(263, new Object[] { Boolean.valueOf(lmaid.isMaidWait()) });
/*  48 */     this.modelMain.setCapsValue(3, new Object[] { Boolean.valueOf(lmaid.isChild()) });
/*  49 */     this.modelMain.setCapsValue(288, new Object[] { Float.valueOf(lmaid.entityIdFactor) });
/*  50 */     this.modelMain.setCapsValue(34, new Object[] { Integer.valueOf(lmaid.ticksExisted) });
/*  51 */     this.modelMain.setCapsValue(293, new Object[] { Integer.valueOf(lmaid.maidDominantArm) });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void renderString(LMM_EntityLittleMaid plittleMaid, double px, double py, double pz, float f, float f1)
/*     */   {
/*  58 */     if ((plittleMaid.mstatgotcha != null) && ((plittleMaid.mstatgotcha instanceof EntityLivingBase))) {
/*  59 */       EntityLivingBase lel = (EntityLivingBase)plittleMaid.mstatgotcha;
/*  60 */       py -= 0.5D;
/*  61 */       Tessellator tessellator = Tessellator.instance;
/*  62 */       float f9 = (lel.prevRotationYaw + (lel.rotationYaw - lel.prevRotationYaw) * f1 * 0.5F) * 3.141593F / 180.0F;
/*  63 */       float f3 = (lel.prevRotationPitch + (lel.rotationPitch - lel.prevRotationPitch) * f1 * 0.5F) * 3.141593F / 180.0F;
/*  64 */       double d3 = MathHelper.sin(f9);
/*  65 */       double d5 = MathHelper.cos(f9);
/*  66 */       float f11 = lel.getSwingProgress(f1);
/*  67 */       float f12 = MathHelper.sin(MathHelper.sqrt_float(f11) * 3.141593F);
/*  68 */       Vec3 vec3d = Vec3.createVectorHelper(-0.5D, 0.03D, 0.55D);
/*  69 */       vec3d.rotateAroundX(-(lel.prevRotationPitch + (lel.rotationPitch - lel.prevRotationPitch) * f1) * 3.141593F / 180.0F);
/*  70 */       vec3d.rotateAroundY(-(lel.prevRotationYaw + (lel.rotationYaw - lel.prevRotationYaw) * f1) * 3.141593F / 180.0F);
/*  71 */       vec3d.rotateAroundY(f12 * 0.5F);
/*  72 */       vec3d.rotateAroundX(-f12 * 0.7F);
/*  73 */       double d7 = lel.prevPosX + (lel.posX - lel.prevPosX) * f1 + vec3d.xCoord;
/*  74 */       double d8 = lel.prevPosY + (lel.posY - lel.prevPosY) * f1 + vec3d.yCoord;
/*  75 */       double d9 = lel.prevPosZ + (lel.posZ - lel.prevPosZ) * f1 + vec3d.zCoord;
/*  76 */       if (this.renderManager.options.thirdPersonView > 0) {
/*  77 */         float f4 = (lel.prevRenderYawOffset + (lel.renderYawOffset - lel.prevRenderYawOffset) * f1) * 3.141593F / 180.0F;
/*  78 */         double d11 = MathHelper.sin(f4);
/*  79 */         double d13 = MathHelper.cos(f4);
/*  80 */         d7 = lel.prevPosX + (lel.posX - lel.prevPosX) * f1 - d13 * 0.35D - d11 * 0.5499999999999999D;
/*  81 */         d8 = lel.prevPosY + (lel.posY - lel.prevPosY) * f1 - 0.45D;
/*  82 */         d9 = lel.prevPosZ + (lel.posZ - lel.prevPosZ) * f1 - d11 * 0.35D + d13 * 0.5499999999999999D;
/*     */       }
/*  84 */       double d10 = plittleMaid.prevPosX + (plittleMaid.posX - plittleMaid.prevPosX) * f1;
/*  85 */       double d12 = plittleMaid.prevPosY + (plittleMaid.posY - plittleMaid.prevPosY) * f1 + 0.25D - 0.5D;
/*  86 */       double d14 = plittleMaid.prevPosZ + (plittleMaid.posZ - plittleMaid.prevPosZ) * f1;
/*  87 */       double d15 = (float)(d7 - d10);
/*  88 */       double d16 = (float)(d8 - d12);
/*  89 */       double d17 = (float)(d9 - d14);
/*  90 */       GL11.glDisable(3553);
/*  91 */       GL11.glDisable(2896);
/*  92 */       tessellator.startDrawing(3);
/*  93 */       tessellator.setColorOpaque_I(0);
/*  94 */       int i = 16;
/*  95 */       for (int j = 0; j <= i; j++)
/*     */       {
/*  97 */         float f5 = j / i;
/*  98 */         tessellator.addVertex(px + d15 * f5, py + d16 * (f5 * f5 + f5) * 0.5D + ((i - j) / (i * 0.75F) + 0.125F), pz + d17 * f5);
/*     */       }
/*     */       
/* 101 */       tessellator.draw();
/* 102 */       GL11.glEnable(2896);
/* 103 */       GL11.glEnable(3553);
/*     */     }
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 144 */     LMM_EntityLittleMaid lmm = (LMM_EntityLittleMaid)par1EntityLiving;
/*     */     
/* 146 */     this.fcaps = lmm.maidCaps;
/*     */     
/* 148 */     renderModelMulti(lmm, par2, par4, par6, par8, par9, this.fcaps);
/* 149 */     renderString(lmm, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void renderModel(EntityLivingBase par1EntityLiving, float par2, float par3, float par4, float par5, float par6, float par7)
/*     */   {
/* 157 */     if (!par1EntityLiving.isInvisible()) {
/* 158 */       this.modelMain.setArmorRendering(true);
/*     */     } else {
/* 160 */       this.modelMain.setArmorRendering(false);
/*     */     }
/*     */     
/* 163 */     this.mainModel.render(par1EntityLiving, par2, par3, par4, par5, par6, par7);
/*     */   }
/*     */   
/*     */   protected void passSpecialRender(EntityLivingBase par1EntityLiving, double par2, double par4, double par6)
/*     */   {
/* 168 */     super.passSpecialRender(par1EntityLiving, par2, par4, par6);
/*     */     
/* 170 */     LMM_EntityLittleMaid llmm = (LMM_EntityLittleMaid)par1EntityLiving;
/*     */     
/* 172 */     for (int li = 0; li < llmm.maidEntityModeList.size(); li++) {
/* 173 */       ((LMM_EntityModeBase)llmm.maidEntityModeList.get(li)).showSpecial(this, par2, par4, par6);
/*     */     }
/*     */   }
/*     */   
/*     */   protected int getColorMultiplier(EntityLivingBase par1EntityLiving, float par2, float par3)
/*     */   {
/* 179 */     return ((LMM_EntityLittleMaid)par1EntityLiving).colorMultiplier(par2, par3);
/*     */   }
/*     */   
/*     */   public void renderLivingLabel(Entity p_147906_1_, String p_147906_2_, double p_147906_3_, double p_147906_5_, double p_147906_7_, int p_147906_9_)
/*     */   {
/* 184 */     super.func_147906_a(p_147906_1_, p_147906_2_, p_147906_3_, p_147906_5_, p_147906_7_, p_147906_9_);
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/littleMaidMobX/LMM_RenderLittleMaid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */