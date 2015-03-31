/*     */ package mmmlibx.lib.multiModel.model.mc162;
/*     */ 
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ModelMultiBase
/*     */   extends ModelBase
/*     */   implements IModelCaps
/*     */ {
/*  16 */   public float[] heldItem = { 0.0F, 0.0F };
/*     */   
/*     */   public boolean aimedBow;
/*     */   
/*     */   public boolean isSneak;
/*     */   
/*     */   public boolean isWait;
/*     */   public ModelRenderer mainFrame;
/*     */   public ModelRenderer HeadMount;
/*     */   public ModelRenderer HeadTop;
/*     */   public ModelRenderer[] Arms;
/*     */   public ModelRenderer[] HardPoint;
/*     */   public float entityIdFactor;
/*     */   public int entityTicksExisted;
/*  30 */   public float scaleFactor = 0.9375F;
/*     */   
/*     */ 
/*     */ 
/*  34 */   private final Map<String, Integer> fcapsmap = new HashMap() {};
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
/*     */   public ModelMultiBase()
/*     */   {
/*  52 */     this(0.0F);
/*     */   }
/*     */   
/*     */   public ModelMultiBase(float pSizeAdjust) {
/*  56 */     this(pSizeAdjust, 0.0F, 64, 32);
/*     */   }
/*     */   
/*     */   public ModelMultiBase(float pSizeAdjust, float pYOffset, int pTextureWidth, int pTextureHeight) {
/*  60 */     this.isSneak = false;
/*  61 */     this.aimedBow = false;
/*  62 */     this.textureWidth = pTextureWidth;
/*  63 */     this.textureHeight = pTextureHeight;
/*     */     
/*  65 */     if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
/*     */     {
/*     */ 
/*  68 */       this.Arms = new ModelRenderer[2];
/*  69 */       this.HeadMount = new ModelRenderer(this, "HeadMount");
/*  70 */       this.HeadTop = new ModelRenderer(this, "HeadTop");
/*     */       
/*  72 */       initModel(pSizeAdjust, pYOffset);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void initModel(float paramFloat1, float paramFloat2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getUsingTexture()
/*     */   {
/*  89 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @Deprecated
/*     */   public abstract float getHeight();
/*     */   
/*     */ 
/*     */ 
/*     */   public float getHeight(IModelCaps pEntityCaps)
/*     */   {
/* 101 */     return getHeight();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @Deprecated
/*     */   public abstract float getWidth();
/*     */   
/*     */ 
/*     */   public float getWidth(IModelCaps pEntityCaps)
/*     */   {
/* 112 */     return getWidth();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @Deprecated
/*     */   public abstract float getyOffset();
/*     */   
/*     */ 
/*     */   public float getyOffset(IModelCaps pEntityCaps)
/*     */   {
/* 123 */     return getyOffset();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @Deprecated
/*     */   public abstract float getMountedYOffset();
/*     */   
/*     */ 
/*     */   public float getMountedYOffset(IModelCaps pEntityCaps)
/*     */   {
/* 134 */     return getMountedYOffset();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getLeashOffset(IModelCaps pEntityCaps)
/*     */   {
/* 142 */     return 0.4F;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   @Deprecated
/*     */   public boolean isItemHolder()
/*     */   {
/* 150 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isItemHolder(IModelCaps pEntityCaps)
/*     */   {
/* 156 */     return isItemHolder();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   @Deprecated
/*     */   public void showAllParts() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void showAllParts(IModelCaps pEntityCaps)
/*     */   {
/* 169 */     showAllParts();
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
/*     */   public int showArmorParts(int parts, int index)
/*     */   {
/* 186 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void renderItems(IModelCaps paramIModelCaps);
/*     */   
/*     */ 
/*     */ 
/*     */   public abstract void renderFirstPersonHand(IModelCaps paramIModelCaps);
/*     */   
/*     */ 
/*     */ 
/*     */   public Map<String, Integer> getModelCaps()
/*     */   {
/* 202 */     return this.fcapsmap;
/*     */   }
/*     */   
/*     */   public Object getCapsValue(int pIndex, Object... pArg)
/*     */   {
/* 207 */     switch (pIndex) {
/*     */     case 1: 
/* 209 */       return this.onGrounds;
/*     */     case 2: 
/* 211 */       return Boolean.valueOf(this.isRiding);
/*     */     case 19: 
/* 213 */       return Boolean.valueOf(this.isSneak);
/*     */     case 263: 
/* 215 */       return Boolean.valueOf(this.isWait);
/*     */     case 3: 
/* 217 */       return Boolean.valueOf(this.isChild);
/*     */     case 16: 
/* 219 */       return Float.valueOf(this.heldItem[1]);
/*     */     case 17: 
/* 221 */       return Float.valueOf(this.heldItem[0]);
/*     */     case 20: 
/* 223 */       return Boolean.valueOf(this.aimedBow);
/*     */     case 288: 
/* 225 */       return Float.valueOf(this.entityIdFactor);
/*     */     case 34: 
/* 227 */       return Integer.valueOf(this.entityTicksExisted);
/*     */     case 512: 
/* 229 */       return Float.valueOf(this.scaleFactor);
/*     */     case 293: 
/* 231 */       return Integer.valueOf(this.dominantArm);
/*     */     }
/* 233 */     return null;
/*     */   }
/*     */   
/*     */   public boolean setCapsValue(int pIndex, Object... pArg)
/*     */   {
/* 238 */     switch (pIndex) {
/*     */     case 1: 
/* 240 */       for (int li = 0; (li < this.onGrounds.length) && (li < pArg.length); li++) {
/* 241 */         this.onGrounds[li] = ((Float)pArg[li]).floatValue();
/*     */       }
/* 243 */       return true;
/*     */     case 2: 
/* 245 */       this.isRiding = ((Boolean)pArg[0]).booleanValue();
/* 246 */       return true;
/*     */     case 19: 
/* 248 */       this.isSneak = ((Boolean)pArg[0]).booleanValue();
/* 249 */       return true;
/*     */     case 263: 
/* 251 */       this.isWait = ((Boolean)pArg[0]).booleanValue();
/* 252 */       return true;
/*     */     case 3: 
/* 254 */       this.isChild = ((Boolean)pArg[0]).booleanValue();
/* 255 */       return true;
/*     */     case 16: 
/* 257 */       this.heldItem[1] = ((Integer)pArg[0]).intValue();
/* 258 */       return true;
/*     */     case 17: 
/* 260 */       this.heldItem[0] = ((Integer)pArg[0]).intValue();
/* 261 */       return true;
/*     */     case 20: 
/* 263 */       this.aimedBow = ((Boolean)pArg[0]).booleanValue();
/* 264 */       return true;
/*     */     case 288: 
/* 266 */       this.entityIdFactor = ((Float)pArg[0]).floatValue();
/* 267 */       return true;
/*     */     case 34: 
/* 269 */       this.entityTicksExisted = ((Integer)pArg[0]).intValue();
/* 270 */       return true;
/*     */     case 512: 
/* 272 */       this.scaleFactor = ((Float)pArg[0]).floatValue();
/* 273 */       return true;
/*     */     case 293: 
/* 275 */       this.dominantArm = ((Integer)pArg[0]).intValue();
/* 276 */       return true;
/*     */     }
/*     */     
/* 279 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/model/mc162/ModelMultiBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */