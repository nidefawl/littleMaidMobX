/*     */ package mmmlibx.lib.multiModel.model.mc162;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import mmmlibx.lib.multiModel.model.AbstractModelBase;
/*     */ import net.minecraft.client.model.TextureOffset;
/*     */ import net.minecraft.client.renderer.entity.Render;
/*     */ import net.minecraft.util.MathHelper;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ModelBase
/*     */   extends AbstractModelBase
/*     */ {
/*     */   public static final float PI = 3.1415927F;
/*     */   public Render render;
/*  21 */   public int textureWidth = 64;
/*  22 */   public int textureHeight = 32;
/*  23 */   public float[] onGrounds = { 0.0F, 0.0F };
/*  24 */   public int dominantArm = 0;
/*  25 */   public boolean isRiding = false;
/*  26 */   public boolean isChild = true;
/*  27 */   public List<ModelRenderer> boxList = new ArrayList();
/*  28 */   private Map<String, TextureOffset> modelTextureMap = new HashMap();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void render(IModelCaps pEntityCaps, float par2, float par3, float ticksExisted, float pheadYaw, float pheadPitch, float par7, boolean pIsRender) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void setRotationAngles(float par1, float par2, float pTicksExisted, float pHeadYaw, float pHeadPitch, float par6, IModelCaps pEntityCaps) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void setLivingAnimations(IModelCaps pEntityCaps, float par2, float par3, float pRenderPartialTicks) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public ModelRenderer getRandomModelBox(Random par1Random)
/*     */   {
/*  47 */     int li = par1Random.nextInt(this.boxList.size());
/*  48 */     ModelRenderer lmr = (ModelRenderer)this.boxList.get(li);
/*  49 */     for (int lj = 0; lj < this.boxList.size(); lj++) {
/*  50 */       if (!lmr.cubeList.isEmpty()) {
/*     */         break;
/*     */       }
/*     */       
/*  54 */       li++; if (li >= this.boxList.size()) {
/*  55 */         li = 0;
/*     */       }
/*  57 */       lmr = (ModelRenderer)this.boxList.get(li);
/*     */     }
/*  59 */     return lmr;
/*     */   }
/*     */   
/*     */   protected void setTextureOffset(String par1Str, int par2, int par3) {
/*  63 */     this.modelTextureMap.put(par1Str, new TextureOffset(par2, par3));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextureOffset getTextureOffset(String par1Str)
/*     */   {
/*  71 */     return (TextureOffset)this.modelTextureMap.get(par1Str);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static final float mh_sin(float f)
/*     */   {
/*  78 */     f %= 6.2831855F;
/*  79 */     f = f < 0.0F ? 360.0F + f : f;
/*  80 */     return MathHelper.sin(f);
/*     */   }
/*     */   
/*     */   public static final float mh_cos(float f) {
/*  84 */     f %= 6.2831855F;
/*  85 */     f = f < 0.0F ? 360.0F + f : f;
/*  86 */     return MathHelper.cos(f);
/*     */   }
/*     */   
/*     */   public static final float mh_sqrt_float(float f) {
/*  90 */     return MathHelper.sqrt_float(f);
/*     */   }
/*     */   
/*     */   public static final float mh_sqrt_double(double d) {
/*  94 */     return MathHelper.sqrt_double(d);
/*     */   }
/*     */   
/*     */   public static final int mh_floor_float(float f) {
/*  98 */     return MathHelper.floor_float(f);
/*     */   }
/*     */   
/*     */   public static final int mh_floor_double(double d) {
/* 102 */     return MathHelper.floor_double(d);
/*     */   }
/*     */   
/*     */   public static final long mh_floor_double_long(double d) {
/* 106 */     return MathHelper.floor_double_long(d);
/*     */   }
/*     */   
/*     */   public static final float mh_abs(float f) {
/* 110 */     return MathHelper.abs(f);
/*     */   }
/*     */   
/*     */   public static final double mh_abs_max(double d, double d1) {
/* 114 */     return MathHelper.abs_max(d, d1);
/*     */   }
/*     */   
/*     */   public static final int mh_bucketInt(int i, int j) {
/* 118 */     return MathHelper.bucketInt(i, j);
/*     */   }
/*     */   
/*     */   public static final boolean mh_stringNullOrLengthZero(String s) {
/* 122 */     return MathHelper.stringNullOrLengthZero(s);
/*     */   }
/*     */   
/*     */   public static final int mh_getRandomIntegerInRange(Random random, int i, int j) {
/* 126 */     return MathHelper.getRandomIntegerInRange(random, i, j);
/*     */   }
/*     */ }


/* Location:              /home/kongou/Downloads/littleMaidMobX-1.7.x_0.0.8 (1)-deobf.jar!/mmmlibx/lib/multiModel/model/mc162/ModelBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */