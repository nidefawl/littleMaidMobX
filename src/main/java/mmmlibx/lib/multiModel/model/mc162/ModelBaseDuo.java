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
/*     */ public class ModelBaseDuo
/*     */   extends ModelBaseNihil
/*     */   implements IModelBaseMMM
/*     */ {
/*     */   public ModelMultiBase modelOuter;
/*     */   public ModelMultiBase modelInner;
/*     */   public ResourceLocation[] textureOuter;
/*     */   public ResourceLocation[] textureInner;
/*     */   public ResourceLocation[] textureOuterLight;
/*     */   public ResourceLocation[] textureInnerLight;
/*     */   public int renderParts;
/*     */   public float[] textureLightColor;
/*     */   
/*     */   public ModelBaseDuo(RendererLivingEntity pRender)
/*     */   {
/*  53 */     this.rendererLivingEntity = pRender;
/*  54 */     this.renderParts = 0;
/*     */   }
/*     */   
/*     */   public void setLivingAnimations(EntityLivingBase par1EntityLiving, float par2, float par3, float par4)
/*     */   {
/*  59 */     if (this.modelInner != null) {
/*  60 */       this.modelInner.setLivingAnimations(this.entityCaps, par2, par3, par4);
/*     */     }
/*  62 */     if (this.modelOuter != null) {
/*  63 */       this.modelOuter.setLivingAnimations(this.entityCaps, par2, par3, par4);
/*     */     }
/*  65 */     this.isAlphablend = true;
/*     */   }
/*     */   
/*     */   public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7)
/*     */   {
/*  70 */     boolean lri = (this.renderCount & 0xF) == 0;
/*  71 */     if (this.modelInner != null) {
/*  72 */       if ((this.textureInner != null) && (lri)) {
/*  73 */         if (this.textureInner[this.renderParts] != null)
/*     */         {
/*  75 */           Client.setTexture(this.textureInner[this.renderParts]);
/*  76 */           this.modelInner.render(this.entityCaps, par2, par3, par4, par5, par6, par7, this.isRendering);
/*     */         }
/*     */       }
/*     */       else {
/*  80 */         this.modelInner.render(this.entityCaps, par2, par3, par4, par5, par6, par7, this.isRendering);
/*     */       }
/*  82 */       if ((this.textureInnerLight != null) && (this.renderCount == 0))
/*     */       {
/*  84 */         if (this.textureInnerLight[this.renderParts] != null) {
/*  85 */           Client.setTexture(this.textureInnerLight[this.renderParts]);
/*  86 */           GL11.glEnable(3042);
/*  87 */           GL11.glEnable(3008);
/*  88 */           GL11.glBlendFunc(1, 1);
/*  89 */           GL11.glDepthFunc(515);
/*     */           
/*  91 */           Client.setLightmapTextureCoords(15728880);
/*  92 */           if (this.textureLightColor == null) {
/*  93 */             GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */           }
/*     */           else {
/*  96 */             GL11.glColor4f(this.textureLightColor[0], this.textureLightColor[1], this.textureLightColor[2], this.textureLightColor[3]);
/*     */           }
/*     */           
/*     */ 
/*     */ 
/*     */ 
/* 102 */           this.modelInner.render(this.entityCaps, par2, par3, par4, par5, par6, par7, this.isRendering);
/* 103 */           Client.setLightmapTextureCoords(this.lighting);
/* 104 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 105 */           GL11.glDisable(3042);
/* 106 */           GL11.glEnable(3008);
/*     */         }
/*     */       }
/*     */     }
/* 110 */     if (this.modelOuter != null) {
/* 111 */       if ((this.textureOuter != null) && (lri))
/*     */       {
/* 113 */         if (this.textureOuter[this.renderParts] != null) {
/* 114 */           Client.setTexture(this.textureOuter[this.renderParts]);
/* 115 */           this.modelOuter.render(this.entityCaps, par2, par3, par4, par5, par6, par7, this.isRendering);
/*     */         }
/*     */       }
/*     */       else {
/* 119 */         this.modelOuter.render(this.entityCaps, par2, par3, par4, par5, par6, par7, this.isRendering);
/*     */       }
/* 121 */       if ((this.textureOuterLight != null) && (this.renderCount == 0))
/*     */       {
/* 123 */         if (this.textureOuterLight[this.renderParts] != null) {
/* 124 */           Client.setTexture(this.textureOuterLight[this.renderParts]);
/* 125 */           float var4 = 1.0F;
/* 126 */           GL11.glEnable(3042);
/* 127 */           GL11.glEnable(3008);
/* 128 */           GL11.glBlendFunc(1, 1);
/* 129 */           GL11.glDepthFunc(515);
/*     */           
/* 131 */           Client.setLightmapTextureCoords(15728880);
/* 132 */           if (this.textureLightColor == null) {
/* 133 */             GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */           }
/*     */           else {
/* 136 */             GL11.glColor4f(this.textureLightColor[0], this.textureLightColor[1], this.textureLightColor[2], this.textureLightColor[3]);
/*     */           }
/*     */           
/*     */ 
/*     */ 
/*     */ 
/* 142 */           this.modelOuter.render(this.entityCaps, par2, par3, par4, par5, par6, par7, this.isRendering);
/* 143 */           Client.setLightmapTextureCoords(this.lighting);
/* 144 */           GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 145 */           GL11.glDisable(3042);
/* 146 */           GL11.glEnable(3008);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 151 */     this.renderCount += 1;
/*     */   }
/*     */   
/*     */   public TextureOffset getTextureOffset(String par1Str)
/*     */   {
/* 156 */     return this.modelInner == null ? null : this.modelInner.getTextureOffset(par1Str);
/*     */   }
/*     */   
/*     */ 
/*     */   public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity)
/*     */   {
/* 162 */     if (this.modelInner != null) {
/* 163 */       this.modelInner.setRotationAngles(par1, par2, par3, par4, par5, par6, this.entityCaps);
/*     */     }
/* 165 */     if (this.modelOuter != null) {
/* 166 */       this.modelOuter.setRotationAngles(par1, par2, par3, par4, par5, par6, this.entityCaps);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renderItems(EntityLivingBase pEntity, Render pRender)
/*     */   {
/* 175 */     if (this.modelInner != null) {
/* 176 */       this.modelInner.renderItems(this.entityCaps);
/*     */     }
/*     */   }
/*     */   
/*     */   public void showArmorParts(int pParts)
/*     */   {
/* 182 */     if (this.modelInner != null) {
/* 183 */       this.modelInner.showArmorParts(pParts, 0);
/*     */     }
/* 185 */     if (this.modelOuter != null) {
/* 186 */       this.modelOuter.showArmorParts(pParts, 1);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEntityCaps(IModelCaps pEntityCaps)
/*     */   {
/* 196 */     this.entityCaps = pEntityCaps;
/* 197 */     if (this.capsLink != null) {
/* 198 */       this.capsLink.setEntityCaps(pEntityCaps);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setRender(Render pRender)
/*     */   {
/* 204 */     if (this.modelInner != null) {
/* 205 */       this.modelInner.render = pRender;
/*     */     }
/* 207 */     if (this.modelOuter != null) {
/* 208 */       this.modelOuter.render = pRender;
/*     */     }
/*     */   }
/*     */   
/*     */   public void setArmorRendering(boolean pFlag)
/*     */   {
/* 214 */     this.isRendering = pFlag;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Map<String, Integer> getModelCaps()
/*     */   {
/* 222 */     return this.modelInner == null ? null : this.modelInner.getModelCaps();
/*     */   }
/*     */   
/*     */   public Object getCapsValue(int pIndex, Object... pArg)
/*     */   {
/* 227 */     return this.modelInner == null ? null : this.modelInner.getCapsValue(pIndex, pArg);
/*     */   }
/*     */   
/*     */   public boolean setCapsValue(int pIndex, Object... pArg)
/*     */   {
/* 232 */     if (this.capsLink != null) {
/* 233 */       this.capsLink.setCapsValue(pIndex, pArg);
/*     */     }
/* 235 */     if (this.modelOuter != null) {
/* 236 */       this.modelOuter.setCapsValue(pIndex, pArg);
/*     */     }
/* 238 */     if (this.modelInner != null) {
/* 239 */       return this.modelInner.setCapsValue(pIndex, pArg);
/*     */     }
/* 241 */     return false;
/*     */   }
/*     */   
/*     */   public void showAllParts()
/*     */   {
/* 246 */     if (this.modelInner != null) {
/* 247 */       this.modelInner.showAllParts();
/*     */     }
/* 249 */     if (this.modelOuter != null) {
/* 250 */       this.modelOuter.showAllParts();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/model/mc162/ModelBaseDuo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */