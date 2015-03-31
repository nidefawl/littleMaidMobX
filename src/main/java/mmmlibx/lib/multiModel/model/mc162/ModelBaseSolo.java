/*     */ package mmmlibx.lib.multiModel.model.mc162;
/*     */ 
/*     */ import java.util.Map;
/*     */ import mmmlibx.lib.Client;
/*     */ import net.minecraft.client.model.TextureOffset;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.client.renderer.entity.RendererLivingEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class ModelBaseSolo
/*     */   extends ModelBaseNihil
/*     */   implements IModelBaseMMM
/*     */ {
/*     */   public ModelMultiBase model;
/*     */   public ResourceLocation[] textures;
/*  19 */   public static final ResourceLocation[] blanks = new ResourceLocation[0];
/*     */   
/*     */   public ModelBaseSolo(RendererLivingEntity pRender)
/*     */   {
/*  23 */     this.rendererLivingEntity = pRender;
/*     */   }
/*     */   
/*     */   public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4)
/*     */   {
/*  28 */     if (this.model != null) {
/*     */       try
/*     */       {
/*  31 */         this.model.setLivingAnimations(this.entityCaps, par2, par3, par4);
/*     */       }
/*     */       catch (Exception e)
/*     */       {
/*  35 */         e.printStackTrace();
/*     */       }
/*     */     }
/*  38 */     this.isAlphablend = true;
/*     */   }
/*     */   
/*     */   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/*     */   {
/*  43 */     if (this.model == null) {
/*  44 */       this.isAlphablend = false;
/*  45 */       return;
/*     */     }
/*  47 */     if (this.isAlphablend) {
/*  48 */       if (this.isModelAlphablend) {
/*  49 */         GL11.glEnable(3042);
/*  50 */         GL11.glBlendFunc(770, 771);
/*     */       } else {
/*  52 */         GL11.glDisable(3042);
/*     */       }
/*     */     }
/*  55 */     if ((this.textures.length > 2) && (this.textures[2] != null))
/*     */     {
/*  57 */       this.model.setRotationAngles(par2, par3, par4, par5, par6, par7, this.entityCaps);
/*     */       
/*     */ 
/*  60 */       Client.setTexture(this.textures[2]);
/*  61 */       this.model.setCapsValue(784, new Object[] { this.entityCaps, Float.valueOf(par2), Float.valueOf(par3), Float.valueOf(par4), Float.valueOf(par5), Float.valueOf(par6), Float.valueOf(par7), Boolean.valueOf(this.isRendering) });
/*     */       
/*  63 */       Client.setTexture(this.textures[0]);
/*  64 */       this.model.setCapsValue(785, new Object[] { this.entityCaps, Float.valueOf(par2), Float.valueOf(par3), Float.valueOf(par4), Float.valueOf(par5), Float.valueOf(par6), Float.valueOf(par7), Boolean.valueOf(this.isRendering) });
/*     */     }
/*     */     else {
/*  67 */       if ((this.textures.length > 0) && (this.textures[0] != null)) {
/*  68 */         Client.setTexture(this.textures[0]);
/*     */       }
/*  70 */       this.model.render(this.entityCaps, par2, par3, par4, par5, par6, par7, this.isRendering);
/*     */     }
/*  72 */     this.isAlphablend = false;
/*  73 */     if ((this.textures.length > 1) && (this.textures[1] != null) && (this.renderCount == 0))
/*     */     {
/*  75 */       Client.setTexture(this.textures[1]);
/*  76 */       float var4 = 1.0F;
/*  77 */       GL11.glEnable(3042);
/*  78 */       GL11.glEnable(3008);
/*  79 */       GL11.glBlendFunc(1, 1);
/*  80 */       GL11.glDepthFunc(515);
/*     */       
/*  82 */       Client.setLightmapTextureCoords(15728880);
/*  83 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, var4);
/*  84 */       this.model.render(this.entityCaps, par2, par3, par4, par5, par6, par7, true);
/*     */       
/*  86 */       Client.setLightmapTextureCoords(this.lighting);
/*     */       
/*     */ 
/*  89 */       GL11.glDisable(3042);
/*  90 */       GL11.glEnable(3008);
/*  91 */       GL11.glDepthMask(true);
/*     */     }
/*     */     
/*  94 */     this.renderCount += 1;
/*     */   }
/*     */   
/*     */   public TextureOffset getTextureOffset(String par1Str)
/*     */   {
/*  99 */     return this.model == null ? null : this.model.getTextureOffset(par1Str);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
/*     */   {
/* 105 */     if (this.model != null) {
/* 106 */       this.model.setRotationAngles(par1, par2, par3, par4, par5, par6, this.entityCaps);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renderItems(EntityLivingBase pEntity, Render pRender)
/*     */   {
/* 115 */     if (this.model != null) {
/* 116 */       this.model.renderItems(this.entityCaps);
/*     */     }
/*     */   }
/*     */   
/*     */   public void showArmorParts(int pParts)
/*     */   {
/* 122 */     if (this.model != null) {
/* 123 */       this.model.showArmorParts(pParts, 0);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEntityCaps(IModelCaps pEntityCaps)
/*     */   {
/* 133 */     this.entityCaps = pEntityCaps;
/* 134 */     if (this.capsLink != null) {
/* 135 */       this.capsLink.setEntityCaps(pEntityCaps);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRender(Render pRender)
/*     */   {
/* 141 */     if (this.model != null) {
/* 142 */       this.model.render = pRender;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setArmorRendering(boolean pFlag)
/*     */   {
/* 148 */     this.isRendering = pFlag;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map<String, Integer> getModelCaps()
/*     */   {
/* 156 */     return this.model == null ? null : this.model.getModelCaps();
/*     */   }
/*     */   
/*     */   public Object getCapsValue(int pIndex, Object... pArg)
/*     */   {
/* 161 */     return this.model == null ? null : this.model.getCapsValue(pIndex, pArg);
/*     */   }
/*     */   
/*     */   public boolean setCapsValue(int pIndex, Object... pArg)
/*     */   {
/* 166 */     if (this.capsLink != null) {
/* 167 */       this.capsLink.setCapsValue(pIndex, pArg);
/*     */     }
/* 169 */     if (this.model != null) {
/* 170 */       this.model.setCapsValue(pIndex, pArg);
/*     */     }
/* 172 */     return false;
/*     */   }
/*     */   
/*     */   public void showAllParts()
/*     */   {
/* 177 */     if (this.model != null) {
/* 178 */       this.model.showAllParts();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/model/mc162/ModelBaseSolo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */