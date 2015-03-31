/*     */ package mmmlibx.lib.multiModel.model.mc162;
/*     */ 
/*     */ import mmmlibx.lib.Client;
/*     */ import mmmlibx.lib.ITextureEntity;
/*     */ import mmmlibx.lib.MMMLib;
/*     */ import net.minecraft.client.model.ModelBase;
/*     */ import net.minecraft.client.renderer.entity.RenderLiving;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class RenderModelMulti
/*     */   extends RenderLiving
/*     */ {
/*     */   public ModelBaseSolo modelMain;
/*     */   public ModelBaseDuo modelFATT;
/*     */   public IModelCaps fcaps;
/*     */   
/*     */   public RenderModelMulti(float pShadowSize)
/*     */   {
/*  24 */     super(null, pShadowSize);
/*  25 */     this.modelFATT = new ModelBaseDuo(this);
/*  26 */     this.modelFATT.isModelAlphablend = MMMLib.cfg_isModelAlphaBlend;
/*  27 */     this.modelFATT.isRendering = true;
/*  28 */     this.modelMain = new ModelBaseSolo(this);
/*  29 */     this.modelMain.isModelAlphablend = MMMLib.cfg_isModelAlphaBlend;
/*  30 */     this.modelMain.capsLink = this.modelFATT;
/*  31 */     this.mainModel = this.modelMain;
/*  32 */     setRenderPassModel(this.modelFATT);
/*     */   }
/*     */   
/*     */   protected int showArmorParts(EntityLivingBase par1EntityLiving, int par2, float par3)
/*     */   {
/*  37 */     this.modelFATT.renderParts = par2;
/*  38 */     this.modelFATT.renderCount = 0;
/*  39 */     ItemStack is = par1EntityLiving.getEquipmentInSlot(par2 + 1);
/*  40 */     if ((is != null) && (is.stackSize > 0)) {
/*  41 */       this.modelFATT.showArmorParts(par2);
/*  42 */       return is.isItemEnchanted() ? 15 : 1;
/*     */     }
/*     */     
/*  45 */     return -1;
/*     */   }
/*     */   
/*     */   protected int shouldRenderPass(EntityLivingBase par1EntityLiving, int par2, float par3) {
/*  49 */     return showArmorParts(par1EntityLiving, par2, par3);
/*     */   }
/*     */   
/*     */   protected void preRenderCallback(EntityLivingBase entityliving, float f)
/*     */   {
/*  54 */     Float lscale = (Float)this.modelMain.getCapsValue(512, new Object[0]);
/*  55 */     if (lscale != null) {
/*  56 */       GL11.glScalef(lscale.floatValue(), lscale.floatValue(), lscale.floatValue());
/*     */     }
/*     */   }
/*     */   
/*     */   public void setModelValues(EntityLivingBase par1EntityLiving, double par2, double par4, double par6, float par8, float par9, IModelCaps pEntityCaps)
/*     */   {
/*  62 */     if ((par1EntityLiving instanceof ITextureEntity)) {
/*  63 */       ITextureEntity ltentity = (ITextureEntity)par1EntityLiving;
/*  64 */       this.modelMain.model = ltentity.getTextureData().textureModel[0];
/*  65 */       this.modelFATT.modelInner = ltentity.getTextureData().textureModel[1];
/*  66 */       this.modelFATT.modelOuter = ltentity.getTextureData().textureModel[2];
/*     */       
/*  68 */       this.modelMain.textures = ltentity.getTextures(0);
/*     */       
/*     */ 
/*  71 */       this.modelFATT.textureInner = ltentity.getTextures(1);
/*  72 */       this.modelFATT.textureOuter = ltentity.getTextures(2);
/*  73 */       this.modelFATT.textureInnerLight = ltentity.getTextures(3);
/*  74 */       this.modelFATT.textureOuterLight = ltentity.getTextures(4);
/*  75 */       this.modelFATT.textureLightColor = ((float[])this.modelFATT.getCapsValue(788, new Object[] { pEntityCaps }));
/*     */     }
/*  77 */     this.modelMain.setEntityCaps(pEntityCaps);
/*  78 */     this.modelFATT.setEntityCaps(pEntityCaps);
/*  79 */     this.modelMain.setRender(this);
/*  80 */     this.modelFATT.setRender(this);
/*  81 */     this.modelMain.showAllParts();
/*  82 */     this.modelFATT.showAllParts();
/*  83 */     this.modelMain.isAlphablend = true;
/*  84 */     this.modelFATT.isAlphablend = true;
/*  85 */     this.modelMain.renderCount = 0;
/*  86 */     this.modelFATT.renderCount = 0;
/*  87 */     this.modelMain.lighting = (this.modelFATT.lighting = par1EntityLiving.getBrightnessForRender(par8));
/*     */     
/*  89 */     this.modelMain.setCapsValue(16, new Object[] { Integer.valueOf(0) });
/*  90 */     this.modelMain.setCapsValue(17, new Object[] { Integer.valueOf(0) });
/*  91 */     this.modelMain.setCapsValue(1, new Object[] { Float.valueOf(renderSwingProgress(par1EntityLiving, par9)) });
/*  92 */     this.modelMain.setCapsValue(2, new Object[] { Boolean.valueOf(par1EntityLiving.isRiding()) });
/*  93 */     this.modelMain.setCapsValue(19, new Object[] { Boolean.valueOf(par1EntityLiving.isSneaking()) });
/*  94 */     this.modelMain.setCapsValue(20, new Object[] { Boolean.valueOf(false) });
/*  95 */     this.modelMain.setCapsValue(263, new Object[] { Boolean.valueOf(false) });
/*  96 */     this.modelMain.setCapsValue(3, new Object[] { Boolean.valueOf(par1EntityLiving.isChild()) });
/*  97 */     this.modelMain.setCapsValue(288, new Object[] { Float.valueOf(0.0F) });
/*  98 */     this.modelMain.setCapsValue(34, new Object[] { Integer.valueOf(par1EntityLiving.ticksExisted) });
/*     */   }
/*     */   
/*     */ 
/*     */   public void renderModelMulti(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9, IModelCaps pEntityCaps)
/*     */   {
/* 104 */     setModelValues(par1EntityLiving, par2, par4, par6, par8, par9, pEntityCaps);
/*     */     
/*     */ 
/* 107 */     super.doRender(par1EntityLiving, par2, par4, par6, par8, par9);
/*     */   }
/*     */   
/*     */ 
/*     */   public void doRender(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 113 */     this.fcaps = ((IModelCaps)par1EntityLiving);
/* 114 */     renderModelMulti(par1EntityLiving, par2, par4, par6, par8, par9, this.fcaps);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void func_110827_b(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
/*     */   {
/* 122 */     float lf = 0.0F;
/* 123 */     if ((this.modelMain.model != null) && (this.fcaps != null)) {
/* 124 */       lf = this.modelMain.model.getLeashOffset(this.fcaps);
/*     */     }
/* 126 */     super.func_110827_b(par1EntityLiving, par2, par4 - lf, par6, par8, par9);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void renderModel(EntityLivingBase par1EntityLiving, float par2, float par3, float par4, float par5, float par6, float par7)
/*     */   {
/* 132 */     if (!par1EntityLiving.isInvisible()) {
/* 133 */       this.modelMain.setArmorRendering(true);
/*     */     } else {
/* 135 */       this.modelMain.setArmorRendering(false);
/*     */     }
/*     */     
/* 138 */     this.mainModel.render(par1EntityLiving, par2, par3, par4, par5, par6, par7);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void renderEquippedItems(EntityLivingBase par1EntityLiving, float par2)
/*     */   {
/* 144 */     this.modelMain.renderItems(par1EntityLiving, this);
/* 145 */     renderArrowsStuckInEntity(par1EntityLiving, par2);
/*     */   }
/*     */   
/*     */   protected void renderArrowsStuckInEntity(EntityLivingBase par1EntityLiving, float par2)
/*     */   {
/* 150 */     Client.renderArrowsStuckInEntity(par1EntityLiving, par2, this, this.modelMain.model);
/*     */   }
/*     */   
/*     */ 
/*     */   protected ResourceLocation getEntityTexture(Entity var1)
/*     */   {
/* 156 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   protected int getColorMultiplier(EntityLivingBase par1EntityLivingBase, float par2, float par3)
/*     */   {
/* 162 */     this.modelMain.renderCount = 16;
/* 163 */     return super.getColorMultiplier(par1EntityLivingBase, par2, par3);
/*     */   }
/*     */   
/*     */ 
/*     */   protected int inheritRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
/*     */   {
/* 169 */     int li = super.inheritRenderPass(par1EntityLivingBase, par2, par3);
/* 170 */     this.modelFATT.renderCount = 16;
/* 171 */     return li;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/model/mc162/RenderModelMulti.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */