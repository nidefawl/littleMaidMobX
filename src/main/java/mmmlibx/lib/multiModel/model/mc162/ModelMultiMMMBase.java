/*     */ package mmmlibx.lib.multiModel.model.mc162;
/*     */ 
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import mmmlibx.lib.Client;
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
/*     */ public abstract class ModelMultiMMMBase
/*     */   extends ModelMultiBase
/*     */ {
/*     */   public Map<String, EquippedStabilizer> stabiliser;
/*     */   @Deprecated
/*     */   public float onGround;
/*     */   @Deprecated
/*     */   public float heldItemLeft;
/*     */   @Deprecated
/*     */   public float heldItemRight;
/*     */   
/*     */   public ModelMultiMMMBase() {}
/*     */   
/*     */   public ModelMultiMMMBase(float pSizeAdjust)
/*     */   {
/*  40 */     super(pSizeAdjust);
/*     */   }
/*     */   
/*  43 */   public ModelMultiMMMBase(float pSizeAdjust, float pYOffset, int pTextureWidth, int pTextureHeight) { super(pSizeAdjust, pYOffset, pTextureWidth, pTextureHeight); }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void render(IModelCaps pEntityCaps, float par2, float par3, float ticksExisted, float pheadYaw, float pheadPitch, float par7, boolean pIsRender)
/*     */   {
/*  52 */     setRotationAngles(par2, par3, ticksExisted, pheadYaw, pheadPitch, par7, pEntityCaps);
/*  53 */     this.mainFrame.render(par7, pIsRender);
/*  54 */     renderStabilizer(pEntityCaps, par2, par3, ticksExisted, pheadYaw, pheadPitch, par7);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean preRender(float par2, float par3, float par4, float par5, float par6, float par7)
/*     */   {
/*  63 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renderExtention(float par2, float par3, float par4, float par5, float par6, float par7) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void renderStabilizer(IModelCaps pEntityCaps, float par2, float par3, float ticksExisted, float pheadYaw, float pheadPitch, float par7)
/*     */   {
/*  79 */     if ((this.stabiliser == null) || (this.stabiliser.isEmpty()) || (this.render == null)) {
/*  80 */       return;
/*     */     }
/*  82 */     GL11.glPushMatrix();
/*  83 */     for (Map.Entry<String, EquippedStabilizer> le : this.stabiliser.entrySet()) {
/*  84 */       EquippedStabilizer les = (EquippedStabilizer)le.getValue();
/*  85 */       if ((les != null) && (les.equipPoint != null)) {
/*  86 */         ModelStabilizerBase lsb = les.stabilizer;
/*  87 */         if (lsb.isLoadAnotherTexture()) {
/*  88 */           Client.setTexture(lsb.getTexture());
/*     */         }
/*  90 */         les.equipPoint.loadMatrix();
/*  91 */         lsb.render(this, null, par2, par3, ticksExisted, pheadYaw, pheadPitch, par7);
/*     */       }
/*     */     }
/*  94 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void changeModel(IModelCaps pEntityCaps) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void buildTexture() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDefaultPause() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDefaultPause(float par1, float par2, float pTicksExisted, float pHeadYaw, float pHeadPitch, float par6, IModelCaps pEntityCaps)
/*     */   {
/* 118 */     setDefaultPause();
/*     */   }
/*     */   
/*     */   public boolean setCapsValue(int pIndex, Object... pArg)
/*     */   {
/* 123 */     switch (pIndex) {
/*     */     case 768: 
/* 125 */       changeModel((IModelCaps)pArg[0]);
/* 126 */       return true;
/*     */     case 784: 
/* 128 */       renderFace((IModelCaps)pArg[0], ((Float)pArg[1]).floatValue(), ((Float)pArg[2]).floatValue(), ((Float)pArg[3]).floatValue(), ((Float)pArg[4]).floatValue(), ((Float)pArg[5]).floatValue(), ((Float)pArg[6]).floatValue(), ((Boolean)pArg[7]).booleanValue());
/*     */       
/* 130 */       return true;
/*     */     case 785: 
/* 132 */       renderBody((IModelCaps)pArg[0], ((Float)pArg[1]).floatValue(), ((Float)pArg[2]).floatValue(), ((Float)pArg[3]).floatValue(), ((Float)pArg[4]).floatValue(), ((Float)pArg[5]).floatValue(), ((Float)pArg[6]).floatValue(), ((Boolean)pArg[7]).booleanValue());
/*     */       
/* 134 */       return true;
/*     */     }
/* 136 */     return super.setCapsValue(pIndex, pArg);
/*     */   }
/*     */   
/*     */   public Object getCapsValue(int pIndex, Object... pArg)
/*     */   {
/* 141 */     switch (pIndex) {
/*     */     case 786: 
/* 143 */       return Integer.valueOf(setFaceTexture(((Integer)pArg[0]).intValue()));
/*     */     case 788: 
/* 145 */       return getTextureLightColor((IModelCaps)pArg[0]);
/*     */     }
/* 147 */     return super.getCapsValue(pIndex, pArg);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renderFace(IModelCaps pEntityCaps, float par2, float par3, float ticksExisted, float pheadYaw, float pheadPitch, float par7, boolean pIsRender) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void renderBody(IModelCaps pEntityCaps, float par2, float par3, float ticksExisted, float pheadYaw, float pheadPitch, float par7, boolean pIsRender) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int setFaceTexture(int pIndex)
/*     */   {
/* 165 */     GL11.glTranslatef((pIndex & 0x1) * 32 / this.textureWidth, (pIndex >>> 1 & 0x1) * 16 / this.textureHeight, 0.0F);
/* 166 */     return pIndex / 4;
/*     */   }
/*     */   
/*     */   public float[] getTextureLightColor(IModelCaps pEntityCaps) {
/* 170 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/model/mc162/ModelMultiMMMBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */