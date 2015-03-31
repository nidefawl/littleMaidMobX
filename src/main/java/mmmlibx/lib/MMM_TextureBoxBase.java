/*     */ package mmmlibx.lib;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import mmmlibx.lib.multiModel.model.mc162.IModelCaps;
/*     */ 
/*     */ 
/*     */ public abstract class MMM_TextureBoxBase
/*     */ {
/*     */   public String textureName;
/*     */   protected int contractColor;
/*     */   public int wildColor;
/*     */   protected float modelHeight;
/*     */   protected float modelWidth;
/*     */   protected float modelYOffset;
/*     */   protected float modelMountedYOffset;
/*     */   protected boolean isUpdateSize;
/*     */   
/*     */   public void setModelSize(float pHeight, float pWidth, float pYOffset, float pMountedYOffset)
/*     */   {
/*  22 */     this.modelHeight = pHeight;
/*  23 */     this.modelWidth = pWidth;
/*  24 */     this.modelYOffset = pYOffset;
/*  25 */     this.modelMountedYOffset = pMountedYOffset;
/*     */   }
/*     */   
/*     */   protected int getRandomColor(int pColor, Random pRand) {
/*  29 */     List<Integer> llist = new ArrayList();
/*  30 */     for (int li = 0; li < 16; li++) {
/*  31 */       if ((pColor & 0x1) > 0) {
/*  32 */         llist.add(Integer.valueOf(li));
/*     */       }
/*  34 */       pColor >>>= 1;
/*     */     }
/*     */     
/*  37 */     if (llist.size() > 0) {
/*  38 */       return ((Integer)llist.get(pRand.nextInt(llist.size()))).intValue();
/*     */     }
/*  40 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getContractColorBits()
/*     */   {
/*  48 */     return this.contractColor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getWildColorBits()
/*     */   {
/*  55 */     return this.wildColor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRandomWildColor(Random pRand)
/*     */   {
/*  66 */     return getRandomColor(getWildColorBits(), pRand);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getRandomContractColor(Random pRand)
/*     */   {
/*  73 */     return getRandomColor(getContractColorBits(), pRand);
/*     */   }
/*     */   
/*     */   public float getHeight(IModelCaps pEntityCaps) {
/*  77 */     return this.modelHeight;
/*     */   }
/*     */   
/*  80 */   public float getHeight() { return getHeight(null); }
/*     */   
/*     */   public float getWidth(IModelCaps pEntityCaps)
/*     */   {
/*  84 */     return this.modelWidth;
/*     */   }
/*     */   
/*  87 */   public float getWidth() { return getWidth(null); }
/*     */   
/*     */   public float getYOffset(IModelCaps pEntityCaps)
/*     */   {
/*  91 */     return this.modelYOffset;
/*     */   }
/*     */   
/*  94 */   public float getYOffset() { return getYOffset(null); }
/*     */   
/*     */   public float getMountedYOffset(IModelCaps pEntityCaps)
/*     */   {
/*  98 */     return this.modelMountedYOffset;
/*     */   }
/*     */   
/* 101 */   public float getMountedYOffset() { return getMountedYOffset(null); }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/MMM_TextureBoxBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */